package ru.vlsu.animalcertification.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vlsu.animalcertification.domain.Request;
import ru.vlsu.animalcertification.repository.RequestRepository;
import ru.vlsu.animalcertification.service.RequestService;
import ru.vlsu.animalcertification.service.dto.RequestDTO;
import ru.vlsu.animalcertification.service.mapper.RequestMapper;

import java.util.Optional;

@Service
@Transactional
public class RequestServiceImpl implements RequestService {

    private final Logger log = LoggerFactory.getLogger(RequestServiceImpl.class);

    private final RequestMapper requestMapper;

    private final RequestRepository requestRepository;

    public RequestServiceImpl(RequestMapper requestMapper, RequestRepository requestRepository) {
        this.requestMapper = requestMapper;
        this.requestRepository = requestRepository;
    }

    @Override
    public RequestDTO save(RequestDTO requestDto) {
        log.debug("Request to save Request : {}", requestDto);
        Request request = requestMapper.toEntity(requestDto);
        request = requestRepository.save(request);
        return requestMapper.toDto(request);
    }

    @Override
    public Page<RequestDTO> findAll(Pageable pageable) {
        log.debug("Request to find all Requests");
        return requestRepository.findAllWithEagerRelationships(pageable)
            .map(requestMapper::toDto);
    }

    @Override
    public Optional<RequestDTO> findOne(Long id) {
        log.debug("Request to find Request by id : {}", id);
        return requestRepository.findOneWithEagerRelationships(id)
            .map(requestMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Request by id : {}", id);
        requestRepository.deleteById(id);
    }
}
