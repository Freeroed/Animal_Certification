package ru.vlsu.animalcertification.service.mapper;


import org.mapstruct.Mapper;
import ru.vlsu.animalcertification.domain.AnimalType;
import ru.vlsu.animalcertification.service.dto.AnimalTypeDTO;

@Mapper(componentModel = "spring", uses = {})
public interface AnimalTypeMapper extends EntityMapper<AnimalTypeDTO, AnimalType> {

    default AnimalType fromId(Long id) {
        if (id == null) {
            return null;
        }
        AnimalType animalType = new AnimalType();
        animalType.setId(id);
        return animalType;
    }
}
