package ru.vlsu.animalcertification.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.vlsu.animalcertification.service.dto.BreedDTO;

import java.util.Optional;

public interface BreedService {

    Page<BreedDTO> findAll(Pageable pageable);

    BreedDTO save(BreedDTO breedDTO);

    Optional<BreedDTO> findOne(Long id);

    void delete(Long id);
}
