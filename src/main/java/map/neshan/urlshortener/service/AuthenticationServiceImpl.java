package map.neshan.urlshortener.service;

import lombok.extern.slf4j.Slf4j;
import map.neshan.urlshortener.models.User;
import map.neshan.urlshortener.repository.UsersRepo;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UsersRepo usersRepo;

    public AuthenticationServiceImpl(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    @Override
    public boolean authenticate(String username, String password) {
        User user = usersRepo.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean register(String username, String password) {
        //check if the user already exists
        User user = usersRepo.findByUsername(username);
        if (user != null) {
            return false;
        }

        //define new user
        try {
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
            usersRepo.save(user);

            return true;
        } catch (Exception e) {
            log.error("error in saving user: " + e.getMessage());
            return false;
        }
    }
}
