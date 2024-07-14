package headhunter_webapi.service.tokenService;

import headhunter_webapi.entity.AuthTokens;
import headhunter_webapi.entity.ServiceResponse;
import headhunter_webapi.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;

public interface ITokenService {
    ServiceResponse<AuthTokens> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    void setRefreshTokenCookie(HttpServletResponse response, String refreshToken);
    void saveRefreshToken(String refreshToken) throws Exception;
    String generateRefreshToken(UserDetails userDetails);
    String generateToken(UserDetails userDetails);
}
