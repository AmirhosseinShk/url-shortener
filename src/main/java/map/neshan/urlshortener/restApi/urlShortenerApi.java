package map.neshan.urlshortener.restApi;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

public interface urlShortenerApi {
    /**
     * @param url      the original url
     * @param username the username of the user who wants to shorten the url
     * @return short url
     */
    @ResponseBody
    ResponseEntity<Resource> shortenUrl(String url, String username);

    /**
     * @param shortUrl the shortened url
     * @return original url
     */
    @ResponseBody
    ResponseEntity<Resource> getOriginalUrl(String shortUrl);

    /**
     * @param url      the shortened url
     * @param username the username of the user who wants to remove the url
     * @return true if the url was removed successfully
     */
    @ResponseBody
    ResponseEntity<Resource> removeUserUrl(String url, String username);

    /**
     * @param shortUrl the shortened url
     * @return the number of visits of the url
     */
    @ResponseBody
    ResponseEntity<Resource> getUrlVisit(String shortUrl);
}
