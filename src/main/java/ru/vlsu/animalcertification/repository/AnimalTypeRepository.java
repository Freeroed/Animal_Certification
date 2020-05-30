package ru.vlsu.animalcertification.repository;

import ru.vlsu.animalcertification.domain.AnimalType;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AnimalType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnimalTypeRepository extends JpaRepository<AnimalType, Long> {
}
