package ru.vlsu.animalcertification.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import ru.vlsu.animalcertification.domain.enumeration.TransportType;

import ru.vlsu.animalcertification.domain.enumeration.TransactionType;

import ru.vlsu.animalcertification.domain.enumeration.RequestStatus;

/**
 * A Request.
 */
@Entity
@Table(name = "request")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "transport_type")
    private TransportType transportType;

    @Column(name = "vehicle_number")
    private String vehicleNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "storage_way")
    private String storageWay;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "departure_at")
    private Instant departureAt;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "last_modified_at")
    private Instant lastModifiedAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RequestStatus status;

    @OneToMany(mappedBy = "request")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Document> documents = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "requests", allowSetters = true)
    private User creater;

    @ManyToOne
    @JsonIgnoreProperties(value = "requests", allowSetters = true)
    private User veterinarian;

    @ManyToOne
    @JsonIgnoreProperties(value = "requests", allowSetters = true)
    private User rshInspector;

    @ManyToOne
    @JsonIgnoreProperties(value = "requests", allowSetters = true)
    private Country destinationCountry;

    @ManyToOne
    @JsonIgnoreProperties(value = "requests", allowSetters = true)
    private BorderCrossingPoint borderCrossingPoint;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "request_animals",
               joinColumns = @JoinColumn(name = "request_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "animals_id", referencedColumnName = "id"))
    private Set<Animal> animals = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public Request transportType(TransportType transportType) {
        this.transportType = transportType;
        return this;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public Request vehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
        return this;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public Request transactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getStorageWay() {
        return storageWay;
    }

    public Request storageWay(String storageWay) {
        this.storageWay = storageWay;
        return this;
    }

    public void setStorageWay(String storageWay) {
        this.storageWay = storageWay;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Request postalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Instant getDepartureAt() {
        return departureAt;
    }

    public Request departureAt(Instant departureAt) {
        this.departureAt = departureAt;
        return this;
    }

    public void setDepartureAt(Instant departureAt) {
        this.departureAt = departureAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Request createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getLastModifiedAt() {
        return lastModifiedAt;
    }

    public Request lastModifiedAt(Instant lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
        return this;
    }

    public void setLastModifiedAt(Instant lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public Request status(RequestStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public Request documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public Request addDocuments(Document document) {
        this.documents.add(document);
        document.setRequest(this);
        return this;
    }

    public Request removeDocuments(Document document) {
        this.documents.remove(document);
        document.setRequest(null);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public User getCreater() {
        return creater;
    }

    public Request creater(User user) {
        this.creater = user;
        return this;
    }

    public void setCreater(User user) {
        this.creater = user;
    }

    public User getVeterinarian() {
        return veterinarian;
    }

    public Request veterinarian(User user) {
        this.veterinarian = user;
        return this;
    }

    public void setVeterinarian(User user) {
        this.veterinarian = user;
    }

    public User getRshInspector() {
        return rshInspector;
    }

    public Request rshInspector(User user) {
        this.rshInspector = user;
        return this;
    }

    public void setRshInspector(User user) {
        this.rshInspector = user;
    }

    public Country getDestinationCountry() {
        return destinationCountry;
    }

    public Request destinationCountry(Country country) {
        this.destinationCountry = country;
        return this;
    }

    public void setDestinationCountry(Country country) {
        this.destinationCountry = country;
    }

    public BorderCrossingPoint getBorderCrossingPoint() {
        return borderCrossingPoint;
    }

    public Request borderCrossingPoint(BorderCrossingPoint borderCrossingPoint) {
        this.borderCrossingPoint = borderCrossingPoint;
        return this;
    }

    public void setBorderCrossingPoint(BorderCrossingPoint borderCrossingPoint) {
        this.borderCrossingPoint = borderCrossingPoint;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public Request animals(Set<Animal> animals) {
        this.animals = animals;
        return this;
    }

    public Request addAnimals(Animal animal) {
        this.animals.add(animal);
        animal.getRequests().add(this);
        return this;
    }

    public Request removeAnimals(Animal animal) {
        this.animals.remove(animal);
        animal.getRequests().remove(this);
        return this;
    }

    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Request)) {
            return false;
        }
        return id != null && id.equals(((Request) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Request{" +
            "id=" + getId() +
            ", transportType='" + getTransportType() + "'" +
            ", vehicleNumber='" + getVehicleNumber() + "'" +
            ", transactionType='" + getTransactionType() + "'" +
            ", storageWay='" + getStorageWay() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", departureAt='" + getDepartureAt() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", lastModifiedAt='" + getLastModifiedAt() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
