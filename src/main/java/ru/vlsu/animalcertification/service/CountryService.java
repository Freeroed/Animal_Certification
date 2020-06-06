package ru.vlsu.animalcertification.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.vlsu.animalcertification.service.dto.CountryDTO;

import java.util.Optional;

public interface CountryService {

    CountryDTO save(CountryDTO countryDto);

    Page<CountryDTO> findAll(Pageable pageable);

    Optional<CountryDTO> findOne(Long id);

    void delete(Long id);
}
