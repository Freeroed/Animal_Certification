package ru.vlsu.animalcertification.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.vlsu.animalcertification.service.dto.AddressDTO;

import java.util.Optional;

public interface AddressService {

    AddressDTO save(AddressDTO addressDto);

    Page<AddressDTO> findAll(Pageable pageable);

    Optional<AddressDTO> findOne(Long id);

    void delete(Long id);
}
