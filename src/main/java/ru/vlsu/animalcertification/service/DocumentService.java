package ru.vlsu.animalcertification.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.vlsu.animalcertification.service.dto.DocumentDTO;

import java.util.Optional;

public interface DocumentService {

    DocumentDTO save(DocumentDTO documentDto);

    Page<DocumentDTO> findAll(Pageable pageable);

    Optional<DocumentDTO> findOne(Long id);

    void delete(Long id);
}
