package ru.vlsu.animalcertification.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vlsu.animalcertification.domain.DocumentType;
import ru.vlsu.animalcertification.repository.DocumentTypeRepository;
import ru.vlsu.animalcertification.service.DocumentTypeService;
import ru.vlsu.animalcertification.service.dto.DocumentTypeDTO;
import ru.vlsu.animalcertification.service.mapper.DocumentTypeMapper;

import java.util.Optional;

@Service
@Transactional
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private final Logger log = LoggerFactory.getLogger(DocumentTypeServiceImpl.class);

    private final DocumentTypeMapper documentTypeMapper;

    private final DocumentTypeRepository documentTypeRepository;

    public DocumentTypeServiceImpl(DocumentTypeMapper documentTypeMapper, DocumentTypeRepository documentTypeRepository) {
        this.documentTypeMapper = documentTypeMapper;
        this.documentTypeRepository = documentTypeRepository;
    }

    @Override
    public DocumentTypeDTO save(DocumentTypeDTO documentTypeDto) {
        log.debug("Request to save DocumentType : {}", documentTypeDto);
        DocumentType documentType = documentTypeMapper.toEntity(documentTypeDto);
        documentType = documentTypeRepository.save(documentType);
        return documentTypeMapper.toDto(documentType);
    }

    @Override
    public Page<DocumentTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to find all DocumentTypes");
        return documentTypeRepository.findAll(pageable)
            .map(documentTypeMapper::toDto);
    }

    @Override
    public Optional<DocumentTypeDTO> findOne(Long id) {
        log.debug("Request to find DocumentType by id : {}", id);
        return documentTypeRepository.findById(id)
            .map(documentTypeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DocumentType by id : {}", id);
        documentTypeRepository.deleteById(id);
    }
}
