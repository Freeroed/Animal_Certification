package ru.vlsu.animalcertification.service.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.vlsu.animalcertification.service.dto.AnimalDTO;

import java.util.Optional;

public interface AnimalService {

    AnimalDTO save(AnimalDTO animalDTO);

    Page<AnimalDTO> findAll(Pageable pageable);

    Optional<AnimalDTO> findOne(Long id);

    void delete(Long id);
}
