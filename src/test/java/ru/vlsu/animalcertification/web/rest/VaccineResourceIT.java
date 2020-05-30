package ru.vlsu.animalcertification.web.rest;

import ru.vlsu.animalcertification.AnimalCretificationApp;
import ru.vlsu.animalcertification.domain.Vaccine;
import ru.vlsu.animalcertification.repository.VaccineRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ru.vlsu.animalcertification.domain.enumeration.VaccineType;
/**
 * Integration tests for the {@link VaccineResource} REST controller.
 */
@SpringBootTest(classes = AnimalCretificationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class VaccineResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_BATCH_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_BATCH_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_AND_MANUFACTURER = "AAAAAAAAAA";
    private static final String UPDATED_NAME_AND_MANUFACTURER = "BBBBBBBBBB";

    private static final Instant DEFAULT_VALID_UTIL = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_VALID_UTIL = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final VaccineType DEFAULT_TYPE = VaccineType.TREATMENT;
    private static final VaccineType UPDATED_TYPE = VaccineType.IMMUNIZATION;

    @Autowired
    private VaccineRepository vaccineRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVaccineMockMvc;

    private Vaccine vaccine;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vaccine createEntity(EntityManager em) {
        Vaccine vaccine = new Vaccine()
            .title(DEFAULT_TITLE)
            .date(DEFAULT_DATE)
            .batchNumber(DEFAULT_BATCH_NUMBER)
            .nameAndManufacturer(DEFAULT_NAME_AND_MANUFACTURER)
            .validUtil(DEFAULT_VALID_UTIL)
            .type(DEFAULT_TYPE);
        return vaccine;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vaccine createUpdatedEntity(EntityManager em) {
        Vaccine vaccine = new Vaccine()
            .title(UPDATED_TITLE)
            .date(UPDATED_DATE)
            .batchNumber(UPDATED_BATCH_NUMBER)
            .nameAndManufacturer(UPDATED_NAME_AND_MANUFACTURER)
            .validUtil(UPDATED_VALID_UTIL)
            .type(UPDATED_TYPE);
        return vaccine;
    }

    @BeforeEach
    public void initTest() {
        vaccine = createEntity(em);
    }

    @Test
    @Transactional
    public void createVaccine() throws Exception {
        int databaseSizeBeforeCreate = vaccineRepository.findAll().size();
        // Create the Vaccine
        restVaccineMockMvc.perform(post("/api/vaccines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vaccine)))
            .andExpect(status().isCreated());

        // Validate the Vaccine in the database
        List<Vaccine> vaccineList = vaccineRepository.findAll();
        assertThat(vaccineList).hasSize(databaseSizeBeforeCreate + 1);
        Vaccine testVaccine = vaccineList.get(vaccineList.size() - 1);
        assertThat(testVaccine.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testVaccine.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testVaccine.getBatchNumber()).isEqualTo(DEFAULT_BATCH_NUMBER);
        assertThat(testVaccine.getNameAndManufacturer()).isEqualTo(DEFAULT_NAME_AND_MANUFACTURER);
        assertThat(testVaccine.getValidUtil()).isEqualTo(DEFAULT_VALID_UTIL);
        assertThat(testVaccine.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createVaccineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vaccineRepository.findAll().size();

        // Create the Vaccine with an existing ID
        vaccine.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVaccineMockMvc.perform(post("/api/vaccines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vaccine)))
            .andExpect(status().isBadRequest());

        // Validate the Vaccine in the database
        List<Vaccine> vaccineList = vaccineRepository.findAll();
        assertThat(vaccineList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = vaccineRepository.findAll().size();
        // set the field null
        vaccine.setTitle(null);

        // Create the Vaccine, which fails.


        restVaccineMockMvc.perform(post("/api/vaccines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vaccine)))
            .andExpect(status().isBadRequest());

        List<Vaccine> vaccineList = vaccineRepository.findAll();
        assertThat(vaccineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = vaccineRepository.findAll().size();
        // set the field null
        vaccine.setDate(null);

        // Create the Vaccine, which fails.


        restVaccineMockMvc.perform(post("/api/vaccines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vaccine)))
            .andExpect(status().isBadRequest());

        List<Vaccine> vaccineList = vaccineRepository.findAll();
        assertThat(vaccineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBatchNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = vaccineRepository.findAll().size();
        // set the field null
        vaccine.setBatchNumber(null);

        // Create the Vaccine, which fails.


        restVaccineMockMvc.perform(post("/api/vaccines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vaccine)))
            .andExpect(status().isBadRequest());

        List<Vaccine> vaccineList = vaccineRepository.findAll();
        assertThat(vaccineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameAndManufacturerIsRequired() throws Exception {
        int databaseSizeBeforeTest = vaccineRepository.findAll().size();
        // set the field null
        vaccine.setNameAndManufacturer(null);

        // Create the Vaccine, which fails.


        restVaccineMockMvc.perform(post("/api/vaccines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vaccine)))
            .andExpect(status().isBadRequest());

        List<Vaccine> vaccineList = vaccineRepository.findAll();
        assertThat(vaccineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValidUtilIsRequired() throws Exception {
        int databaseSizeBeforeTest = vaccineRepository.findAll().size();
        // set the field null
        vaccine.setValidUtil(null);

        // Create the Vaccine, which fails.


        restVaccineMockMvc.perform(post("/api/vaccines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vaccine)))
            .andExpect(status().isBadRequest());

        List<Vaccine> vaccineList = vaccineRepository.findAll();
        assertThat(vaccineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = vaccineRepository.findAll().size();
        // set the field null
        vaccine.setType(null);

        // Create the Vaccine, which fails.


        restVaccineMockMvc.perform(post("/api/vaccines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vaccine)))
            .andExpect(status().isBadRequest());

        List<Vaccine> vaccineList = vaccineRepository.findAll();
        assertThat(vaccineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVaccines() throws Exception {
        // Initialize the database
        vaccineRepository.saveAndFlush(vaccine);

        // Get all the vaccineList
        restVaccineMockMvc.perform(get("/api/vaccines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vaccine.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].batchNumber").value(hasItem(DEFAULT_BATCH_NUMBER)))
            .andExpect(jsonPath("$.[*].nameAndManufacturer").value(hasItem(DEFAULT_NAME_AND_MANUFACTURER)))
            .andExpect(jsonPath("$.[*].validUtil").value(hasItem(DEFAULT_VALID_UTIL.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getVaccine() throws Exception {
        // Initialize the database
        vaccineRepository.saveAndFlush(vaccine);

        // Get the vaccine
        restVaccineMockMvc.perform(get("/api/vaccines/{id}", vaccine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(vaccine.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.batchNumber").value(DEFAULT_BATCH_NUMBER))
            .andExpect(jsonPath("$.nameAndManufacturer").value(DEFAULT_NAME_AND_MANUFACTURER))
            .andExpect(jsonPath("$.validUtil").value(DEFAULT_VALID_UTIL.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingVaccine() throws Exception {
        // Get the vaccine
        restVaccineMockMvc.perform(get("/api/vaccines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVaccine() throws Exception {
        // Initialize the database
        vaccineRepository.saveAndFlush(vaccine);

        int databaseSizeBeforeUpdate = vaccineRepository.findAll().size();

        // Update the vaccine
        Vaccine updatedVaccine = vaccineRepository.findById(vaccine.getId()).get();
        // Disconnect from session so that the updates on updatedVaccine are not directly saved in db
        em.detach(updatedVaccine);
        updatedVaccine
            .title(UPDATED_TITLE)
            .date(UPDATED_DATE)
            .batchNumber(UPDATED_BATCH_NUMBER)
            .nameAndManufacturer(UPDATED_NAME_AND_MANUFACTURER)
            .validUtil(UPDATED_VALID_UTIL)
            .type(UPDATED_TYPE);

        restVaccineMockMvc.perform(put("/api/vaccines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedVaccine)))
            .andExpect(status().isOk());

        // Validate the Vaccine in the database
        List<Vaccine> vaccineList = vaccineRepository.findAll();
        assertThat(vaccineList).hasSize(databaseSizeBeforeUpdate);
        Vaccine testVaccine = vaccineList.get(vaccineList.size() - 1);
        assertThat(testVaccine.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testVaccine.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testVaccine.getBatchNumber()).isEqualTo(UPDATED_BATCH_NUMBER);
        assertThat(testVaccine.getNameAndManufacturer()).isEqualTo(UPDATED_NAME_AND_MANUFACTURER);
        assertThat(testVaccine.getValidUtil()).isEqualTo(UPDATED_VALID_UTIL);
        assertThat(testVaccine.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingVaccine() throws Exception {
        int databaseSizeBeforeUpdate = vaccineRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVaccineMockMvc.perform(put("/api/vaccines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vaccine)))
            .andExpect(status().isBadRequest());

        // Validate the Vaccine in the database
        List<Vaccine> vaccineList = vaccineRepository.findAll();
        assertThat(vaccineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVaccine() throws Exception {
        // Initialize the database
        vaccineRepository.saveAndFlush(vaccine);

        int databaseSizeBeforeDelete = vaccineRepository.findAll().size();

        // Delete the vaccine
        restVaccineMockMvc.perform(delete("/api/vaccines/{id}", vaccine.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Vaccine> vaccineList = vaccineRepository.findAll();
        assertThat(vaccineList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
