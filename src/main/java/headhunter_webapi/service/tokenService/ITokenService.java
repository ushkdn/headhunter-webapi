package headhunter_webapi.service.tokenService;

import headhunter_webapi.entity.AuthTokens;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ITokenService {
    AuthTokens refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
