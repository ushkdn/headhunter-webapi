package headhunter_webapi.service.authService;

import headhunter_webapi.dto.authDto.ForgotPasswordDto;
import headhunter_webapi.dto.authDto.LogInUserDto;
import headhunter_webapi.dto.authDto.RegisterUserDto;
import headhunter_webapi.dto.authDto.ResetPasswordDto;
import headhunter_webapi.dto.mapper.authMapper.RegisterUserDtoMapper;
import headhunter_webapi.dto.mapper.userMapper.GetUserDtoMapper;
import headhunter_webapi.entity.ServiceResponse;
import headhunter_webapi.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService implements IAuthService{

    private final UserRepository _userRepository;
    private final RegisterUserDtoMapper _registerUserDtoMapper;
    private final GetUserDtoMapper _getUserDtoMapper;

    public AuthService(UserRepository userRepository, RegisterUserDtoMapper registerUserDtoMapper, GetUserDtoMapper getUserDtoMapper) {
        _userRepository = userRepository;
        _registerUserDtoMapper = registerUserDtoMapper;
        _getUserDtoMapper = getUserDtoMapper;
    }

    @Override
    public ServiceResponse<String> register(RegisterUserDto newUser) {
        var serviceResponse = new ServiceResponse<String>();
        try{
            var user = _registerUserDtoMapper.apply(newUser);
            if(_userRepository.findUserByEmail(user.getEmail()).isPresent()){
                throw new Exception("Email already taken!");
            }
            var bcrypt = new BCryptPasswordEncoder();
            user.setPassword(bcrypt.encode(user.getPassword()));
            //email sender here with confirmation code(need verify)
            serviceResponse.data=null;
            serviceResponse.message="You have successfully registered.";
            serviceResponse.success=true;
            _userRepository.save(user);
        }catch(Exception ex){
            serviceResponse.data=null;
            serviceResponse.message=ex.getMessage();
            serviceResponse.success=false;
        }
        return serviceResponse;
    }

    @Override
    public ServiceResponse<String> logIn(LogInUserDto user) {
        var serviceResponse = new ServiceResponse<String>();
        try{
            var storedUser = _userRepository.findUserByEmail(user.email()).orElseThrow(()->new Exception("User not found."));
            var bcryptVerification = new BCryptPasswordEncoder();
            if(!bcryptVerification.matches(user.password(), storedUser.getPassword())){
                throw new Exception("Wrong password.");
            }
            serviceResponse.data=null;
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
