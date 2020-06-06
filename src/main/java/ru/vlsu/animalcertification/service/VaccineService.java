package ru.vlsu.animalcertification.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.vlsu.animalcertification.service.dto.DocumentDTO;
import ru.vlsu.animalcertification.service.dto.VaccineDTO;

import java.util.Optional;

public interface VaccineService {

    VaccineDTO save(VaccineDTO vaccineDto);

    Page<VaccineDTO> findAll(Pageable pageable);

    Optional<VaccineDTO> findOne(Long id);

    void delete(Long id);
}
