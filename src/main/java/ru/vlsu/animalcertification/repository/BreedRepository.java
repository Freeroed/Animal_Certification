package ru.vlsu.animalcertification.repository;

import ru.vlsu.animalcertification.domain.Breed;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Breed entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BreedRepository extends JpaRepository<Breed, Long> {
}
