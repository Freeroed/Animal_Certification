package ru.vlsu.animalcertification.web.rest;

import ru.vlsu.animalcertification.AnimalCertificationApp;
import ru.vlsu.animalcertification.domain.PersonalData;
import ru.vlsu.animalcertification.repository.PersonalDataRepository;

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
public class PersonalDataResourceIT {

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

    private static final String DEFAULT_INN = "AAAAAAAAAA";
    private static final String UPDATED_INN = "BBBBBBBBBB";

    @Autowired
    private PersonalDataRepository personalDataRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPersonDataMockMvc;

    private PersonalData personalData;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PersonalData createEntity(EntityManager em) {
        PersonalData personalData = new PersonalData()
            .name(DEFAULT_NAME)
            .surname(DEFAULT_SURNAME)
            .nameEng(DEFAULT_NAME_ENG)
            .surnameEng(DEFAULT_SURNAME_ENG)
            .patronymic(DEFAULT_PATRONYMIC)
            .phone(DEFAULT_PHONE)
            .inn(DEFAULT_INN);
        return personalData;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PersonalData createUpdatedEntity(EntityManager em) {
        PersonalData personalData = new PersonalData()
            .name(UPDATED_NAME)
            .surname(UPDATED_SURNAME)
            .nameEng(UPDATED_NAME_ENG)
            .surnameEng(UPDATED_SURNAME_ENG)
            .patronymic(UPDATED_PATRONYMIC)
            .phone(UPDATED_PHONE)
            .inn(UPDATED_INN);
        return personalData;
    }

    @BeforeEach
    public void initTest() {
        personalData = createEntity(em);
    }

    @Test
    @Transactional
    public void createPersonData() throws Exception {
        int databaseSizeBeforeCreate = personalDataRepository.findAll().size();
        // Create the PersonalData
        restPersonDataMockMvc.perform(post("/api/person-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personalData)))
            .andExpect(status().isCreated());

        // Validate the PersonalData in the database
        List<PersonalData> personalDataList = personalDataRepository.findAll();
        assertThat(personalDataList).hasSize(databaseSizeBeforeCreate + 1);
        PersonalData testPersonalData = personalDataList.get(personalDataList.size() - 1);
        assertThat(testPersonalData.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPersonalData.getSurname()).isEqualTo(DEFAULT_SURNAME);
        assertThat(testPersonalData.getNameEng()).isEqualTo(DEFAULT_NAME_ENG);
        assertThat(testPersonalData.getSurnameEng()).isEqualTo(DEFAULT_SURNAME_ENG);
        assertThat(testPersonalData.getPatronymic()).isEqualTo(DEFAULT_PATRONYMIC);
        assertThat(testPersonalData.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testPersonalData.getInn()).isEqualTo(DEFAULT_INN);
    }

    @Test
    @Transactional
    public void createPersonDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = personalDataRepository.findAll().size();

        // Create the PersonalData with an existing ID
        personalData.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersonDataMockMvc.perform(post("/api/person-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personalData)))
            .andExpect(status().isBadRequest());

        // Validate the PersonalData in the database
        List<PersonalData> personalDataList = personalDataRepository.findAll();
        assertThat(personalDataList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = personalDataRepository.findAll().size();
        // set the field null
        personalData.setName(null);

        // Create the PersonalData, which fails.


        restPersonDataMockMvc.perform(post("/api/person-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personalData)))
            .andExpect(status().isBadRequest());

        List<PersonalData> personalDataList = personalDataRepository.findAll();
        assertThat(personalDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSurnameIsRequired() throws Exception {
        int databaseSizeBeforeTest = personalDataRepository.findAll().size();
        // set the field null
        personalData.setSurname(null);

        // Create the PersonalData, which fails.


        restPersonDataMockMvc.perform(post("/api/person-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personalData)))
            .andExpect(status().isBadRequest());

        List<PersonalData> personalDataList = personalDataRepository.findAll();
        assertThat(personalDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPersonData() throws Exception {
        // Initialize the database
        personalDataRepository.saveAndFlush(personalData);

        // Get all the personDataList
        restPersonDataMockMvc.perform(get("/api/person-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(personalData.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].surname").value(hasItem(DEFAULT_SURNAME)))
            .andExpect(jsonPath("$.[*].nameEng").value(hasItem(DEFAULT_NAME_ENG)))
            .andExpect(jsonPath("$.[*].surnameEng").value(hasItem(DEFAULT_SURNAME_ENG)))
            .andExpect(jsonPath("$.[*].patronymic").value(hasItem(DEFAULT_PATRONYMIC)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].inn").value(hasItem(DEFAULT_INN)));
    }

    @Test
    @Transactional
    public void getPersonData() throws Exception {
        // Initialize the database
        personalDataRepository.saveAndFlush(personalData);

        // Get the personalData
        restPersonDataMockMvc.perform(get("/api/person-data/{id}", personalData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(personalData.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.surname").value(DEFAULT_SURNAME))
            .andExpect(jsonPath("$.nameEng").value(DEFAULT_NAME_ENG))
            .andExpect(jsonPath("$.surnameEng").value(DEFAULT_SURNAME_ENG))
            .andExpect(jsonPath("$.patronymic").value(DEFAULT_PATRONYMIC))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.inn").value(DEFAULT_INN));
    }
    @Test
    @Transactional
    public void getNonExistingPersonData() throws Exception {
        // Get the personalData
        restPersonDataMockMvc.perform(get("/api/person-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePersonData() throws Exception {
        // Initialize the database
        personalDataRepository.saveAndFlush(personalData);

        int databaseSizeBeforeUpdate = personalDataRepository.findAll().size();

        // Update the personalData
        PersonalData updatedPersonalData = personalDataRepository.findById(personalData.getId()).get();
        // Disconnect from session so that the updates on updatedPersonalData are not directly saved in db
        em.detach(updatedPersonalData);
        updatedPersonalData
            .name(UPDATED_NAME)
            .surname(UPDATED_SURNAME)
            .nameEng(UPDATED_NAME_ENG)
            .surnameEng(UPDATED_SURNAME_ENG)
            .patronymic(UPDATED_PATRONYMIC)
            .phone(UPDATED_PHONE)
            .inn(UPDATED_INN);

        restPersonDataMockMvc.perform(put("/api/person-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPersonalData)))
            .andExpect(status().isOk());

        // Validate the PersonalData in the database
        List<PersonalData> personalDataList = personalDataRepository.findAll();
        assertThat(personalDataList).hasSize(databaseSizeBeforeUpdate);
        PersonalData testPersonalData = personalDataList.get(personalDataList.size() - 1);
        assertThat(testPersonalData.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPersonalData.getSurname()).isEqualTo(UPDATED_SURNAME);
        assertThat(testPersonalData.getNameEng()).isEqualTo(UPDATED_NAME_ENG);
        assertThat(testPersonalData.getSurnameEng()).isEqualTo(UPDATED_SURNAME_ENG);
        assertThat(testPersonalData.getPatronymic()).isEqualTo(UPDATED_PATRONYMIC);
        assertThat(testPersonalData.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testPersonalData.getInn()).isEqualTo(UPDATED_INN);
    }

    @Test
    @Transactional
    public void updateNonExistingPersonData() throws Exception {
        int databaseSizeBeforeUpdate = personalDataRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPersonDataMockMvc.perform(put("/api/person-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personalData)))
            .andExpect(status().isBadRequest());

        // Validate the PersonalData in the database
        List<PersonalData> personalDataList = personalDataRepository.findAll();
        assertThat(personalDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePersonData() throws Exception {
        // Initialize the database
        personalDataRepository.saveAndFlush(personalData);

        int databaseSizeBeforeDelete = personalDataRepository.findAll().size();

        // Delete the personalData
        restPersonDataMockMvc.perform(delete("/api/person-data/{id}", personalData.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PersonalData> personalDataList = personalDataRepository.findAll();
        assertThat(personalDataList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
