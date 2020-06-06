package ru.vlsu.animalcertification.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vlsu.animalcertification.domain.PersonalData;
import ru.vlsu.animalcertification.repository.PersonalDataRepository;
import ru.vlsu.animalcertification.service.PersonalDataService;
import ru.vlsu.animalcertification.service.dto.PersonalDataDTO;
import ru.vlsu.animalcertification.service.mapper.PersonalDataMapper;

import java.util.Optional;

@Service
@Transactional
public class PersonalDataServiceImpl implements PersonalDataService {

    private final Logger log = LoggerFactory.getLogger(PersonalDataServiceImpl.class);

    private final PersonalDataMapper personalDataMapper;

    private final PersonalDataRepository personalDataRepository;

    public PersonalDataServiceImpl(PersonalDataMapper personalDataMapper, PersonalDataRepository personalDataRepository) {
        this.personalDataMapper = personalDataMapper;
        this.personalDataRepository = personalDataRepository;
    }

    @Override
    public PersonalDataDTO save(PersonalDataDTO personalDataDTO) {
        log.debug("Request to save PersonalData : {}", personalDataDTO);
        PersonalData personalData = personalDataMapper.toEntity(personalDataDTO);
        personalData = personalDataRepository.save(personalData);
        return personalDataMapper.toDto(personalData);
    }

    @Override
    public Page<PersonalDataDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PersonalDatas");
        return personalDataRepository.findAll(pageable)
            .map(personalDataMapper::toDto);
    }

    @Override
    public Optional<PersonalDataDTO> findOne(Long id) {
        log.debug("Request to get PersonalData by id : {}", id);
        return personalDataRepository.findById(id)
            .map(personalDataMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PersonalData by id : {}", id);
        personalDataRepository.deleteById(id);
    }
}
