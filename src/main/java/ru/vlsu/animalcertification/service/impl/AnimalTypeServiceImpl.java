package ru.vlsu.animalcertification.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vlsu.animalcertification.domain.AnimalType;
import ru.vlsu.animalcertification.repository.AnimalTypeRepository;
import ru.vlsu.animalcertification.service.AnimalTypeService;
import ru.vlsu.animalcertification.service.dto.AnimalTypeDTO;
import ru.vlsu.animalcertification.service.mapper.AnimalTypeMapper;

import java.util.Optional;

@Service
@Transactional
public class AnimalTypeServiceImpl implements AnimalTypeService {

    private final Logger log = LoggerFactory.getLogger(AnimalTypeService.class);

    private final AnimalTypeMapper animalTypeMapper;

    private final AnimalTypeRepository animalTypeRepository;

    public AnimalTypeServiceImpl(AnimalTypeMapper animalTypeMapper, AnimalTypeRepository animalTypeRepository) {
        this.animalTypeMapper = animalTypeMapper;
        this.animalTypeRepository = animalTypeRepository;
    }

    @Override
    public AnimalTypeDTO save(AnimalTypeDTO animalTypeDto) {
        log.debug("Request to save AnimalType : {}", animalTypeDto);
        AnimalType animalType = animalTypeMapper.toEntity(animalTypeDto);
        animalType = animalTypeRepository.save(animalType);
        return animalTypeMapper.toDto(animalType);
    }

    @Override
    public Page<AnimalTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to find all AnimalTypes");
        return animalTypeRepository.findAll(pageable)
            .map(animalTypeMapper::toDto);
    }

    @Override
    public Optional<AnimalTypeDTO> findOne(Long id) {
        log.debug("Request to find AnimalType by id : {}", id);
        return animalTypeRepository.findById(id)
            .map(animalTypeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AnimalType : {}");
        animalTypeRepository.deleteById(id);

    }
}
