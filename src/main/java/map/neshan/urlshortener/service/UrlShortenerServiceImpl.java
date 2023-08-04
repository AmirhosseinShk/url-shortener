package map.neshan.urlshortener.service;

import lombok.extern.slf4j.Slf4j;
import map.neshan.urlshortener.models.UrlStatus;
import map.neshan.urlshortener.repository.UrlShortenerRepo;
import map.neshan.urlshortener.utils.UrlHashUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class UrlShortenerServiceImpl implements UrlShortenerService {

    private final UrlHashUtils urlHashUtils;
    private final UrlShortenerRepo urlShortenerRepo;

    private final long MILLISECONDS_IN_YEAR = 31536000000L;

    public UrlShortenerServiceImpl(UrlHashUtils urlHashUtils, UrlShortenerRepo urlShortenerRepo) {
        this.urlHashUtils = urlHashUtils;
        this.urlShortenerRepo = urlShortenerRepo;
    }

    @Override
    public String shortenUrl(String url, String username) throws Exception {
        //when shortenUrl Trigger we check urls for remove
        checkExpiredUrlsThenRemoveAll();

        // create a hash from url and username
        String hash = urlHashUtils.hashUrl(url, username);

        try {
            //check user limited Urls
            int userUrlsCount = urlShortenerRepo.countAllByUsername(username);
            if (userUrlsCount >= 10) {
                throw new ReachedUrlsLimit("User has reached the limit of urls");
            }

            // save the hash and url in database
            UrlStatus urlStatus = new UrlStatus();
            urlStatus.setUrl(url);
            urlStatus.setShortUrl(hash);
            urlStatus.setUsername(username);
            urlStatus.setVisits(0L);
            urlStatus.setLastVisitTime(System.currentTimeMillis());
            urlShortenerRepo.save(urlStatus);
        } catch (Exception e) {
            throw new Exception("Error in saving url in database");
        }

        return hash;
    }

    @Override
    public String getOriginalUrl(String shortUrl) throws UrlNotFoundException {
        //when shortenUrl Trigger we check urls for remove
        checkExpiredUrlsThenRemoveAll();

        UrlStatus urlStatus = urlShortenerRepo.findUrlsStatusByShortUrl(shortUrl);
        if (urlStatus == null) {
            throw new UrlNotFoundException("Url not found");
        } else {
            //update urlVisitCount
            updateUrlVisitCount(urlStatus);
            return urlStatus.getUrl();
        }
    }

    @Override
    public boolean removeUserUrl(String url, String username) {
        //when shortenUrl Trigger we check urls for remove
        checkExpiredUrlsThenRemoveAll();

        try {
            urlShortenerRepo.deleteUrlStatusByUrlAndUsername(url, username);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public long getUrlVisit(String shortUrl) throws UrlNotFoundException {
        UrlStatus urlStatus = urlShortenerRepo.findUrlsStatusByShortUrl(shortUrl);
        if (urlStatus == null) {
            throw new UrlNotFoundException("Url not found");
        } else {
            return urlStatus.getVisits();
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public void updateUrlVisitCount(UrlStatus urlStatus) {
        try {
            urlStatus.setVisits(urlStatus.getVisits() + 1);
            urlStatus.setLastVisitTime(System.currentTimeMillis());
            urlShortenerRepo.save(urlStatus);
        } catch (Exception e) {
            throw new Error("Error in updating url visit count");
        }
    }

    private void checkExpiredUrlsThenRemoveAll() {
        try {
            long oneYearAgoTimeStamp = System.currentTimeMillis() - MILLISECONDS_IN_YEAR;
            List<UrlStatus> oneYearAgoUrls = urlShortenerRepo.findAllByLastVisitTimeIsLessThan(oneYearAgoTimeStamp);
            urlShortenerRepo.deleteAll(oneYearAgoUrls);
        } catch (Exception e) {
            log.error(e.getMessage(), e.getCause());
        }
    }
}