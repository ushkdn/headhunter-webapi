package headhunter_webapi.service.tokenService;

import headhunter_webapi.entity.AuthTokens;
import headhunter_webapi.entity.RefreshToken;
import headhunter_webapi.entity.ServiceResponse;
import headhunter_webapi.repository.CacheRepository;
import headhunter_webapi.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import java.io.IOException;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
public class TokenService implements ITokenService{
    private final UserRepository _userRepository;
    private final CacheRepository _cacheRepository;


    public TokenService(UserRepository userRepository, CacheRepository cacheRepository){
        _userRepository=userRepository;
        _cacheRepository=cacheRepository;
    }
    private final static int REFRESH_TOKEN_COOKIE_LIFETIME=7 * 24 * 60 * 60+3*60*60;
    @Value("${application.security.jwt.secret-key}")
    private String SECRET_KEY;
    @Value("${application.security.jwt.expiration}")
    private long JWT_EXPIRATION;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long REFRESH_TOKEN_EXPIRATION;

    public String extractUserEmail(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims =extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return buildToken(extraClaims, userDetails, JWT_EXPIRATION);
    }
    public String generateRefreshToken(UserDetails userDetails){
        return buildToken( new HashMap<>(), userDetails, REFRESH_TOKEN_EXPIRATION);
    }
    @Override
    public void setRefreshTokenCookie(HttpServletResponse response, String refreshToken){
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(REFRESH_TOKEN_COOKIE_LIFETIME);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
    private Cookie getCookie(HttpServletRequest request, String cookieName){
        return WebUtils.getCookie(request, cookieName);
    }
    public void saveRefreshToken(String refreshToken) throws Exception {
        var storedUser=_userRepository.findUserByEmail(extractUserEmail(refreshToken)).orElseThrow(()->new Exception("User with this email not found."));
        _cacheRepository.save("refreshToken", storedUser.getEmail(), new RefreshToken(
                refreshToken,
                extractCreation(refreshToken),
                extractExpiration(refreshToken)
        ), Duration.ofDays(7));
    }
    public ServiceResponse<AuthTokens> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var serviceResponse = new ServiceResponse<AuthTokens>();
        try {

            Cookie refreshTokenCookie = getCookie(request, "refreshToken");
            if (refreshTokenCookie == null) {
                throw new Exception("User not found. Please try to log-in again.");
            }
            final String userEmail = extractUserEmail(refreshTokenCookie.getValue());
            if (userEmail == null) {
                throw new Exception("Failed to process email correctly.");
            }
            var user = _userRepository.findUserByEmail(userEmail).orElseThrow(()-> new Exception("User with this email not found."));
            if (!isTokenValid(refreshTokenCookie.getValue(), user)) {
                throw new Exception("Please log-in again, your session has expired.");
            }
            Optional<RefreshToken> storedRefreshToken=_cacheRepository.getData("refreshToken", user.getEmail());
            if(storedRefreshToken.isEmpty()){
                throw new Exception("Please log-in again. Your session has expired.");
            }
            if(!storedRefreshToken.get().getToken().equals(refreshTokenCookie.getValue())){
                throw new Exception("Incorrect refresh-token. Please try to log-in again.");
            }
            var accessToken = generateToken(user);
            var newRefreshToken = generateRefreshToken(user);
            saveRefreshToken(newRefreshToken);
            setRefreshTokenCookie(response, newRefreshToken);
            serviceResponse.data = new AuthTokens(accessToken,newRefreshToken);
            serviceResponse.success = true;
            serviceResponse.message = "Refresh and access token successfully updated.";

        } catch (Exception ex) {
            serviceResponse.data = null;
            serviceResponse.success = false;
            serviceResponse.message = ex.getMessage();
        }
        return serviceResponse;
    }


    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS512)
                .compact();
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String userEmail=extractUserEmail(token);
        return (userEmail.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    private Date extractCreation(String token){
        return extractClaim(token, Claims::getIssuedAt);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}