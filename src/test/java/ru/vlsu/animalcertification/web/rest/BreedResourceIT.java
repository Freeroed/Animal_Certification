package ru.vlsu.animalcertification.web.rest;

import ru.vlsu.animalcertification.AnimalCertificationApp;
import ru.vlsu.animalcertification.domain.Breed;
import ru.vlsu.animalcertification.repository.BreedRepository;

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
 * Integration tests for the {@link BreedResource} REST controller.
 */
@SpringBootTest(classes = AnimalCertificationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BreedResourceIT {

    private static final String DEFAULT_BREED_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BREED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BREED_NAME_ENG = "AAAAAAAAAA";
    private static final String UPDATED_BREED_NAME_ENG = "BBBBBBBBBB";

    @Autowired
    private BreedRepository breedRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBreedMockMvc;

    private Breed breed;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Breed createEntity(EntityManager em) {
        Breed breed = new Breed()
            .breedName(DEFAULT_BREED_NAME)
            .breedNameEng(DEFAULT_BREED_NAME_ENG);
        return breed;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Breed createUpdatedEntity(EntityManager em) {
        Breed breed = new Breed()
            .breedName(UPDATED_BREED_NAME)
            .breedNameEng(UPDATED_BREED_NAME_ENG);
        return breed;
    }

    @BeforeEach
    public void initTest() {
        breed = createEntity(em);
    }

    @Test
    @Transactional
    public void createBreed() throws Exception {
        int databaseSizeBeforeCreate = breedRepository.findAll().size();
        // Create the Breed
        restBreedMockMvc.perform(post("/api/breeds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(breed)))
            .andExpect(status().isCreated());

        // Validate the Breed in the database
        List<Breed> breedList = breedRepository.findAll();
        assertThat(breedList).hasSize(databaseSizeBeforeCreate + 1);
        Breed testBreed = breedList.get(breedList.size() - 1);
        assertThat(testBreed.getBreedName()).isEqualTo(DEFAULT_BREED_NAME);
        assertThat(testBreed.getBreedNameEng()).isEqualTo(DEFAULT_BREED_NAME_ENG);
    }

    @Test
    @Transactional
    public void createBreedWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = breedRepository.findAll().size();

        // Create the Breed with an existing ID
        breed.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBreedMockMvc.perform(post("/api/breeds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(breed)))
            .andExpect(status().isBadRequest());

        // Validate the Breed in the database
        List<Breed> breedList = breedRepository.findAll();
        assertThat(breedList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkBreedNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = breedRepository.findAll().size();
        // set the field null
        breed.setBreedName(null);

        // Create the Breed, which fails.


        restBreedMockMvc.perform(post("/api/breeds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(breed)))
            .andExpect(status().isBadRequest());

        List<Breed> breedList = breedRepository.findAll();
        assertThat(breedList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBreedNameEngIsRequired() throws Exception {
        int databaseSizeBeforeTest = breedRepository.findAll().size();
        // set the field null
        breed.setBreedNameEng(null);

        // Create the Breed, which fails.


        restBreedMockMvc.perform(post("/api/breeds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(breed)))
            .andExpect(status().isBadRequest());

        List<Breed> breedList = breedRepository.findAll();
        assertThat(breedList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBreeds() throws Exception {
        // Initialize the database
        breedRepository.saveAndFlush(breed);

        // Get all the breedList
        restBreedMockMvc.perform(get("/api/breeds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(breed.getId().intValue())))
            .andExpect(jsonPath("$.[*].breedName").value(hasItem(DEFAULT_BREED_NAME)))
            .andExpect(jsonPath("$.[*].breedNameEng").value(hasItem(DEFAULT_BREED_NAME_ENG)));
    }
    
    @Test
    @Transactional
    public void getBreed() throws Exception {
        // Initialize the database
        breedRepository.saveAndFlush(breed);

        // Get the breed
        restBreedMockMvc.perform(get("/api/breeds/{id}", breed.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(breed.getId().intValue()))
            .andExpect(jsonPath("$.breedName").value(DEFAULT_BREED_NAME))
            .andExpect(jsonPath("$.breedNameEng").value(DEFAULT_BREED_NAME_ENG));
    }
    @Test
    @Transactional
    public void getNonExistingBreed() throws Exception {
        // Get the breed
        restBreedMockMvc.perform(get("/api/breeds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBreed() throws Exception {
        // Initialize the database
        breedRepository.saveAndFlush(breed);

        int databaseSizeBeforeUpdate = breedRepository.findAll().size();

        // Update the breed
        Breed updatedBreed = breedRepository.findById(breed.getId()).get();
        // Disconnect from session so that the updates on updatedBreed are not directly saved in db
        em.detach(updatedBreed);
        updatedBreed
            .breedName(UPDATED_BREED_NAME)
            .breedNameEng(UPDATED_BREED_NAME_ENG);

        restBreedMockMvc.perform(put("/api/breeds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBreed)))
            .andExpect(status().isOk());

        // Validate the Breed in the database
        List<Breed> breedList = breedRepository.findAll();
        assertThat(breedList).hasSize(databaseSizeBeforeUpdate);
        Breed testBreed = breedList.get(breedList.size() - 1);
        assertThat(testBreed.getBreedName()).isEqualTo(UPDATED_BREED_NAME);
        assertThat(testBreed.getBreedNameEng()).isEqualTo(UPDATED_BREED_NAME_ENG);
    }

    @Test
    @Transactional
    public void updateNonExistingBreed() throws Exception {
        int databaseSizeBeforeUpdate = breedRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBreedMockMvc.perform(put("/api/breeds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(breed)))
            .andExpect(status().isBadRequest());

        // Validate the Breed in the database
        List<Breed> breedList = breedRepository.findAll();
        assertThat(breedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBreed() throws Exception {
        // Initialize the database
        breedRepository.saveAndFlush(breed);

        int databaseSizeBeforeDelete = breedRepository.findAll().size();

        // Delete the breed
        restBreedMockMvc.perform(delete("/api/breeds/{id}", breed.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Breed> breedList = breedRepository.findAll();
        assertThat(breedList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
