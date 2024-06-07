package headhunter_webapi.controller;

import headhunter_webapi.dto.authDto.LogInUserDto;
import headhunter_webapi.dto.authDto.RegisterUserDto;
import headhunter_webapi.dto.userDto.GetUserDto;
import headhunter_webapi.entity.ServiceResponse;
import headhunter_webapi.service.authService.IAuthService;
//import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.io.Serial;

@RestController
@RequestMapping("api/auth")
@Tag(name = "AuthController")
public class AuthController {

    private final IAuthService _authService;

    public AuthController(IAuthService authService){
        _authService=authService;
    }

    @PostMapping("/register")
    public ServiceResponse<String> register(@RequestBody RegisterUserDto newUser){
        return _authService.register(newUser);
    }
    @PostMapping("/log-in")
    public ServiceResponse<String> logIn(@RequestBody LogInUserDto user){
        return _authService.logIn(user);
    }
}
