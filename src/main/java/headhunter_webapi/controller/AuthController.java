package headhunter_webapi.controller;

import headhunter_webapi.service.authService.IAuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@Tag(name = "AuthController")
public class AuthController {

    private final IAuthService _authService;

    public AuthController(IAuthService authService){
        _authService=authService;
    }
}
