package ru.vlsu.animalcertification.repository;

import ru.vlsu.animalcertification.domain.PersonData;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PersonData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PersonDataRepository extends JpaRepository<PersonData, Long> {
}
