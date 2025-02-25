package in.exploretech.logasup.repository;

import in.exploretech.logasup.entity.UserMast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserMastRepository extends JpaRepository<UserMast, String> {

    Optional<UserMast> findByUsername(String username);
    boolean existsByUsername(String username);
}
