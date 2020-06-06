package ru.vlsu.animalcertification.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vlsu.animalcertification.domain.Document;
import ru.vlsu.animalcertification.repository.DocumentRepository;
import ru.vlsu.animalcertification.service.DocumentService;
import ru.vlsu.animalcertification.service.dto.DocumentDTO;
import ru.vlsu.animalcertification.service.mapper.DocumentMapper;

import java.util.Optional;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

    private final Logger log = LoggerFactory.getLogger(DocumentService.class);

    private final DocumentRepository documentRepository;

    private final DocumentMapper documentMapper;

    public DocumentServiceImpl(DocumentRepository documentRepository, DocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
    }

    @Override
    public DocumentDTO save(DocumentDTO documentDto) {
        log.debug("Request to save Document : {}", documentDto);
        Document document = documentMapper.toEntity(documentDto);
        document = documentRepository.save(document);
        return documentMapper.toDto(document);
    }

    @Override
    public Page<DocumentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Documents");
        return documentRepository.findAll(pageable)
            .map(documentMapper::toDto);
    }

    @Override
    public Optional<DocumentDTO> findOne(Long id) {
        log.debug("Request to find Document by id : {}", id);
        return documentRepository.findById(id)
            .map(documentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete document by id : {}", id);
        documentRepository.deleteById(id);
    }
}
