package ru.vlsu.animalcertification.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vlsu.animalcertification.domain.Country;
import ru.vlsu.animalcertification.repository.CountryRepository;
import ru.vlsu.animalcertification.service.CountryService;
import ru.vlsu.animalcertification.service.dto.CountryDTO;
import ru.vlsu.animalcertification.service.mapper.CountryMapper;

import java.util.Optional;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {

    private final Logger log = LoggerFactory.getLogger(CountryService.class);

    private final CountryMapper countryMapper;

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryMapper countryMapper, CountryRepository countryRepository) {
        this.countryMapper = countryMapper;
        this.countryRepository = countryRepository;
    }

    @Override
    public CountryDTO save(CountryDTO countryDto) {
        log.debug("Request to save Country : {}", countryDto);
        Country country = countryMapper.toEntity(countryDto);
        country = countryRepository.save(country);
        return countryMapper.toDto(country);
    }

    @Override
    public Page<CountryDTO> findAll(Pageable pageable) {
        log.debug("Request to find all Countries");
        return countryRepository.findAll(pageable)
            .map(countryMapper::toDto);
    }

    @Override
    public Optional<CountryDTO> findOne(Long id) {
        log.debug("Request to find Country by id : {}", id);
        return countryRepository.findById(id)
            .map(countryMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete country by id : {}", id);
        countryRepository.deleteById(id);

    }
}
