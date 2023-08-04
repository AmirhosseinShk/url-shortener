package map.neshan.urlshortener.restApi;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import map.neshan.urlshortener.jwtToken.JwtUtils;
import map.neshan.urlshortener.jwtToken.JwtUtilsImpl;
import map.neshan.urlshortener.service.UrlShortenerService;
import map.neshan.urlshortener.service.UrlShortenerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Slf4j
public class UrlShortenerControllerApi implements UrlShortenerApi {

    private final UrlShortenerServiceImpl urlShortenerService;
    private final JwtUtilsImpl jwtUtils;

    public UrlShortenerControllerApi(UrlShortenerServiceImpl urlShortenerService, JwtUtilsImpl jwtUtils) {
        this.urlShortenerService = urlShortenerService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    @GetMapping("/shortenUrl")
    public @ResponseBody ResponseEntity<String> shortenUrl(@RequestParam(value = "url") String url, @RequestParam(value = "token") String token) {
        try {
            String username = jwtUtils.getUsernameFromToken(token);
            String shortUrl = urlShortenerService.shortenUrl(url, username);
            return ResponseEntity.ok().body("ShortenUrl Successfully : " + shortUrl);
        } catch (JwtUtils.NotValidTokenException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token not valid");
        } catch (UrlShortenerService.ReachedUrlsLimit r) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(r.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + e.getMessage());
        }
    }

    @Override
    @GetMapping("/getUrl")
    public void getOriginalUrl(@RequestParam(name = "shortUrl") String shortUrl,
                               HttpServletResponse response) {
        try {
            //first we need split params from shortUrl
            String baseShortUrl = getBaseUrl(shortUrl);
            String originalUrl = urlShortenerService.getOriginalUrl(baseShortUrl);
            String url = generateUrlsWithParamsIfExist(originalUrl, shortUrl);
            response.sendRedirect(url);
        } catch (UrlShortenerService.UrlNotFoundException e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    @GetMapping("/removeUrl")
    public ResponseEntity<String> removeUserUrl(@RequestParam(name = "url") String url,
                                                @RequestParam(name = "token") String token) {
        try {
            String username = jwtUtils.getUsernameFromToken(token);
            String baseShortUrl = getBaseUrl(url);
            boolean status = urlShortenerService.removeUserUrl(baseShortUrl, username);
            if (status) {
                return ResponseEntity.ok().body("Url removed successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Url not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token not valid");
        }
    }

    @Override
    @GetMapping("/getUrlVisit")
    public ResponseEntity<String> getUrlVisit(@RequestParam(name = "shortUrl") String shortUrl) {
        try {
            //first we need split params from shortUrl
            String baseShortUrl = getBaseUrl(shortUrl);
            long visit = urlShortenerService.getUrlVisit(baseShortUrl);
            return ResponseEntity.ok().body("Url visit : " + visit);
        } catch (UrlShortenerService.UrlNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Url not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + e.getMessage());
        }
    }

    private String getBaseUrl(String shortUrl) {
        String[] url = shortUrl.split("\\?");
        return url[0];
    }

    private String generateUrlsWithParamsIfExist(String originalUrl, String shortUrl) {
        String[] params = shortUrl.split("\\?");
        if (params.length > 1) {
            return originalUrl + "?" + params[1];
        }
        return originalUrl;
    }
}
