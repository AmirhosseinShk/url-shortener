package map.neshan.urlshortener.service;

import org.springframework.stereotype.Service;

@Service
public interface UrlShortenerService {
    /**
     * @param url      the original url
     * @param username the username of the user who wants to shorten the url
     * @return short url
     */
    String shortenUrl(String url, String username) throws Exception;

    /**
     * @param shortUrl the shortened url
     * @return original url
     */
    String getOriginalUrl(String shortUrl) throws UrlNotFoundException;

    /**
     * @param url      the shortened url
     * @param username the username of the user who wants to remove the url
     * @return true if the url was removed successfully
     */
    boolean removeUserUrl(String url, String username);

    /**
     * @param shortUrl the shortened url
     * @return the number of visits of the url
     */
    long getUrlVisit(String shortUrl) throws UrlNotFoundException;


    class ReachedUrlsLimit extends Exception {
        public ReachedUrlsLimit(String message) {
            super(message);
        }
    }

    class UrlNotFoundException extends Exception {
        public UrlNotFoundException(String message) {
            super(message);
        }
    }

}
