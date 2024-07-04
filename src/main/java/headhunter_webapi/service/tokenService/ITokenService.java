package headhunter_webapi.service.tokenService;

import headhunter_webapi.entity.AuthTokens;
import headhunter_webapi.entity.ServiceResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ITokenService {
    String refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
