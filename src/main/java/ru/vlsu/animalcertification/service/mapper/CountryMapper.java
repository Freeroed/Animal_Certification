package ru.vlsu.animalcertification.service.mapper;

import org.mapstruct.Mapper;
import ru.vlsu.animalcertification.domain.Country;
import ru.vlsu.animalcertification.service.dto.CountryDTO;

@Mapper(componentModel = "spring", uses = {})
public interface CountryMapper extends EntityMapper<CountryDTO, Country> {

    default Country fromId(Long id) {
        if (id == null) {
            return null;
        }
        Country country = new Country();
        country.setId(id);
        return country;
    }
}
