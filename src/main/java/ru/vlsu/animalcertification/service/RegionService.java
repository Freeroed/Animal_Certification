package ru.vlsu.animalcertification.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.vlsu.animalcertification.service.dto.RegionDTO;

import java.util.Optional;

public interface RegionService {

    RegionDTO save(RegionDTO regionDto);

    Page<RegionDTO> findAll(Pageable pageable);

    Optional<RegionDTO> findOne(Long id);

    void delete(Long id);

}
