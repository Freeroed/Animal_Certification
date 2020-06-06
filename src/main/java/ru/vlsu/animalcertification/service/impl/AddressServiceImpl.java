package ru.vlsu.animalcertification.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vlsu.animalcertification.domain.Address;
import ru.vlsu.animalcertification.repository.AddressRepository;
import ru.vlsu.animalcertification.service.AddressService;
import ru.vlsu.animalcertification.service.dto.AddressDTO;
import ru.vlsu.animalcertification.service.mapper.AddressMapper;

import java.util.Optional;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private final Logger log = LoggerFactory.getLogger(AddressService.class);

    private final AddressRepository addressRepository;

    private final AddressMapper addressMapper;

    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public AddressDTO save(AddressDTO addressDto) {
        log.debug("Request to save address : {}", addressDto);
        Address address = addressMapper.toEntity(addressDto);
        address = addressRepository.save(address);
        return addressMapper.toDto(address);
    }

    @Override
    public Page<AddressDTO> findAll(Pageable pageable) {
        log.debug("Request to find all Addresses");
        return addressRepository.findAll(pageable)
            .map(addressMapper::toDto);
    }

    @Override
    public Optional<AddressDTO> findOne(Long id) {
        log.debug("Request to find Address by id : {}", id);
        return addressRepository.findById(id)
            .map(addressMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Address by id : {}", id);
        addressRepository.deleteById(id);
    }
}
