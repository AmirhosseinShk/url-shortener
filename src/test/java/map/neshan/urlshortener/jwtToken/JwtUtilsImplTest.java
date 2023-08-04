package map.neshan.urlshortener.jwtToken;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {JwtUtilsImpl.class})
public class JwtUtilsImplTest {

    @Autowired
    private JwtUtilsImpl jwtUtils;

    @Test
    public void testCreateAccessTokenAndGetUserName() throws JwtUtils.NotValidTokenException {
        String userName = "test";
        String token = jwtUtils.createAccessToken(userName);
        String user = jwtUtils.getUsernameFromToken(token);
        Assert.assertEquals(user, userName);
    }

}
