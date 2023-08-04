package map.neshan.urlshortener.repository;

import map.neshan.urlshortener.models.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@PostgresDataJpaTest
public class UsersRepoTest {

    @Autowired
    private UsersRepo usersRepo;

    @Test
    public void testAddUserAndGetUser() {
        // define and save user
        User user = new User();
        user.setUsername("test-user");
        user.setPassword("test-pass");
        usersRepo.save(user);

        User u = usersRepo.findAll().get(0);
        Assert.assertEquals(u.getUsername(), user.getUsername());
        Assert.assertEquals(u.getPassword(), user.getPassword());
    }
}
