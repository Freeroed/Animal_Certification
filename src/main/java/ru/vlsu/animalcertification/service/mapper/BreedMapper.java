package ru.vlsu.animalcertification.service.mapper;

import org.mapstruct.Mapper;
import ru.vlsu.animalcertification.domain.Breed;
import ru.vlsu.animalcertification.service.dto.BreedDTO;

@Mapper(componentModel = "spring", uses = {})
public interface BreedMapper extends EntityMapper<BreedDTO, Breed> {

    default Breed fromId (Long id) {
        if (id == null) {
            return null;
        }
        Breed breed = new Breed();
        breed.setId(id);
        return breed;
    }
}
