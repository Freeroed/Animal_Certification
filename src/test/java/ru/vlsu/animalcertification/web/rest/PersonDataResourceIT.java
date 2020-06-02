package ru.vlsu.animalcertification.web.rest;

import ru.vlsu.animalcertification.AnimalCertificationApp;
import ru.vlsu.animalcertification.domain.PersonData;
import ru.vlsu.animalcertification.repository.PersonDataRepository;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PersonDataResource} REST controller.
 */
@SpringBootTest(classes = AnimalCertificationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PersonDataResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SURNAME = "AAAAAAAAAA";
    private static final String UPDATED_SURNAME = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_ENG = "AAAAAAAAAA";
    private static final String UPDATED_NAME_ENG = "BBBBBBBBBB";

    private static final String DEFAULT_SURNAME_ENG = "AAAAAAAAAA";
    private static final String UPDATED_SURNAME_ENG = "BBBBBBBBBB";

    private static final String DEFAULT_PATRONYMIC = "AAAAAAAAAA";
    private static final String UPDATED_PATRONYMIC = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_INN = "AAAAAAAAAA";
    private static final String UPDATED_INN = "BBBBBBBBBB";

    @Autowired
    private PersonDataRepository personDataRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPersonDataMockMvc;

    private PersonData personData;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PersonData createEntity(EntityManager em) {
        PersonData personData = new PersonData()
            .name(DEFAULT_NAME)
            .surname(DEFAULT_SURNAME)
            .nameEng(DEFAULT_NAME_ENG)
            .surnameEng(DEFAULT_SURNAME_ENG)
            .patronymic(DEFAULT_PATRONYMIC)
            .phone(DEFAULT_PHONE)
            .inn(DEFAULT_INN);
        return personData;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PersonData createUpdatedEntity(EntityManager em) {
        PersonData personData = new PersonData()
            .name(UPDATED_NAME)
            .surname(UPDATED_SURNAME)
            .nameEng(UPDATED_NAME_ENG)
            .surnameEng(UPDATED_SURNAME_ENG)
            .patronymic(UPDATED_PATRONYMIC)
            .phone(UPDATED_PHONE)
            .inn(UPDATED_INN);
        return personData;
    }

    @BeforeEach
    public void initTest() {
        personData = createEntity(em);
    }

    @Test
    @Transactional
    public void createPersonData() throws Exception {
        int databaseSizeBeforeCreate = personDataRepository.findAll().size();
        // Create the PersonData
        restPersonDataMockMvc.perform(post("/api/person-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personData)))
            .andExpect(status().isCreated());

        // Validate the PersonData in the database
        List<PersonData> personDataList = personDataRepository.findAll();
        assertThat(personDataList).hasSize(databaseSizeBeforeCreate + 1);
        PersonData testPersonData = personDataList.get(personDataList.size() - 1);
        assertThat(testPersonData.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPersonData.getSurname()).isEqualTo(DEFAULT_SURNAME);
        assertThat(testPersonData.getNameEng()).isEqualTo(DEFAULT_NAME_ENG);
        assertThat(testPersonData.getSurnameEng()).isEqualTo(DEFAULT_SURNAME_ENG);
        assertThat(testPersonData.getPatronymic()).isEqualTo(DEFAULT_PATRONYMIC);
        assertThat(testPersonData.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testPersonData.getInn()).isEqualTo(DEFAULT_INN);
    }

    @Test
    @Transactional
    public void createPersonDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = personDataRepository.findAll().size();

        // Create the PersonData with an existing ID
        personData.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersonDataMockMvc.perform(post("/api/person-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personData)))
            .andExpect(status().isBadRequest());

        // Validate the PersonData in the database
        List<PersonData> personDataList = personDataRepository.findAll();
        assertThat(personDataList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = personDataRepository.findAll().size();
        // set the field null
        personData.setName(null);

        // Create the PersonData, which fails.


        restPersonDataMockMvc.perform(post("/api/person-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personData)))
            .andExpect(status().isBadRequest());

        List<PersonData> personDataList = personDataRepository.findAll();
        assertThat(personDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSurnameIsRequired() throws Exception {
        int databaseSizeBeforeTest = personDataRepository.findAll().size();
        // set the field null
        personData.setSurname(null);

        // Create the PersonData, which fails.


        restPersonDataMockMvc.perform(post("/api/person-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personData)))
            .andExpect(status().isBadRequest());

        List<PersonData> personDataList = personDataRepository.findAll();
        assertThat(personDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameEngIsRequired() throws Exception {
        int databaseSizeBeforeTest = personDataRepository.findAll().size();
        // set the field null
        personData.setNameEng(null);

        // Create the PersonData, which fails.


        restPersonDataMockMvc.perform(post("/api/person-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personData)))
            .andExpect(status().isBadRequest());

        List<PersonData> personDataList = personDataRepository.findAll();
        assertThat(personDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSurnameEngIsRequired() throws Exception {
        int databaseSizeBeforeTest = personDataRepository.findAll().size();
        // set the field null
        personData.setSurnameEng(null);

        // Create the PersonData, which fails.


        restPersonDataMockMvc.perform(post("/api/person-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personData)))
            .andExpect(status().isBadRequest());

        List<PersonData> personDataList = personDataRepository.findAll();
        assertThat(personDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPatronymicIsRequired() throws Exception {
        int databaseSizeBeforeTest = personDataRepository.findAll().size();
        // set the field null
        personData.setPatronymic(null);

        // Create the PersonData, which fails.


        restPersonDataMockMvc.perform(post("/api/person-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personData)))
            .andExpect(status().isBadRequest());

        List<PersonData> personDataList = personDataRepository.findAll();
        assertThat(personDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPhoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = personDataRepository.findAll().size();
        // set the field null
        personData.setPhone(null);

        // Create the PersonData, which fails.


        restPersonDataMockMvc.perform(post("/api/person-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personData)))
            .andExpect(status().isBadRequest());

        List<PersonData> personDataList = personDataRepository.findAll();
        assertThat(personDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = personDataRepository.findAll().size();
        // set the field null

        // Create the PersonData, which fails.


        restPersonDataMockMvc.perform(post("/api/person-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personData)))
            .andExpect(status().isBadRequest());

        List<PersonData> personDataList = personDataRepository.findAll();
        assertThat(personDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInnIsRequired() throws Exception {
        int databaseSizeBeforeTest = personDataRepository.findAll().size();
        // set the field null
        personData.setInn(null);

        // Create the PersonData, which fails.


        restPersonDataMockMvc.perform(post("/api/person-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personData)))
            .andExpect(status().isBadRequest());

        List<PersonData> personDataList = personDataRepository.findAll();
        assertThat(personDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPersonData() throws Exception {
        // Initialize the database
        personDataRepository.saveAndFlush(personData);

        // Get all the personDataList
        restPersonDataMockMvc.perform(get("/api/person-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(personData.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].surname").value(hasItem(DEFAULT_SURNAME)))
            .andExpect(jsonPath("$.[*].nameEng").value(hasItem(DEFAULT_NAME_ENG)))
            .andExpect(jsonPath("$.[*].surnameEng").value(hasItem(DEFAULT_SURNAME_ENG)))
            .andExpect(jsonPath("$.[*].patronymic").value(hasItem(DEFAULT_PATRONYMIC)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].inn").value(hasItem(DEFAULT_INN)));
    }

    @Test
    @Transactional
    public void getPersonData() throws Exception {
        // Initialize the database
        personDataRepository.saveAndFlush(personData);

        // Get the personData
        restPersonDataMockMvc.perform(get("/api/person-data/{id}", personData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(personData.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.surname").value(DEFAULT_SURNAME))
            .andExpect(jsonPath("$.nameEng").value(DEFAULT_NAME_ENG))
            .andExpect(jsonPath("$.surnameEng").value(DEFAULT_SURNAME_ENG))
            .andExpect(jsonPath("$.patronymic").value(DEFAULT_PATRONYMIC))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.inn").value(DEFAULT_INN));
    }
    @Test
    @Transactional
    public void getNonExistingPersonData() throws Exception {
        // Get the personData
        restPersonDataMockMvc.perform(get("/api/person-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePersonData() throws Exception {
        // Initialize the database
        personDataRepository.saveAndFlush(personData);

        int databaseSizeBeforeUpdate = personDataRepository.findAll().size();

        // Update the personData
        PersonData updatedPersonData = personDataRepository.findById(personData.getId()).get();
        // Disconnect from session so that the updates on updatedPersonData are not directly saved in db
        em.detach(updatedPersonData);
        updatedPersonData
            .name(UPDATED_NAME)
            .surname(UPDATED_SURNAME)
            .nameEng(UPDATED_NAME_ENG)
            .surnameEng(UPDATED_SURNAME_ENG)
            .patronymic(UPDATED_PATRONYMIC)
            .phone(UPDATED_PHONE)
            .inn(UPDATED_INN);

        restPersonDataMockMvc.perform(put("/api/person-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPersonData)))
            .andExpect(status().isOk());

        // Validate the PersonData in the database
        List<PersonData> personDataList = personDataRepository.findAll();
        assertThat(personDataList).hasSize(databaseSizeBeforeUpdate);
        PersonData testPersonData = personDataList.get(personDataList.size() - 1);
        assertThat(testPersonData.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPersonData.getSurname()).isEqualTo(UPDATED_SURNAME);
        assertThat(testPersonData.getNameEng()).isEqualTo(UPDATED_NAME_ENG);
        assertThat(testPersonData.getSurnameEng()).isEqualTo(UPDATED_SURNAME_ENG);
        assertThat(testPersonData.getPatronymic()).isEqualTo(UPDATED_PATRONYMIC);
        assertThat(testPersonData.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testPersonData.getInn()).isEqualTo(UPDATED_INN);
    }

    @Test
    @Transactional
    public void updateNonExistingPersonData() throws Exception {
        int databaseSizeBeforeUpdate = personDataRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPersonDataMockMvc.perform(put("/api/person-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personData)))
            .andExpect(status().isBadRequest());

        // Validate the PersonData in the database
        List<PersonData> personDataList = personDataRepository.findAll();
        assertThat(personDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePersonData() throws Exception {
        // Initialize the database
        personDataRepository.saveAndFlush(personData);

        int databaseSizeBeforeDelete = personDataRepository.findAll().size();

        // Delete the personData
        restPersonDataMockMvc.perform(delete("/api/person-data/{id}", personData.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PersonData> personDataList = personDataRepository.findAll();
        assertThat(personDataList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
