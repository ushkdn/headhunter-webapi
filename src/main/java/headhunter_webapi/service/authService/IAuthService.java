package headhunter_webapi.service.authService;

import headhunter_webapi.dto.authDto.ForgotPasswordDto;
import headhunter_webapi.dto.authDto.LogInUserDto;
import headhunter_webapi.dto.authDto.RegisterUserDto;
import headhunter_webapi.dto.authDto.ResetPasswordDto;
import headhunter_webapi.dto.userDto.GetUserDto;
import headhunter_webapi.entity.ServiceResponse;

public interface IAuthService {
    ServiceResponse<String> register(RegisterUserDto newUser);

    ServiceResponse<String> logIn(LogInUserDto user);

    ServiceResponse<String> forgotPassword(ForgotPasswordDto userMetaData);

    ServiceResponse<String> resetPassword(Long id, ResetPasswordDto userMetaData);
}
