package ru.vlsu.animalcertification.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vlsu.animalcertification.domain.Breed;
import ru.vlsu.animalcertification.repository.BreedRepository;
import ru.vlsu.animalcertification.service.BreedService;
import ru.vlsu.animalcertification.service.dto.BreedDTO;
import ru.vlsu.animalcertification.service.mapper.BreedMapper;

import java.util.Optional;
@Service
@Transactional
public class BreedServiceImpl implements BreedService {

    private final Logger log = LoggerFactory.getLogger(BreedServiceImpl.class);

    private final BreedMapper breedMapper;

    private final BreedRepository breedRepository;

    public BreedServiceImpl(BreedMapper breedMapper, BreedRepository breedRepository) {
        this.breedMapper = breedMapper;
        this.breedRepository = breedRepository;
    }

    @Override
    public Page<BreedDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Breeds");
        return breedRepository.findAll(pageable)
            .map(breedMapper::toDto);
    }

    @Override
    public BreedDTO save(BreedDTO breedDTO) {
        log.debug("Request to save Breed : {}", breedDTO);
        Breed breed = breedMapper.toEntity(breedDTO);
        breed = breedRepository.save(breed);
        return breedMapper.toDto(breed);
    }

    @Override
    public Optional<BreedDTO> findOne(Long id) {
        log.debug("Request to get Breed by id : {}", id);
        return breedRepository.findById(id)
            .map(breedMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Breed by id : {}", id);
        breedRepository.deleteById(id);
    }
}
