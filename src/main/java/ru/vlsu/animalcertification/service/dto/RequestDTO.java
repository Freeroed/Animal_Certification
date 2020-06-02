package ru.vlsu.animalcertification.service.dto;

import ru.vlsu.animalcertification.domain.enumeration.RequestStatus;
import ru.vlsu.animalcertification.domain.enumeration.TransactionType;
import ru.vlsu.animalcertification.domain.enumeration.TransportType;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class RequestDTO implements Serializable {

    private Long id;

    private TransportType transportType;

    private String vehicleNumber;

    private TransactionType transactionType;

    private String storageWay;

    private String postalCode;

    private Instant departureAt;

    private Instant createdAt;

    private Instant lastModifiedAt;

    private RequestStatus status;

    private Set<DocumentDTO> documents = new HashSet<>();

    private UserDTO creater;

    private UserDTO veterinarian;

    private UserDTO rshInspector;

    private CountryDTO destinationCountry;

    private BorderCrossingPointDTO borderCrossingPoint;

    private Set<AnimalDTO> animals = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getStorageWay() {
        return storageWay;
    }

    public void setStorageWay(String storageWay) {
        this.storageWay = storageWay;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Instant getDepartureAt() {
        return departureAt;
    }

    public void setDepartureAt(Instant departureAt) {
        this.departureAt = departureAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(Instant lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public Set<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<DocumentDTO> documents) {
        this.documents = documents;
    }

    public UserDTO getCreater() {
        return creater;
    }

    public void setCreater(UserDTO creater) {
        this.creater = creater;
    }

    public UserDTO getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(UserDTO veterinarian) {
        this.veterinarian = veterinarian;
    }

    public UserDTO getRshInspector() {
        return rshInspector;
    }

    public void setRshInspector(UserDTO rshInspector) {
        this.rshInspector = rshInspector;
    }

    public CountryDTO getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(CountryDTO destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public BorderCrossingPointDTO getBorderCrossingPoint() {
        return borderCrossingPoint;
    }

    public void setBorderCrossingPoint(BorderCrossingPointDTO borderCrossingPoint) {
        this.borderCrossingPoint = borderCrossingPoint;
    }

    public Set<AnimalDTO> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<AnimalDTO> animals) {
        this.animals = animals;
    }

    public RequestDTO() {
    }

    @Override
    public String toString() {
        return "RequestDTO{" +
            "id=" + id +
            ", transportType=" + transportType +
            ", vehicleNumber='" + vehicleNumber + '\'' +
            ", transactionType=" + transactionType +
            ", storageWay='" + storageWay + '\'' +
            ", postalCode='" + postalCode + '\'' +
            ", departureAt=" + departureAt +
            ", createdAt=" + createdAt +
            ", lastModifiedAt=" + lastModifiedAt +
            ", status=" + status +
            ", documents=" + documents +
            ", creater=" + creater +
            ", veterinarian=" + veterinarian +
            ", rshInspector=" + rshInspector +
            ", destinationCountry=" + destinationCountry +
            ", borderCrossingPoint=" + borderCrossingPoint +
            ", animals=" + animals +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestDTO that = (RequestDTO) o;
        return Objects.equals(id, that.id) &&
            transportType == that.transportType &&
            Objects.equals(vehicleNumber, that.vehicleNumber) &&
            transactionType == that.transactionType &&
            Objects.equals(storageWay, that.storageWay) &&
            Objects.equals(postalCode, that.postalCode) &&
            Objects.equals(departureAt, that.departureAt) &&
            Objects.equals(createdAt, that.createdAt) &&
            Objects.equals(lastModifiedAt, that.lastModifiedAt) &&
            status == that.status &&
            Objects.equals(documents, that.documents) &&
            Objects.equals(creater, that.creater) &&
            Objects.equals(veterinarian, that.veterinarian) &&
            Objects.equals(rshInspector, that.rshInspector) &&
            Objects.equals(destinationCountry, that.destinationCountry) &&
            Objects.equals(borderCrossingPoint, that.borderCrossingPoint) &&
            Objects.equals(animals, that.animals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transportType, vehicleNumber, transactionType, storageWay, postalCode, departureAt, createdAt, lastModifiedAt, status, documents, creater, veterinarian, rshInspector, destinationCountry, borderCrossingPoint, animals);
    }
}
