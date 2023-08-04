package map.neshan.urlshortener.repository;

import map.neshan.urlshortener.models.UrlStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlShortenerRepo extends JpaRepository<UrlStatus, Integer> {

    UrlStatus findUrlsStatusByShortUrl(String shortUrl);

    void deleteUrlStatusByUrlAndUsername(String url, String username);

    int countAllByUsername(String username);

    List<UrlStatus> findAllByLastVisitTimeIsLessThan(long lastVisitTime);
}
