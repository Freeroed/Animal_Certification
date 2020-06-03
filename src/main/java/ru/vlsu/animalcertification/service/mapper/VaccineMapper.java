package ru.vlsu.animalcertification.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vlsu.animalcertification.domain.Vaccine;
import ru.vlsu.animalcertification.service.dto.VaccineDTO;

import java.util.List;

@Mapper(componentModel = "spring", uses = AnimalMapper.class)
public interface VaccineMapper extends EntityMapper<VaccineDTO, Vaccine> {

    default Vaccine fromId(Long id) {
        if (id == null) {
            return null;
        }
        Vaccine vaccine = new Vaccine();
        vaccine.setId(id);
        return vaccine;
    }

    @Mapping(source = "vaccine.animal", target = "animal", qualifiedByName = "animalMapper.toDto")
    VaccineDTO toDto(Vaccine vaccine);

    @Mapping(source = "vaccineDTO.animal", target = "animal", qualifiedByName = "animalMapper.toEntity")
    Vaccine toEntity(VaccineDTO vaccineDTO);

    List<Vaccine> toEntity(List<VaccineDTO> vaccineDTO);

    List<VaccineDTO> toDto(List<Vaccine> vaccineDTO);
}
