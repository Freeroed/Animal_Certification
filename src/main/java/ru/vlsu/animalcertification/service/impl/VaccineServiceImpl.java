package ru.vlsu.animalcertification.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vlsu.animalcertification.domain.Vaccine;
import ru.vlsu.animalcertification.repository.VaccineRepository;
import ru.vlsu.animalcertification.service.VaccineService;
import ru.vlsu.animalcertification.service.dto.VaccineDTO;
import ru.vlsu.animalcertification.service.mapper.VaccineMapper;

import java.util.Optional;

@Service
@Transactional
public class VaccineServiceImpl implements VaccineService {

    private final Logger log = LoggerFactory.getLogger(VaccineServiceImpl.class);

    private final VaccineMapper vaccineMapper;

    private final VaccineRepository vaccineRepository;

    public VaccineServiceImpl(VaccineMapper vaccineMapper, VaccineRepository vaccineRepository) {
        this.vaccineMapper = vaccineMapper;
        this.vaccineRepository = vaccineRepository;
    }

    @Override
    public VaccineDTO save(VaccineDTO vaccineDto) {
        log.debug("Request to save Vaccine : {}", vaccineDto);
        Vaccine vaccine = vaccineMapper.toEntity(vaccineDto);
        vaccine = vaccineRepository.save(vaccine);
        return vaccineMapper.toDto(vaccine);
    }

    @Override
    public Page<VaccineDTO> findAll(Pageable pageable) {
        log.debug("Request to find all vaccines");
        return vaccineRepository.findAll(pageable)
            .map(vaccineMapper::toDto);
    }

    @Override
    public Optional<VaccineDTO> findOne(Long id) {
        log.debug("Request to find Vaccine by id : {}", id);
        return vaccineRepository.findById(id)
            .map(vaccineMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Vaccine by id : {}", id);
        vaccineRepository.deleteById(id);
    }
}
