package ru.vlsu.animalcertification.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vlsu.animalcertification.domain.Animal;
import ru.vlsu.animalcertification.service.dto.AnimalDTO;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface AnimalMapper extends EntityMapper<AnimalDTO, Animal> {

    default Animal fromId(Long id) {
        if (id == null) {
            return null;
        }
        Animal animal = new Animal();
        animal.setId(id);
        return animal;

    }

    @Mapping(source = "animalDTO.master", target = "master", qualifiedByName = "userDTOToUser")
    Animal toEntity(AnimalDTO animalDTO);

    @Mapping(source = "animal.master", target = "master", qualifiedByName = "userToUserDTO")
    AnimalDTO toDto(Animal animal);

    List<Animal> toEntity(List<AnimalDTO> dtoList);

    List<AnimalDTO> toDto(List<Animal> entityList);
}
