package ru.vlsu.animalcertification.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vlsu.animalcertification.domain.Request;
import ru.vlsu.animalcertification.service.dto.RequestDTO;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, AnimalMapper.class})
public interface RequestMapper extends EntityMapper<RequestDTO, Request> {

    default Request fromId(Long id) {
        if (id == null) {
            return null;
        }
        Request request = new Request();
        request.setId(id);
        return request;
    }

    @Mapping(source = "requestDTO.creater", target = "creater", qualifiedByName = "userToUser")
    @Mapping(source = "requestDTO.veterinarian", target = "veterinarian", qualifiedByName = "userDTOToUser")
    @Mapping(source = "requestDTO.rshInspector", target = "rshInspector", qualifiedByName = "userDTOToUser")
    @Mapping(source = "requestDTO.animals", target = "animals", qualifiedByName = "animalMapper.toEntity")
    Request toEntity(RequestDTO requestDTO);

    @Mapping(source = "request.creater", target = "creater", qualifiedByName = "userToUserDTO")
    @Mapping(source = "request.veterinarian", target = "veterinarian", qualifiedByName = "userToUserDTO")
    @Mapping(source = "request.rshInspector", target = "rshInspector", qualifiedByName = "userToUserDTO")
    @Mapping(source = "request.animals", target = "animals", qualifiedByName = "animalMapper.toDto")
    RequestDTO toDto(Request request);

    List<Request> toEntity(List<RequestDTO> requestDtoList);

    List<RequestDTO> toDto(List<Request> requestList);
}
