package map.neshan.urlshortener.restApi;

import lombok.extern.slf4j.Slf4j;
import map.neshan.urlshortener.jwtToken.JwtUtilsImpl;
import map.neshan.urlshortener.service.AuthenticationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class AuthenticationControllerApi implements AuthenticationApi {

    private final AuthenticationServiceImpl authenticationService;
    private final JwtUtilsImpl jwtUtils;

    AuthenticationControllerApi(AuthenticationServiceImpl authenticationService, JwtUtilsImpl jwtUtils) {
        this.authenticationService = authenticationService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    @GetMapping("/login")
    public @ResponseBody ResponseEntity<String> login(@RequestParam(name = "username") String username,
                                                      @RequestParam(name = "password") String password) {
        boolean auth = authenticationService.authenticate(username, password);
        if (auth) {
            return ResponseEntity.ok()
                    .body("Bearer Authentication: " + jwtUtils.createAccessToken(username));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong username or password");
        }
    }

    @Override
    @GetMapping("/register")
    public @ResponseBody ResponseEntity<String> register(@RequestParam(name = "username") String username,
                                                         @RequestParam(name = "password") String password) {
        boolean register = authenticationService.register(username, password);
        if (register) {
            return ResponseEntity.ok().body("User registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }
    }
}
