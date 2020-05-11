package fr.esgi.api.repositories.user;


import fr.esgi.api.models.user.Role;
import fr.esgi.api.models.user.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Zakaria FAHRAOUI.
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
