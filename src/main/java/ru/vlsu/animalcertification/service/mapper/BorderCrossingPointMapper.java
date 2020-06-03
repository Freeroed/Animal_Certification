package ru.vlsu.animalcertification.service.mapper;

import org.mapstruct.Mapper;
import ru.vlsu.animalcertification.domain.BorderCrossingPoint;
import ru.vlsu.animalcertification.service.dto.BorderCrossingPointDTO;

@Mapper(componentModel = "spring", uses = {})
public interface BorderCrossingPointMapper extends EntityMapper<BorderCrossingPointDTO, BorderCrossingPoint> {

    default BorderCrossingPoint fromId(Long id) {
        if (id == null) {
            return null;
        }
        BorderCrossingPoint borderCrossingPoint = new BorderCrossingPoint();
        borderCrossingPoint.setId(id);
        return borderCrossingPoint;
    }
}
