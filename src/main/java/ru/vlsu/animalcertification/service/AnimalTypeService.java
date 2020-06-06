package ru.vlsu.animalcertification.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.vlsu.animalcertification.service.dto.AnimalTypeDTO;

import java.util.Optional;

public interface AnimalTypeService {

    AnimalTypeDTO save(AnimalTypeDTO animalTypeDto);

    Page<AnimalTypeDTO> findAll(Pageable pageable);

    Optional<AnimalTypeDTO> findOne(Long id);

    void delete(Long id);
}
