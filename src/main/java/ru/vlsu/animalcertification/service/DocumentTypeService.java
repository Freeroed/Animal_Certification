package ru.vlsu.animalcertification.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.vlsu.animalcertification.service.dto.DocumentTypeDTO;

import java.util.Optional;

public interface DocumentTypeService {

    DocumentTypeDTO save(DocumentTypeDTO documentTypeDto);

    Page<DocumentTypeDTO> findAll(Pageable pageable);

    Optional<DocumentTypeDTO> findOne(Long id);

    void delete(Long id);

}
