package fr.esgi.api.repositories.request;

import fr.esgi.api.models.request.RequestState;
import fr.esgi.api.models.request.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    Optional<State> findByStateName(RequestState stateName);
}
