package ru.vlsu.animalcertification.repository;

import ru.vlsu.animalcertification.domain.LaboratoryResearch;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the LaboratoryResearch entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LaboratoryResearchRepository extends JpaRepository<LaboratoryResearch, Long> {
}
