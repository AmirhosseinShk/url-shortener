package map.neshan.urlshortener.restApi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

public interface AuthenticationApi {
    /**
     * @param username the username of the user
     * @param password the password of the user
     * @return jwt token if the user was logged in successfully
     */
    @ResponseBody
    ResponseEntity<String> login(String username, String password);

    /**
     * @param username the username of the user
     * @param password the password of the user
     * @return true if the user was registered successfully
     */
    @ResponseBody
    ResponseEntity<String> register(String username, String password);
}
