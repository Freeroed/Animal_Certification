package ru.vlsu.animalcertification.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vlsu.animalcertification.domain.Animal;
import ru.vlsu.animalcertification.repository.AnimalRepository;
import ru.vlsu.animalcertification.service.dto.AnimalDTO;
import ru.vlsu.animalcertification.service.mapper.AnimalMapper;
import ru.vlsu.animalcertification.service.mapper.AnimalService;

import java.util.Optional;

@Service
@Transactional
public class AnimalServiceImpl implements AnimalService {

    private final Logger log = LoggerFactory.getLogger(AnimalService.class);

    private final AnimalRepository animalRepository;

    private final AnimalMapper animalMapper;

    public AnimalServiceImpl(AnimalRepository animalRepository, AnimalMapper animalMapper) {
        this.animalRepository = animalRepository;
        this.animalMapper = animalMapper;
    }

    @Override
    public AnimalDTO save(AnimalDTO animalDTO) {
        log.debug("Request to save Animal : {}", animalDTO);
        Animal animal = animalMapper.toEntity(animalDTO);
        animal = animalRepository.save(animal);
        return animalMapper.toDto(animal);
    }

    @Override
    public Page<AnimalDTO> findAll(Pageable pageable) {
        log.debug("Request to find all Animals");
        return animalRepository.findAll(pageable)
            .map(animalMapper::toDto);
    }

    @Override
    public Optional<AnimalDTO> findOne(Long id) {
        log.debug("Request to get Animal by id : {}", id);
        return animalRepository.findById(id)
            .map(animalMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Animal by id : {}", id);
        animalRepository.deleteById(id);
    }
}
