package ru.vlsu.animalcertification.service.mapper;

import org.mapstruct.Mapper;
import ru.vlsu.animalcertification.domain.Region;
import ru.vlsu.animalcertification.service.dto.RegionDTO;

@Mapper(componentModel = "spring", uses = {})
public interface RegionMapper  extends EntityMapper<RegionDTO, Region> {

    default Region fromId(Long id) {
        if (id == null) {
            return null;
        }
        Region region = new Region();
        region.setId(id);
        return region;
    }
}
