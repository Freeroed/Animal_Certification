package ru.vlsu.animalcertification.web.rest;

import ru.vlsu.animalcertification.AnimalCertificationApp;
import ru.vlsu.animalcertification.domain.AnimalType;
import ru.vlsu.animalcertification.repository.AnimalTypeRepository;

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
 * Integration tests for the {@link AnimalTypeResource} REST controller.
 */
@SpringBootTest(classes = AnimalCertificationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AnimalTypeResourceIT {

    private static final String DEFAULT_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_NAME_ENG = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_NAME_ENG = "BBBBBBBBBB";

    private static final String DEFAULT_SCIENTIFIC_NAME_ENG = "AAAAAAAAAA";
    private static final String UPDATED_SCIENTIFIC_NAME_ENG = "BBBBBBBBBB";

    @Autowired
    private AnimalTypeRepository animalTypeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAnimalTypeMockMvc;

    private AnimalType animalType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnimalType createEntity(EntityManager em) {
        AnimalType animalType = new AnimalType()
            .typeName(DEFAULT_TYPE_NAME)
            .typeNameEng(DEFAULT_TYPE_NAME_ENG)
            .scientificNameENG(DEFAULT_SCIENTIFIC_NAME_ENG);
        return animalType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnimalType createUpdatedEntity(EntityManager em) {
        AnimalType animalType = new AnimalType()
            .typeName(UPDATED_TYPE_NAME)
            .typeNameEng(UPDATED_TYPE_NAME_ENG)
            .scientificNameENG(UPDATED_SCIENTIFIC_NAME_ENG);
        return animalType;
    }

    @BeforeEach
    public void initTest() {
        animalType = createEntity(em);
    }

    @Test
    @Transactional
    public void createAnimalType() throws Exception {
        int databaseSizeBeforeCreate = animalTypeRepository.findAll().size();
        // Create the AnimalType
        restAnimalTypeMockMvc.perform(post("/api/animal-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(animalType)))
            .andExpect(status().isCreated());

        // Validate the AnimalType in the database
        List<AnimalType> animalTypeList = animalTypeRepository.findAll();
        assertThat(animalTypeList).hasSize(databaseSizeBeforeCreate + 1);
        AnimalType testAnimalType = animalTypeList.get(animalTypeList.size() - 1);
        assertThat(testAnimalType.getTypeName()).isEqualTo(DEFAULT_TYPE_NAME);
        assertThat(testAnimalType.getTypeNameEng()).isEqualTo(DEFAULT_TYPE_NAME_ENG);
        assertThat(testAnimalType.getScientificNameENG()).isEqualTo(DEFAULT_SCIENTIFIC_NAME_ENG);
    }

    @Test
    @Transactional
    public void createAnimalTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = animalTypeRepository.findAll().size();

        // Create the AnimalType with an existing ID
        animalType.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAnimalTypeMockMvc.perform(post("/api/animal-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(animalType)))
            .andExpect(status().isBadRequest());

        // Validate the AnimalType in the database
        List<AnimalType> animalTypeList = animalTypeRepository.findAll();
        assertThat(animalTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTypeNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = animalTypeRepository.findAll().size();
        // set the field null
        animalType.setTypeName(null);

        // Create the AnimalType, which fails.


        restAnimalTypeMockMvc.perform(post("/api/animal-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(animalType)))
            .andExpect(status().isBadRequest());

        List<AnimalType> animalTypeList = animalTypeRepository.findAll();
        assertThat(animalTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeNameEngIsRequired() throws Exception {
        int databaseSizeBeforeTest = animalTypeRepository.findAll().size();
        // set the field null
        animalType.setTypeNameEng(null);

        // Create the AnimalType, which fails.


        restAnimalTypeMockMvc.perform(post("/api/animal-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(animalType)))
            .andExpect(status().isBadRequest());

        List<AnimalType> animalTypeList = animalTypeRepository.findAll();
        assertThat(animalTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkScientificNameENGIsRequired() throws Exception {
        int databaseSizeBeforeTest = animalTypeRepository.findAll().size();
        // set the field null
        animalType.setScientificNameENG(null);

        // Create the AnimalType, which fails.


        restAnimalTypeMockMvc.perform(post("/api/animal-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(animalType)))
            .andExpect(status().isBadRequest());

        List<AnimalType> animalTypeList = animalTypeRepository.findAll();
        assertThat(animalTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAnimalTypes() throws Exception {
        // Initialize the database
        animalTypeRepository.saveAndFlush(animalType);

        // Get all the animalTypeList
        restAnimalTypeMockMvc.perform(get("/api/animal-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(animalType.getId().intValue())))
            .andExpect(jsonPath("$.[*].typeName").value(hasItem(DEFAULT_TYPE_NAME)))
            .andExpect(jsonPath("$.[*].typeNameEng").value(hasItem(DEFAULT_TYPE_NAME_ENG)))
            .andExpect(jsonPath("$.[*].scientificNameENG").value(hasItem(DEFAULT_SCIENTIFIC_NAME_ENG)));
    }
    
    @Test
    @Transactional
    public void getAnimalType() throws Exception {
        // Initialize the database
        animalTypeRepository.saveAndFlush(animalType);

        // Get the animalType
        restAnimalTypeMockMvc.perform(get("/api/animal-types/{id}", animalType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(animalType.getId().intValue()))
            .andExpect(jsonPath("$.typeName").value(DEFAULT_TYPE_NAME))
            .andExpect(jsonPath("$.typeNameEng").value(DEFAULT_TYPE_NAME_ENG))
            .andExpect(jsonPath("$.scientificNameENG").value(DEFAULT_SCIENTIFIC_NAME_ENG));
    }
    @Test
    @Transactional
    public void getNonExistingAnimalType() throws Exception {
        // Get the animalType
        restAnimalTypeMockMvc.perform(get("/api/animal-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAnimalType() throws Exception {
        // Initialize the database
        animalTypeRepository.saveAndFlush(animalType);

        int databaseSizeBeforeUpdate = animalTypeRepository.findAll().size();

        // Update the animalType
        AnimalType updatedAnimalType = animalTypeRepository.findById(animalType.getId()).get();
        // Disconnect from session so that the updates on updatedAnimalType are not directly saved in db
        em.detach(updatedAnimalType);
        updatedAnimalType
            .typeName(UPDATED_TYPE_NAME)
            .typeNameEng(UPDATED_TYPE_NAME_ENG)
            .scientificNameENG(UPDATED_SCIENTIFIC_NAME_ENG);

        restAnimalTypeMockMvc.perform(put("/api/animal-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAnimalType)))
            .andExpect(status().isOk());

        // Validate the AnimalType in the database
        List<AnimalType> animalTypeList = animalTypeRepository.findAll();
        assertThat(animalTypeList).hasSize(databaseSizeBeforeUpdate);
        AnimalType testAnimalType = animalTypeList.get(animalTypeList.size() - 1);
        assertThat(testAnimalType.getTypeName()).isEqualTo(UPDATED_TYPE_NAME);
        assertThat(testAnimalType.getTypeNameEng()).isEqualTo(UPDATED_TYPE_NAME_ENG);
        assertThat(testAnimalType.getScientificNameENG()).isEqualTo(UPDATED_SCIENTIFIC_NAME_ENG);
    }

    @Test
    @Transactional
    public void updateNonExistingAnimalType() throws Exception {
        int databaseSizeBeforeUpdate = animalTypeRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnimalTypeMockMvc.perform(put("/api/animal-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(animalType)))
            .andExpect(status().isBadRequest());

        // Validate the AnimalType in the database
        List<AnimalType> animalTypeList = animalTypeRepository.findAll();
        assertThat(animalTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAnimalType() throws Exception {
        // Initialize the database
        animalTypeRepository.saveAndFlush(animalType);

        int databaseSizeBeforeDelete = animalTypeRepository.findAll().size();

        // Delete the animalType
        restAnimalTypeMockMvc.perform(delete("/api/animal-types/{id}", animalType.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AnimalType> animalTypeList = animalTypeRepository.findAll();
        assertThat(animalTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
