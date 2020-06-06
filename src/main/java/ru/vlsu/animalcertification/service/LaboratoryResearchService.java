package ru.vlsu.animalcertification.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.vlsu.animalcertification.service.dto.LaboratoryResearchDTO;

import java.util.Optional;

public interface LaboratoryResearchService {

    LaboratoryResearchDTO save(LaboratoryResearchDTO laboratoryResearchDto);

    Page<LaboratoryResearchDTO> findAll(Pageable pageable);

    Optional<LaboratoryResearchDTO> findOne(Long id);

    void delete(Long id);
}
