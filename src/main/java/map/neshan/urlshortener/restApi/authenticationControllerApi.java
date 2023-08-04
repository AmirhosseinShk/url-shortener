package map.neshan.urlshortener.restApi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class authenticationControllerApi implements authenticationApi {

    /**
     * @param username
     * @param password
     * @return jwt token if the user was logged in successfully
     */
    @Override
    @GetMapping("/login/{username}/{password}")
    public @ResponseBody ResponseEntity<Resource> login(@PathVariable(name = "username") String username,
                                                        @PathVariable(name = "password") String password) {
        return null;
    }

    /**
     * @param username
     * @param password
     * @return true if the user was registered successfully
     */
    @Override
    @GetMapping("/register/{username}/{password}")
    public @ResponseBody ResponseEntity<Resource> register(@PathVariable(name = "username") String username,
                                                           @PathVariable(name = "password") String password) {
        return null;
    }
}
