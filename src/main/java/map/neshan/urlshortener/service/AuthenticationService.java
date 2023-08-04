package map.neshan.urlshortener.service;

public interface AuthenticationService {
    /**
     * This function will check if the user is valid or not
     *
     * @param username Username
     * @param password Password
     * @return boolean This will return true if the user is valid
     */
    boolean authenticate(String username, String password);

    /**
     * This function will register a new user
     *
     * @param username Username
     * @param password Password
     * @return boolean This will return true if the user was registered successfully
     */
    boolean register(String username, String password);
}
