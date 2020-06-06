package ru.vlsu.animalcertification.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.vlsu.animalcertification.service.dto.RequestDTO;

import java.util.Optional;

public interface RequestService {

    RequestDTO save(RequestDTO requestDto);

    Page<RequestDTO> findAll(Pageable pageable);

    Optional<RequestDTO> findOne(Long id);

    void delete(Long id);
}
