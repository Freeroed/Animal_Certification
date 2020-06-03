package ru.vlsu.animalcertification.service.mapper;

import org.mapstruct.Mapper;
import ru.vlsu.animalcertification.domain.PersonalData;
import ru.vlsu.animalcertification.service.dto.PersonalDataDTO;

@Mapper(componentModel = "spring", uses = {})
public interface PersonalDataMapper extends EntityMapper<PersonalDataDTO, PersonalData> {

    default PersonalData fromId(Long id) {
        if (id == null) {
            return null;
        }
        PersonalData personalData = new PersonalData();
        personalData.setId(id);
        return personalData;
    }
}
