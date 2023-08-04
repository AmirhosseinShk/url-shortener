package map.neshan.urlshortener.repository;

import map.neshan.urlshortener.models.UrlsStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlShortenerRepo extends JpaRepository<UrlsStatus, Integer> {
}
