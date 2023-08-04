package map.neshan.urlshortener.jwtToken;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtUtilsImpl implements JwtUtils {

    private final Duration accessTokenExpirationTime;

    private final String accessTokenSecretKeyString;

    /**
     * Token Type Extra Header for JWT Tokens
     */
    private final String ISSUER = "URL-SHORTENER-AUTH";

    /**
     * Access Token Key, it will be used in token type field in JWT
     */
    private final String ACCESS_TOKEN_KEY = "access_token";

    /**
     * Token Type Extra Header for JWT Tokens
     */
    private static final String TOKEN_TYPE_JWT_KEY = "TOKEN_TYPE";

    @Autowired
    JwtUtilsImpl(@Value("${spring.jwt.access-token-generator-key}") String accessTokenSecretKeyString,
                 @Value("${spring.jwt.access-token-expiration-time}") Duration accessExpire
    ) {
        this.accessTokenExpirationTime = accessExpire;
        this.accessTokenSecretKeyString = accessTokenSecretKeyString;
    }

    @Override
    public String createAccessToken(String username) {
        return Jwts.builder()
                .setIssuer(ISSUER)
                .setIssuedAt(new Date())
                .setExpiration(getExpTimeForAccessToken())
                .setHeaderParam(TOKEN_TYPE_JWT_KEY, ACCESS_TOKEN_KEY)
                .signWith(getAccessTokenSecretKey())
                .setSubject(username).compact();
    }

    @Override
    public String getUsernameFromToken(String token) {
        return null;
    }

    private Date getExpTimeForAccessToken() {
        return Date.from(Instant.now().plus(accessTokenExpirationTime));
    }

    private Key getAccessTokenSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessTokenSecretKeyString));
    }
}
