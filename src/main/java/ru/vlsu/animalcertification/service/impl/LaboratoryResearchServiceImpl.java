package ru.vlsu.animalcertification.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vlsu.animalcertification.domain.LaboratoryResearch;
import ru.vlsu.animalcertification.repository.LaboratoryResearchRepository;
import ru.vlsu.animalcertification.service.LaboratoryResearchService;
import ru.vlsu.animalcertification.service.dto.LaboratoryResearchDTO;
import ru.vlsu.animalcertification.service.mapper.LaboratoryResearchMapper;

import java.util.Optional;

@Service
@Transactional
public class LaboratoryResearchServiceImpl implements LaboratoryResearchService {

    private final Logger log = LoggerFactory.getLogger(LaboratoryResearchServiceImpl.class);

    private final LaboratoryResearchMapper laboratoryResearchMapper;

    private final LaboratoryResearchRepository laboratoryResearchRepository;

    public LaboratoryResearchServiceImpl(LaboratoryResearchMapper laboratoryResearchMapper,
                                         LaboratoryResearchRepository laboratoryResearchRepository) {
        this.laboratoryResearchMapper = laboratoryResearchMapper;
        this.laboratoryResearchRepository = laboratoryResearchRepository;
    }

    @Override
    public LaboratoryResearchDTO save(LaboratoryResearchDTO laboratoryResearchDto) {
        log.debug("Request to save LaboratoryResearch : {}", laboratoryResearchDto);
        LaboratoryResearch laboratoryResearch = laboratoryResearchMapper.toEntity(laboratoryResearchDto);
        laboratoryResearch = laboratoryResearchRepository.save(laboratoryResearch);
        return laboratoryResearchMapper.toDto(laboratoryResearch);
    }

    @Override
    public Page<LaboratoryResearchDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LaboratoryResearches");
        return laboratoryResearchRepository.findAll(pageable)
            .map(laboratoryResearchMapper::toDto);
    }

    @Override
    public Optional<LaboratoryResearchDTO> findOne(Long id) {
        log.debug("Request to find LaboratoryResearch by id : {}", id);
        return laboratoryResearchRepository.findById(id)
            .map(laboratoryResearchMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete LaboratoryResearch by id : {}", id);
        laboratoryResearchRepository.deleteById(id);
    }
}
