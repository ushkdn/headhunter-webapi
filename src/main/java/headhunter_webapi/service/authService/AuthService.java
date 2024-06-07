package headhunter_webapi.service.authService;

import headhunter_webapi.dto.authDto.LogInUserDto;
import headhunter_webapi.dto.authDto.RegisterUserDto;
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
            BCryptPasswordEncoder passwordHash = new BCryptPasswordEncoder();
            user.setPassword(passwordHash.encode(user.getPassword()));
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
            var storedUser = _userRepository.findUserByEmail(user.email());
            if(storedUser.isEmpty()){
                throw new Exception("Email not found.");
            }
            var bcryptVerification = new BCryptPasswordEncoder();
            if(!bcryptVerification.matches(user.password(), storedUser.get().getPassword())){
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
}
