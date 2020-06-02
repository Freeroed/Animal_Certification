package ru.vlsu.animalcertification.repository;

import ru.vlsu.animalcertification.domain.Animal;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Animal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    @Query("select animal from Animal animal where animal.master.login = ?#{principal.username}")
    List<Animal> findByMasterIsCurrentUser();
}
