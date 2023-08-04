package map.neshan.urlshortener.repository;

import map.neshan.urlshortener.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {
}
