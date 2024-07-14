package headhunter_webapi.service.authService;

import headhunter_webapi.dto.authDto.ForgotPasswordDto;
import headhunter_webapi.dto.authDto.LogInUserDto;
import headhunter_webapi.dto.authDto.RegisterUserDto;
import headhunter_webapi.dto.authDto.ResetPasswordDto;
import headhunter_webapi.dto.mapper.authMapper.RegisterUserDtoMapper;
import headhunter_webapi.dto.mapper.userMapper.GetUserDtoMapper;
import headhunter_webapi.entity.AuthTokens;
import headhunter_webapi.entity.Role;
import headhunter_webapi.entity.ServiceResponse;
import headhunter_webapi.repository.CacheRepository;
import headhunter_webapi.repository.UserRepository;
import headhunter_webapi.service.emailService.EmailService;
import headhunter_webapi.service.emailService.IEmailService;
import headhunter_webapi.service.tokenService.ITokenService;
import headhunter_webapi.service.tokenService.TokenService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;


@Service
public class AuthService implements IAuthService{

    private final UserRepository _userRepository;
    private final RegisterUserDtoMapper _registerUserDtoMapper;
    private final PasswordEncoder _passwordEncoder;
    private final ITokenService _tokenService;
    private final AuthenticationManager _authManager;
    private final IEmailService _emailService;
    private final CacheRepository _cacheRepository;

    public AuthService(UserRepository userRepository, RegisterUserDtoMapper registerUserDtoMapper, PasswordEncoder passwordEncoder, ITokenService tokenService, AuthenticationManager authManager, IEmailService emailService, CacheRepository cacheRepository) {
        _userRepository = userRepository;
        _registerUserDtoMapper = registerUserDtoMapper;
        _passwordEncoder = passwordEncoder;
        _tokenService = tokenService;
        _authManager = authManager;
        _emailService=emailService;
        _cacheRepository=cacheRepository;
    }

    @Override
    public ServiceResponse<AuthTokens> register(RegisterUserDto newUser, HttpServletResponse response) {
        var serviceResponse = new ServiceResponse<AuthTokens>();
        try{
            var user = _registerUserDtoMapper.apply(newUser);
            if(_userRepository.findUserByEmail(user.getEmail()).isPresent()){
                throw new Exception("Email already taken!");
            }
            var bcrypt = new BCryptPasswordEncoder();
            user.setPassword(_passwordEncoder.encode(user.getPassword()));
            user.setRole(Role.USER);
            //email sender here with confirmation code(need verify)

            _userRepository.save(user);
            var secretCodeToVerifyEmail=_emailService.generateSecretCode();
            _emailService.sendEmail(user.getEmail(),"Email verification", secretCodeToVerifyEmail);
            _cacheRepository.save("secretCode", user.getEmail(), secretCodeToVerifyEmail, Duration.ofMinutes(3));
//            var jwtToken= _tokenService.generateToken(user);
//            var refreshToken= _tokenService.generateRefreshToken(user);
//            _tokenService.saveRefreshToken(refreshToken);
//            _tokenService.setRefreshTokenCookie(response, refreshToken);
            serviceResponse.data=null;
                    //new AuthTokens(jwtToken, refreshToken);
            serviceResponse.message="You have successfully registered.";
            serviceResponse.success=true;
        }catch(Exception ex){
            serviceResponse.data=null;
            serviceResponse.message=ex.getMessage();
            serviceResponse.success=false;
        }
        return serviceResponse;
    }

    @Override
    public ServiceResponse<AuthTokens> logIn(LogInUserDto user, HttpServletResponse response) {
        var serviceResponse = new ServiceResponse<AuthTokens>();
        try{
            var storedUser = _userRepository.findUserByEmail(user.email()).orElseThrow(()->new Exception("User not found."));
            var bcryptVerification = new BCryptPasswordEncoder();
            if(!bcryptVerification.matches(user.password(), storedUser.getPassword())){
                throw new Exception("Wrong password.");
            }
            _authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.email(),
                            user.password()
                    )
            );
            var jwtToken = _tokenService.generateToken(storedUser);
            var refreshToken= _tokenService.generateRefreshToken(storedUser);
            _tokenService.saveRefreshToken(refreshToken);
            _tokenService.setRefreshTokenCookie(response, refreshToken);
            serviceResponse.data=new AuthTokens(jwtToken, refreshToken);
            serviceResponse.message="You have successfully logged in";
            serviceResponse.success=true;
        }catch(Exception ex){
            serviceResponse.data=null;
            serviceResponse.message=ex.getMessage();
            serviceResponse.success=false;
        }
        return serviceResponse;
    }

    @Override
    public ServiceResponse<String> forgotPassword(ForgotPasswordDto userMetaData){
        var serviceResponse = new ServiceResponse<String>();
        try{
            var storedUser = _userRepository.findUserByEmail(userMetaData.email()).orElseThrow(()->new Exception("User not found."));
            //email sender here
            serviceResponse.data=null;
            serviceResponse.message="Security code sent to your email.";
            serviceResponse.success=true;
        }catch(Exception ex){
            serviceResponse.data=null;
            serviceResponse.message=ex.getMessage();
            serviceResponse.success=false;
        }
        return serviceResponse;
    }

    @Override
    public ServiceResponse<String> resetPassword(Long id, ResetPasswordDto userData){
        var serviceResponse = new ServiceResponse<String>();
        try{
            var storedUser = _userRepository.findById(id).orElseThrow(()-> new Exception("User not found"));
            if(!storedUser.getVerified()){
                throw new Exception("You have not verified your email.");
            }
            //there should be a secret code check
            var bcrypt = new BCryptPasswordEncoder();
            storedUser.setPassword(bcrypt.encode(userData.password()));
            serviceResponse.data=null;
            serviceResponse.message="You have successfully changed your password.";
            serviceResponse.success=true;
            _userRepository.save(storedUser);

        }catch(Exception ex){
            serviceResponse.data=null;
            serviceResponse.message=ex.getMessage();
            serviceResponse.success=false;
        }
        return serviceResponse;
    }
}
