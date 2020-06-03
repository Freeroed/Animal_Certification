package ru.vlsu.animalcertification.service.mapper;

import org.mapstruct.Mapper;
import ru.vlsu.animalcertification.domain.DocumentType;
import ru.vlsu.animalcertification.service.dto.DocumentTypeDTO;

@Mapper(componentModel = "spring", uses = {})
public interface DocumentTypeMapper extends EntityMapper<DocumentTypeDTO, DocumentType> {

    default DocumentType fromId(Long id) {
        if (id == null) {
            return null;
        }
        DocumentType documentType = new DocumentType();
        documentType.setId(id);
        return documentType;
    }
}
