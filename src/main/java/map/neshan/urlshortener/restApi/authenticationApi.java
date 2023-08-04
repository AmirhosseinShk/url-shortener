package map.neshan.urlshortener.restApi;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

public interface authenticationApi {
    /**
     * @param username
     * @param password
     * @return jwt token if the user was logged in successfully
     */
    @ResponseBody
    ResponseEntity<Resource> login(String username, String password);

    /**
     * @param username
     * @param password
     * @return true if the user was registered successfully
     */
    @ResponseBody
    ResponseEntity<Resource> register(String username, String password);
}
