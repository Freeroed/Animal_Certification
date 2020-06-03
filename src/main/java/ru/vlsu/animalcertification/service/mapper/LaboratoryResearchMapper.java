package ru.vlsu.animalcertification.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vlsu.animalcertification.domain.LaboratoryResearch;
import ru.vlsu.animalcertification.domain.Vaccine;
import ru.vlsu.animalcertification.service.dto.LaboratoryResearchDTO;
import ru.vlsu.animalcertification.service.dto.VaccineDTO;

import java.util.List;

@Mapper(componentModel = "spring", uses = AnimalMapper.class)
public interface LaboratoryResearchMapper extends EntityMapper<LaboratoryResearchDTO, LaboratoryResearch> {

    default LaboratoryResearch fromId(Long id) {
        if (id == null) {
            return null;
        }
        LaboratoryResearch laboratoryResearch = new LaboratoryResearch();
        laboratoryResearch.setId(id);
        return laboratoryResearch;
    }

    @Mapping(source = "laboratoryResearch.animal", target = "animal", qualifiedByName = "animalMapper.toDto")
    LaboratoryResearchDTO toDto(LaboratoryResearch laboratoryResearch);

    @Mapping(source = "laboratoryResearchDTO.animal", target = "animal", qualifiedByName = "animalMapper.toEntity")
    LaboratoryResearch toEntity(LaboratoryResearchDTO laboratoryResearchDTO);

    List<LaboratoryResearch> toEntity(List<LaboratoryResearchDTO> laboratoryResearchDTO);

    List<LaboratoryResearchDTO> toDto(List<LaboratoryResearch> laboratoryResearch);
}
