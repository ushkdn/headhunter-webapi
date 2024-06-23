package headhunter_webapi.service.tokenService;

import com.fasterxml.jackson.databind.ObjectMapper;
import headhunter_webapi.entity.AuthTokens;
import headhunter_webapi.entity.Token;
import headhunter_webapi.entity.TokenType;
import headhunter_webapi.entity.User;
import headhunter_webapi.repository.TokenRepository;
import headhunter_webapi.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class TokenService implements ITokenService{
    private final UserRepository _userRepository;
    private final TokenRepository _tokenRepository;


    public TokenService(UserRepository userRepository, TokenRepository tokenRepository){
        _userRepository=userRepository;
        _tokenRepository = tokenRepository;
    }
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

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
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }
    public String generateRefreshToken( UserDetails userDetails){
        return buildToken( new HashMap<>(), userDetails, refreshExpiration);
    }
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return;
        }
        refreshToken= authHeader.substring(7);
        userEmail=extractUserEmail(refreshToken);
        if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            var user = _userRepository.findUserByEmail(userEmail).orElseThrow();
            if(isTokenValid(refreshToken, user)){
                var accessToken=generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user,accessToken);
                var authTokens=new AuthTokens(accessToken, refreshToken);
                new ObjectMapper().writeValue(response.getOutputStream(),authTokens);
            }
        }
    }
    private void revokeAllUserTokens(User user) {
        var validUserToken = _tokenRepository.findValidTokenByUser(user.getId());
        if (validUserToken==null)
            return;
        validUserToken.setExpired(true);
        validUserToken.setRevoked(true);
        _tokenRepository.save(validUserToken);
    }
    private void saveUserToken(User user, String jwtToken) {
        var token = new Token(jwtToken, TokenType.BEARER, false,false,user);
        _tokenRepository.save(token);
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

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
