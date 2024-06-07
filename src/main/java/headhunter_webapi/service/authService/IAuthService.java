package headhunter_webapi.service.authService;

import headhunter_webapi.dto.authDto.LogInUserDto;
import headhunter_webapi.dto.authDto.RegisterUserDto;
import headhunter_webapi.dto.userDto.GetUserDto;
import headhunter_webapi.entity.ServiceResponse;

public interface IAuthService {
    ServiceResponse<String> register(RegisterUserDto newUser);

    ServiceResponse<String> logIn(LogInUserDto user);

    // ServiceResponse<String> forgotPassword(String email);

    // ServiceResponse<String> resetPassword(Long id, ResetPasswordDto);
}
