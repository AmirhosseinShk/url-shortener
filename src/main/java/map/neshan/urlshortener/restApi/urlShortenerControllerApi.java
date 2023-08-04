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
public class urlShortenerControllerApi implements urlShortenerApi {
    /**
     * @param url      the original url
     * @param username the username of the user who wants to shorten the url
     * @return short url
     */
    @Override
    @GetMapping("/shorten/{url}/{username})")
    public @ResponseBody ResponseEntity<Resource> shortenUrl(@PathVariable(value = "url") String url, @PathVariable(value = "username") String username) {
        return null;
    }

    /**
     * @param shortUrl the shortened url
     * @return original url
     */
    @Override
    public @ResponseBody ResponseEntity<Resource> getOriginalUrl(String shortUrl) {
        return null;
    }

    @Override
    public ResponseEntity<Resource> removeUserUrl(String url, String username) {
        return null;
    }

    @Override
    public ResponseEntity<Resource> getUrlVisit(String shortUrl) {
        return null;
    }
}
