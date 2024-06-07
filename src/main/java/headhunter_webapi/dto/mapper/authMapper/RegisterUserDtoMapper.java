package headhunter_webapi.dto.mapper.authMapper;

import headhunter_webapi.dto.authDto.RegisterUserDto;
import headhunter_webapi.entity.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RegisterUserDtoMapper implements Function<RegisterUserDto, User> {
    @Override
    public User apply(RegisterUserDto user){
        return new User(
                user.firstName(),
                user.lastName(),
                user.phoneNumber(),
                user.email(),
                user.password(),
                user.dateOfBirth(),
                user.location());
    }

}
