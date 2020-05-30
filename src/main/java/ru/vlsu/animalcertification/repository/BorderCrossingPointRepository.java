package ru.vlsu.animalcertification.repository;

import ru.vlsu.animalcertification.domain.BorderCrossingPoint;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BorderCrossingPoint entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BorderCrossingPointRepository extends JpaRepository<BorderCrossingPoint, Long> {
}
