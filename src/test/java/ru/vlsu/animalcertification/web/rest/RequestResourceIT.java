package ru.vlsu.animalcertification.web.rest;

import ru.vlsu.animalcertification.AnimalCertificationApp;
import ru.vlsu.animalcertification.domain.Request;
import ru.vlsu.animalcertification.repository.RequestRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ru.vlsu.animalcertification.domain.enumeration.TransportType;
import ru.vlsu.animalcertification.domain.enumeration.TransactionType;
import ru.vlsu.animalcertification.domain.enumeration.RequestStatus;
/**
 * Integration tests for the {@link RequestResource} REST controller.
 */
@SpringBootTest(classes = AnimalCertificationApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class RequestResourceIT {

    private static final TransportType DEFAULT_TRANSPORT_TYPE = TransportType.CAR;
    private static final TransportType UPDATED_TRANSPORT_TYPE = TransportType.TRAIN;

    private static final String DEFAULT_VEHICLE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_VEHICLE_NUMBER = "BBBBBBBBBB";

    private static final TransactionType DEFAULT_TRANSACTION_TYPE = TransactionType.TRAVEL;
    private static final TransactionType UPDATED_TRANSACTION_TYPE = TransactionType.TRAVEL;

    private static final String DEFAULT_STORAGE_WAY = "AAAAAAAAAA";
    private static final String UPDATED_STORAGE_WAY = "BBBBBBBBBB";

    private static final String DEFAULT_POSTAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTAL_CODE = "BBBBBBBBBB";

    private static final Instant DEFAULT_DEPARTURE_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DEPARTURE_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_LAST_MODIFIED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final RequestStatus DEFAULT_STATUS = RequestStatus.CREATED;
    private static final RequestStatus UPDATED_STATUS = RequestStatus.PREPARED;

    @Autowired
    private RequestRepository requestRepository;

    @Mock
    private RequestRepository requestRepositoryMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRequestMockMvc;

    private Request request;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Request createEntity(EntityManager em) {
        Request request = new Request()
            .transportType(DEFAULT_TRANSPORT_TYPE)
            .vehicleNumber(DEFAULT_VEHICLE_NUMBER)
            .transactionType(DEFAULT_TRANSACTION_TYPE)
            .storageWay(DEFAULT_STORAGE_WAY)
            .postalCode(DEFAULT_POSTAL_CODE)
            .departureAt(DEFAULT_DEPARTURE_AT)
            .createdAt(DEFAULT_CREATED_AT)
            .lastModifiedAt(DEFAULT_LAST_MODIFIED_AT)
            .status(DEFAULT_STATUS);
        return request;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Request createUpdatedEntity(EntityManager em) {
        Request request = new Request()
            .transportType(UPDATED_TRANSPORT_TYPE)
            .vehicleNumber(UPDATED_VEHICLE_NUMBER)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .storageWay(UPDATED_STORAGE_WAY)
            .postalCode(UPDATED_POSTAL_CODE)
            .departureAt(UPDATED_DEPARTURE_AT)
            .createdAt(UPDATED_CREATED_AT)
            .lastModifiedAt(UPDATED_LAST_MODIFIED_AT)
            .status(UPDATED_STATUS);
        return request;
    }

    @BeforeEach
    public void initTest() {
        request = createEntity(em);
    }

    @Test
    @Transactional
    public void createRequest() throws Exception {
        int databaseSizeBeforeCreate = requestRepository.findAll().size();
        // Create the Request
        restRequestMockMvc.perform(post("/api/requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(request)))
            .andExpect(status().isCreated());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeCreate + 1);
        Request testRequest = requestList.get(requestList.size() - 1);
        assertThat(testRequest.getTransportType()).isEqualTo(DEFAULT_TRANSPORT_TYPE);
        assertThat(testRequest.getVehicleNumber()).isEqualTo(DEFAULT_VEHICLE_NUMBER);
        assertThat(testRequest.getTransactionType()).isEqualTo(DEFAULT_TRANSACTION_TYPE);
        assertThat(testRequest.getStorageWay()).isEqualTo(DEFAULT_STORAGE_WAY);
        assertThat(testRequest.getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);
        assertThat(testRequest.getDepartureAt()).isEqualTo(DEFAULT_DEPARTURE_AT);
        assertThat(testRequest.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testRequest.getLastModifiedAt()).isEqualTo(DEFAULT_LAST_MODIFIED_AT);
        assertThat(testRequest.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createRequestWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = requestRepository.findAll().size();

        // Create the Request with an existing ID
        request.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRequestMockMvc.perform(post("/api/requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(request)))
            .andExpect(status().isBadRequest());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = requestRepository.findAll().size();
        // set the field null
        request.setCreatedAt(null);

        // Create the Request, which fails.


        restRequestMockMvc.perform(post("/api/requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(request)))
            .andExpect(status().isBadRequest());

        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = requestRepository.findAll().size();
        // set the field null
        request.setStatus(null);

        // Create the Request, which fails.


        restRequestMockMvc.perform(post("/api/requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(request)))
            .andExpect(status().isBadRequest());

        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRequests() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList
        restRequestMockMvc.perform(get("/api/requests?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(request.getId().intValue())))
            .andExpect(jsonPath("$.[*].transportType").value(hasItem(DEFAULT_TRANSPORT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].vehicleNumber").value(hasItem(DEFAULT_VEHICLE_NUMBER)))
            .andExpect(jsonPath("$.[*].transactionType").value(hasItem(DEFAULT_TRANSACTION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].storageWay").value(hasItem(DEFAULT_STORAGE_WAY)))
            .andExpect(jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE)))
            .andExpect(jsonPath("$.[*].departureAt").value(hasItem(DEFAULT_DEPARTURE_AT.toString())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedAt").value(hasItem(DEFAULT_LAST_MODIFIED_AT.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllRequestsWithEagerRelationshipsIsEnabled() throws Exception {
        when(requestRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restRequestMockMvc.perform(get("/api/requests?eagerload=true"))
            .andExpect(status().isOk());

        verify(requestRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllRequestsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(requestRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restRequestMockMvc.perform(get("/api/requests?eagerload=true"))
            .andExpect(status().isOk());

        verify(requestRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getRequest() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get the request
        restRequestMockMvc.perform(get("/api/requests/{id}", request.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(request.getId().intValue()))
            .andExpect(jsonPath("$.transportType").value(DEFAULT_TRANSPORT_TYPE.toString()))
            .andExpect(jsonPath("$.vehicleNumber").value(DEFAULT_VEHICLE_NUMBER))
            .andExpect(jsonPath("$.transactionType").value(DEFAULT_TRANSACTION_TYPE.toString()))
            .andExpect(jsonPath("$.storageWay").value(DEFAULT_STORAGE_WAY))
            .andExpect(jsonPath("$.postalCode").value(DEFAULT_POSTAL_CODE))
            .andExpect(jsonPath("$.departureAt").value(DEFAULT_DEPARTURE_AT.toString()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.lastModifiedAt").value(DEFAULT_LAST_MODIFIED_AT.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingRequest() throws Exception {
        // Get the request
        restRequestMockMvc.perform(get("/api/requests/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRequest() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        int databaseSizeBeforeUpdate = requestRepository.findAll().size();

        // Update the request
        Request updatedRequest = requestRepository.findById(request.getId()).get();
        // Disconnect from session so that the updates on updatedRequest are not directly saved in db
        em.detach(updatedRequest);
        updatedRequest
            .transportType(UPDATED_TRANSPORT_TYPE)
            .vehicleNumber(UPDATED_VEHICLE_NUMBER)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .storageWay(UPDATED_STORAGE_WAY)
            .postalCode(UPDATED_POSTAL_CODE)
            .departureAt(UPDATED_DEPARTURE_AT)
            .createdAt(UPDATED_CREATED_AT)
            .lastModifiedAt(UPDATED_LAST_MODIFIED_AT)
            .status(UPDATED_STATUS);

        restRequestMockMvc.perform(put("/api/requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedRequest)))
            .andExpect(status().isOk());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
        Request testRequest = requestList.get(requestList.size() - 1);
        assertThat(testRequest.getTransportType()).isEqualTo(UPDATED_TRANSPORT_TYPE);
        assertThat(testRequest.getVehicleNumber()).isEqualTo(UPDATED_VEHICLE_NUMBER);
        assertThat(testRequest.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
        assertThat(testRequest.getStorageWay()).isEqualTo(UPDATED_STORAGE_WAY);
        assertThat(testRequest.getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testRequest.getDepartureAt()).isEqualTo(UPDATED_DEPARTURE_AT);
        assertThat(testRequest.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testRequest.getLastModifiedAt()).isEqualTo(UPDATED_LAST_MODIFIED_AT);
        assertThat(testRequest.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingRequest() throws Exception {
        int databaseSizeBeforeUpdate = requestRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRequestMockMvc.perform(put("/api/requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(request)))
            .andExpect(status().isBadRequest());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRequest() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        int databaseSizeBeforeDelete = requestRepository.findAll().size();

        // Delete the request
        restRequestMockMvc.perform(delete("/api/requests/{id}", request.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
