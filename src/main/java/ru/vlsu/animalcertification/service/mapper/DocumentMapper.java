package ru.vlsu.animalcertification.service.mapper;

import org.mapstruct.Mapper;
import ru.vlsu.animalcertification.domain.Document;
import ru.vlsu.animalcertification.service.dto.DocumentDTO;

@Mapper(componentModel = "spring", uses = {})
public interface DocumentMapper extends EntityMapper<DocumentDTO, Document> {

    default Document fromId(Long id) {
        if (id == null) {
            return null;
        }
        Document document = new Document();
        document.setId(id);
        return document;
    }
}
