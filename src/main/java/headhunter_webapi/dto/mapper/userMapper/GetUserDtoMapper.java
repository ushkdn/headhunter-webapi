package headhunter_webapi.dto.mapper.userMapper;

import headhunter_webapi.dto.userDto.GetUserDto;
import headhunter_webapi.entity.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class GetUserDtoMapper implements Function<User, GetUserDto> {
    @Override
    public GetUserDto apply(User user){
        return new GetUserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getDateOfBirth(),
                user.getLocation()
        );
    }
}
