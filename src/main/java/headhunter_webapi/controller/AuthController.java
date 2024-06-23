package headhunter_webapi.controller;

import headhunter_webapi.dto.authDto.ForgotPasswordDto;
import headhunter_webapi.dto.authDto.LogInUserDto;
import headhunter_webapi.dto.authDto.RegisterUserDto;
import headhunter_webapi.dto.authDto.ResetPasswordDto;
import headhunter_webapi.entity.ServiceResponse;
import headhunter_webapi.service.authService.IAuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@Tag(name = "AuthController")
public class AuthController {

    private final IAuthService _authService;

    public AuthController(IAuthService authService){
        _authService=authService;
    }


    @PostMapping("/register")
    public ServiceResponse<String> register(@Valid @RequestBody RegisterUserDto newUser){
        return _authService.register(newUser);
    }
    @PostMapping("/log-in")
    public ServiceResponse<String> logIn(@Valid @RequestBody LogInUserDto user){
        return _authService.logIn(user);
    }

    @PostMapping("/forgot-password")
    public ServiceResponse<String> forgotPassword(@Valid @RequestBody ForgotPasswordDto userMetaData){
        return _authService.forgotPassword(userMetaData);
    }
    @PostMapping("/reset-password/{id}")
    public ServiceResponse<String> resetPassword(@PathVariable Long id, @Valid @RequestBody ResetPasswordDto userMetaData){
        return _authService.resetPassword(id, userMetaData);
    }
}
