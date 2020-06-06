package ru.vlsu.animalcertification.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.vlsu.animalcertification.service.dto.BorderCrossingPointDTO;

import java.util.Optional;

public interface BorderCrossingPointService {

    BorderCrossingPointDTO save(BorderCrossingPointDTO borderCrossingPointDto);

    Page<BorderCrossingPointDTO> findAll(Pageable pageable);

    Optional<BorderCrossingPointDTO> findOne(Long id);

    void delete(Long id);
}
