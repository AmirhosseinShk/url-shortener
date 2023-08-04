package map.neshan.urlshortener.jwtToken;

import io.jsonwebtoken.JwtBuilder;

public interface JwtUtils {
    /**
     * Create accessToken for users
     *
     * @param username Username
     * @return String This will return JWT token String [access token]
     */
    String createAccessToken(String username);

    /**
     * This function will parse the token and extract username from it.
     *
     * @param token
     * @return String Username
     */
    String getUsernameFromToken(String token) throws NotValidTokenException;

    /**
     * This Exception will thrown if JWT token is not valid to parse or expired or token's type mismatch.
     */
    class NotValidTokenException extends Exception {
        public NotValidTokenException(Exception ex) {
            super(ex);
        }
    }
}
