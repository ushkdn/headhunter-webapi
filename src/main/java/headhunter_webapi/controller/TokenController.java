package headhunter_webapi.controller;

import headhunter_webapi.entity.ServiceResponse;
import headhunter_webapi.service.tokenService.ITokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/token")
@Tag(name = "TokenController")
public class TokenController {

    private final ITokenService _tokenService;

    public TokenController(ITokenService tokenService){
        _tokenService=tokenService;
    }
    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response ) throws IOException {
        _tokenService.refreshToken(request, response);
    }
}
