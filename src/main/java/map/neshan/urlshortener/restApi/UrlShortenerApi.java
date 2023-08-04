package map.neshan.urlshortener.restApi;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

public interface UrlShortenerApi {
    /**
     * @param url   the original url
     * @param token the token of the user who wants to shorten the url
     * @return short url
     */
    @ResponseBody
    ResponseEntity<String> shortenUrl(String url, String token);

    /**
     * @param shortUrl the shortened url
     * @param response
     */
    void getOriginalUrl(String shortUrl, HttpServletResponse response);

    /**
     * @param url   the shortened url
     * @param token the token of the user who wants to remove the url
     * @return true if the url was removed successfully
     */
    @ResponseBody
    ResponseEntity<String> removeUserUrl(String url, String token);

    /**
     * @param shortUrl the shortened url
     * @return the number of visits of the url
     */
    @ResponseBody
    ResponseEntity<String> getUrlVisit(String shortUrl);
}
