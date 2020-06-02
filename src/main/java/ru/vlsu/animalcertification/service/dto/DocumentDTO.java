package ru.vlsu.animalcertification.service.dto;

import ru.vlsu.animalcertification.domain.Request;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class DocumentDTO implements Serializable {

    private Long id;

    private Instant issuedAt;

    private String link;

    private String documentNumber;

    private DocumentTypeDTO type;

    private Request request;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Instant issuedAt) {
        this.issuedAt = issuedAt;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public DocumentTypeDTO getType() {
        return type;
    }

    public void setType(DocumentTypeDTO type) {
        this.type = type;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public DocumentDTO() {
    }

    @Override
    public String toString() {
        return "DocumentDTO{" +
            "id=" + id +
            ", issuedAt=" + issuedAt +
            ", link='" + link + '\'' +
            ", documentNumber='" + documentNumber + '\'' +
            ", type=" + type +
            ", request=" + request +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentDTO that = (DocumentDTO) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(issuedAt, that.issuedAt) &&
            Objects.equals(link, that.link) &&
            Objects.equals(documentNumber, that.documentNumber) &&
            Objects.equals(type, that.type) &&
            Objects.equals(request, that.request);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, issuedAt, link, documentNumber, type, request);
    }
}
