package headhunter_webapi.controller;

import headhunter_webapi.service.tokenService.ITokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/token")
@Tag(name = "TokenController")
public class TokenController {

    private final ITokenService _tokenService;

    public TokenController(ITokenService tokenService){
        _tokenService=tokenService;
    }
}
