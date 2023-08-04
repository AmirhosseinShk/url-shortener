package map.neshan.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlShortenerRepo extends JpaRepository<UrlsStatus, Integer> {
}
