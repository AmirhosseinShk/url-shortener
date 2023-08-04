package map.neshan.urlshortener.repository;

import map.neshan.urlshortener.models.UrlsStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlShortenerRepo extends JpaRepository<UrlsStatus, Integer> {

}
