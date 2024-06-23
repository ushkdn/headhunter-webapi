package headhunter_webapi.service.tokenService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ITokenService {
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
