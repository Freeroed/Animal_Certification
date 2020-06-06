package ru.vlsu.animalcertification.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vlsu.animalcertification.domain.BorderCrossingPoint;
import ru.vlsu.animalcertification.repository.BorderCrossingPointRepository;
import ru.vlsu.animalcertification.service.BorderCrossingPointService;
import ru.vlsu.animalcertification.service.dto.BorderCrossingPointDTO;
import ru.vlsu.animalcertification.service.mapper.BorderCrossingPointMapper;

import java.util.Optional;

@Service
@Transactional
public class BorderCrossingPointServiceImpl implements BorderCrossingPointService {

    private final Logger log = LoggerFactory.getLogger(BorderCrossingPointService.class);

    private final BorderCrossingPointMapper borderCrossingPointMapper;

    private final BorderCrossingPointRepository borderCrossingPointRepository;

    public BorderCrossingPointServiceImpl(BorderCrossingPointMapper borderCrossingPointMapper,
                                          BorderCrossingPointRepository borderCrossingPointRepository) {
        this.borderCrossingPointMapper = borderCrossingPointMapper;
        this.borderCrossingPointRepository = borderCrossingPointRepository;
    }

    @Override
    public BorderCrossingPointDTO save(BorderCrossingPointDTO borderCrossingPointDto) {
        log.debug("Request to save BorderCrossingPoint : {}", borderCrossingPointDto);
        BorderCrossingPoint borderCrossingPoint = borderCrossingPointMapper.toEntity(borderCrossingPointDto);
        borderCrossingPoint = borderCrossingPointRepository.save(borderCrossingPoint);
        return borderCrossingPointMapper.toDto(borderCrossingPoint);
    }

    @Override
    public Page<BorderCrossingPointDTO> findAll(Pageable pageable) {
        log.debug("Request to find all BorderCrossingPoints");
        return borderCrossingPointRepository.findAll(pageable)
            .map(borderCrossingPointMapper::toDto);
    }

    @Override
    public Optional<BorderCrossingPointDTO> findOne(Long id) {
        log.debug("Request to find BorderCrossingPoint by id : {}", id);
        return borderCrossingPointRepository.findById(id)
            .map(borderCrossingPointMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to BorderCrossingPoint by id : {}", id);
        borderCrossingPointRepository.deleteById(id);
    }
}
