package ru.vlsu.animalcertification.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.vlsu.animalcertification.service.dto.PersonalDataDTO;

import java.util.Optional;

public interface PersonalDataService {

    PersonalDataDTO save(PersonalDataDTO personalDataDTO);

    Page<PersonalDataDTO> findAll(Pageable pageable);

    Optional<PersonalDataDTO> findOne(Long id);

    void delete(Long id);
}
