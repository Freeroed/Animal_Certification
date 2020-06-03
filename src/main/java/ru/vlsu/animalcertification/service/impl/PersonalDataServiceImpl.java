package ru.vlsu.animalcertification.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vlsu.animalcertification.repository.PersonalDataRepository;
import ru.vlsu.animalcertification.service.PersonalDataService;
import ru.vlsu.animalcertification.service.dto.PersonalDataDTO;
import ru.vlsu.animalcertification.service.mapper.PersonalDataMapper;

import java.util.List;
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
        return null;
    }

    @Override
    public Page<PersonalDataDTO> findAll() {
        log.debug("Request to get all PersonalDatas");
        return personalDataRepository.findAll(Pageable.unpaged())
            .map(personalDataMapper::toDto);
    }

    @Override
    public Optional<PersonalDataDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }
}
