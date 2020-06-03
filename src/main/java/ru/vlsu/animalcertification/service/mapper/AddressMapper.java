package ru.vlsu.animalcertification.service.mapper;

import org.mapstruct.Mapper;
import ru.vlsu.animalcertification.domain.Address;
import ru.vlsu.animalcertification.service.dto.AddressDTO;

@Mapper(componentModel = "spring", uses = {})
public interface AddressMapper extends EntityMapper<AddressDTO, Address> {

    default Address fromId(Long id) {
        if (id == null) {
            return null;
        }
        Address address = new Address();
        address.setId(id);
        return address;
    }
}
