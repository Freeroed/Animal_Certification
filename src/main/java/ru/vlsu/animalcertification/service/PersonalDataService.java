package ru.vlsu.animalcertification.service;

import org.springframework.data.domain.Page;
import ru.vlsu.animalcertification.service.dto.PersonalDataDTO;

import java.util.Optional;

public interface PersonalDataService {

    PersonalDataDTO save(PersonalDataDTO personalDataDTO);

    Page<PersonalDataDTO> findAll();

    Optional<PersonalDataDTO> findOne(Long id);

    void delete(Long id);
}
