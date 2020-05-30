package ru.vlsu.animalcertification.repository;

import ru.vlsu.animalcertification.domain.Vaccine;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Vaccine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
}
