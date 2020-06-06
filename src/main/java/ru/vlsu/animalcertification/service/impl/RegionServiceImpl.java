package ru.vlsu.animalcertification.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vlsu.animalcertification.domain.Region;
import ru.vlsu.animalcertification.repository.RegionRepository;
import ru.vlsu.animalcertification.service.RegionService;
import ru.vlsu.animalcertification.service.dto.RegionDTO;
import ru.vlsu.animalcertification.service.mapper.RegionMapper;

import java.util.Optional;

@Service
@Transactional
public class RegionServiceImpl implements RegionService {

    private final Logger log = LoggerFactory.getLogger(RegionServiceImpl.class);

    private final RegionRepository regionRepository;

    private final RegionMapper regionMapper;

    public RegionServiceImpl(RegionRepository regionRepository, RegionMapper regionMapper) {
        this.regionRepository = regionRepository;
        this.regionMapper = regionMapper;
    }

    @Override
    public RegionDTO save(RegionDTO regionDto) {
        log.debug("Request to save Region : {}", regionDto);
        Region region = regionMapper.toEntity(regionDto);
        region = regionRepository.save(region);
        return regionMapper.toDto(region);
    }

    @Override
    public Page<RegionDTO> findAll(Pageable pageable) {
        log.debug("Request to find all Regions");
        return regionRepository.findAll(pageable)
            .map(regionMapper::toDto);
    }

    @Override
    public Optional<RegionDTO> findOne(Long id) {
        log.debug("Request to find Region by id : {}", id);
        return regionRepository.findById(id)
            .map(regionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Region by id : {}", id);
        regionRepository.deleteById(id);
    }
}
