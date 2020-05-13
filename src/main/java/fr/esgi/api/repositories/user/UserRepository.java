package fr.esgi.api.repositories.user;

import fr.esgi.api.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Zakaria FAHRAOUI.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.username=:x")
    User listByUser(@Param("x") String userName);

    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
