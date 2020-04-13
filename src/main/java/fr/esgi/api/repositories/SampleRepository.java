package fr.esgi.api.repositories;

import fr.esgi.api.Entities.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleRepository extends JpaRepository<Sample, Integer> {
}
