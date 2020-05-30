package ru.vlsu.animalcertification.web.rest;

import ru.vlsu.animalcertification.AnimalCretificationApp;
import ru.vlsu.animalcertification.domain.Animal;
import ru.vlsu.animalcertification.repository.AnimalRepository;

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

import ru.vlsu.animalcertification.domain.enumeration.Gender;
import ru.vlsu.animalcertification.domain.enumeration.AnimalStatus;
/**
 * Integration tests for the {@link AnimalResource} REST controller.
 */
@SpringBootTest(classes = AnimalCretificationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AnimalResourceIT {

    private static final String DEFAULT_NICKNAME = "AAAAAAAAAA";
    private static final String UPDATED_NICKNAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_BIRTHDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_BIRTHDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Gender DEFAULT_GENDER = Gender.MALE;
    private static final Gender UPDATED_GENDER = Gender.FEMALE;

    private static final String DEFAULT_CHIP = "AAAAAAAAAA";
    private static final String UPDATED_CHIP = "BBBBBBBBBB";

    private static final String DEFAULT_BIRTH_PLACE = "AAAAAAAAAA";
    private static final String UPDATED_BIRTH_PLACE = "BBBBBBBBBB";

    private static final String DEFAULT_TNVED_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TNVED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_COLOR = "BBBBBBBBBB";

    private static final String DEFAULT_COLOR_ENG = "AAAAAAAAAA";
    private static final String UPDATED_COLOR_ENG = "BBBBBBBBBB";

    private static final AnimalStatus DEFAULT_STATUS = AnimalStatus.READY_TO_REQUEST;
    private static final AnimalStatus UPDATED_STATUS = AnimalStatus.IN_REQUEST;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAnimalMockMvc;

    private Animal animal;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Animal createEntity(EntityManager em) {
        Animal animal = new Animal()
            .nickname(DEFAULT_NICKNAME)
            .birthdate(DEFAULT_BIRTHDATE)
            .gender(DEFAULT_GENDER)
            .chip(DEFAULT_CHIP)
            .birthPlace(DEFAULT_BIRTH_PLACE)
            .tnvedCode(DEFAULT_TNVED_CODE)
            .color(DEFAULT_COLOR)
            .colorEng(DEFAULT_COLOR_ENG)
            .status(DEFAULT_STATUS);
        return animal;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Animal createUpdatedEntity(EntityManager em) {
        Animal animal = new Animal()
            .nickname(UPDATED_NICKNAME)
            .birthdate(UPDATED_BIRTHDATE)
            .gender(UPDATED_GENDER)
            .chip(UPDATED_CHIP)
            .birthPlace(UPDATED_BIRTH_PLACE)
            .tnvedCode(UPDATED_TNVED_CODE)
            .color(UPDATED_COLOR)
            .colorEng(UPDATED_COLOR_ENG)
            .status(UPDATED_STATUS);
        return animal;
    }

    @BeforeEach
    public void initTest() {
        animal = createEntity(em);
    }

    @Test
    @Transactional
    public void createAnimal() throws Exception {
        int databaseSizeBeforeCreate = animalRepository.findAll().size();
        // Create the Animal
        restAnimalMockMvc.perform(post("/api/animals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(animal)))
            .andExpect(status().isCreated());

        // Validate the Animal in the database
        List<Animal> animalList = animalRepository.findAll();
        assertThat(animalList).hasSize(databaseSizeBeforeCreate + 1);
        Animal testAnimal = animalList.get(animalList.size() - 1);
        assertThat(testAnimal.getNickname()).isEqualTo(DEFAULT_NICKNAME);
        assertThat(testAnimal.getBirthdate()).isEqualTo(DEFAULT_BIRTHDATE);
        assertThat(testAnimal.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testAnimal.getChip()).isEqualTo(DEFAULT_CHIP);
        assertThat(testAnimal.getBirthPlace()).isEqualTo(DEFAULT_BIRTH_PLACE);
        assertThat(testAnimal.getTnvedCode()).isEqualTo(DEFAULT_TNVED_CODE);
        assertThat(testAnimal.getColor()).isEqualTo(DEFAULT_COLOR);
        assertThat(testAnimal.getColorEng()).isEqualTo(DEFAULT_COLOR_ENG);
        assertThat(testAnimal.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createAnimalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = animalRepository.findAll().size();

        // Create the Animal with an existing ID
        animal.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAnimalMockMvc.perform(post("/api/animals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(animal)))
            .andExpect(status().isBadRequest());

        // Validate the Animal in the database
        List<Animal> animalList = animalRepository.findAll();
        assertThat(animalList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNicknameIsRequired() throws Exception {
        int databaseSizeBeforeTest = animalRepository.findAll().size();
        // set the field null
        animal.setNickname(null);

        // Create the Animal, which fails.


        restAnimalMockMvc.perform(post("/api/animals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(animal)))
            .andExpect(status().isBadRequest());

        List<Animal> animalList = animalRepository.findAll();
        assertThat(animalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBirthdateIsRequired() throws Exception {
        int databaseSizeBeforeTest = animalRepository.findAll().size();
        // set the field null
        animal.setBirthdate(null);

        // Create the Animal, which fails.


        restAnimalMockMvc.perform(post("/api/animals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(animal)))
            .andExpect(status().isBadRequest());

        List<Animal> animalList = animalRepository.findAll();
        assertThat(animalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGenderIsRequired() throws Exception {
        int databaseSizeBeforeTest = animalRepository.findAll().size();
        // set the field null
        animal.setGender(null);

        // Create the Animal, which fails.


        restAnimalMockMvc.perform(post("/api/animals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(animal)))
            .andExpect(status().isBadRequest());

        List<Animal> animalList = animalRepository.findAll();
        assertThat(animalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkColorIsRequired() throws Exception {
        int databaseSizeBeforeTest = animalRepository.findAll().size();
        // set the field null
        animal.setColor(null);

        // Create the Animal, which fails.


        restAnimalMockMvc.perform(post("/api/animals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(animal)))
            .andExpect(status().isBadRequest());

        List<Animal> animalList = animalRepository.findAll();
        assertThat(animalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = animalRepository.findAll().size();
        // set the field null
        animal.setStatus(null);

        // Create the Animal, which fails.


        restAnimalMockMvc.perform(post("/api/animals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(animal)))
            .andExpect(status().isBadRequest());

        List<Animal> animalList = animalRepository.findAll();
        assertThat(animalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAnimals() throws Exception {
        // Initialize the database
        animalRepository.saveAndFlush(animal);

        // Get all the animalList
        restAnimalMockMvc.perform(get("/api/animals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(animal.getId().intValue())))
            .andExpect(jsonPath("$.[*].nickname").value(hasItem(DEFAULT_NICKNAME)))
            .andExpect(jsonPath("$.[*].birthdate").value(hasItem(DEFAULT_BIRTHDATE.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].chip").value(hasItem(DEFAULT_CHIP)))
            .andExpect(jsonPath("$.[*].birthPlace").value(hasItem(DEFAULT_BIRTH_PLACE)))
            .andExpect(jsonPath("$.[*].tnvedCode").value(hasItem(DEFAULT_TNVED_CODE)))
            .andExpect(jsonPath("$.[*].color").value(hasItem(DEFAULT_COLOR)))
            .andExpect(jsonPath("$.[*].colorEng").value(hasItem(DEFAULT_COLOR_ENG)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getAnimal() throws Exception {
        // Initialize the database
        animalRepository.saveAndFlush(animal);

        // Get the animal
        restAnimalMockMvc.perform(get("/api/animals/{id}", animal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(animal.getId().intValue()))
            .andExpect(jsonPath("$.nickname").value(DEFAULT_NICKNAME))
            .andExpect(jsonPath("$.birthdate").value(DEFAULT_BIRTHDATE.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.chip").value(DEFAULT_CHIP))
            .andExpect(jsonPath("$.birthPlace").value(DEFAULT_BIRTH_PLACE))
            .andExpect(jsonPath("$.tnvedCode").value(DEFAULT_TNVED_CODE))
            .andExpect(jsonPath("$.color").value(DEFAULT_COLOR))
            .andExpect(jsonPath("$.colorEng").value(DEFAULT_COLOR_ENG))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingAnimal() throws Exception {
        // Get the animal
        restAnimalMockMvc.perform(get("/api/animals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAnimal() throws Exception {
        // Initialize the database
        animalRepository.saveAndFlush(animal);

        int databaseSizeBeforeUpdate = animalRepository.findAll().size();

        // Update the animal
        Animal updatedAnimal = animalRepository.findById(animal.getId()).get();
        // Disconnect from session so that the updates on updatedAnimal are not directly saved in db
        em.detach(updatedAnimal);
        updatedAnimal
            .nickname(UPDATED_NICKNAME)
            .birthdate(UPDATED_BIRTHDATE)
            .gender(UPDATED_GENDER)
            .chip(UPDATED_CHIP)
            .birthPlace(UPDATED_BIRTH_PLACE)
            .tnvedCode(UPDATED_TNVED_CODE)
            .color(UPDATED_COLOR)
            .colorEng(UPDATED_COLOR_ENG)
            .status(UPDATED_STATUS);

        restAnimalMockMvc.perform(put("/api/animals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAnimal)))
            .andExpect(status().isOk());

        // Validate the Animal in the database
        List<Animal> animalList = animalRepository.findAll();
        assertThat(animalList).hasSize(databaseSizeBeforeUpdate);
        Animal testAnimal = animalList.get(animalList.size() - 1);
        assertThat(testAnimal.getNickname()).isEqualTo(UPDATED_NICKNAME);
        assertThat(testAnimal.getBirthdate()).isEqualTo(UPDATED_BIRTHDATE);
        assertThat(testAnimal.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testAnimal.getChip()).isEqualTo(UPDATED_CHIP);
        assertThat(testAnimal.getBirthPlace()).isEqualTo(UPDATED_BIRTH_PLACE);
        assertThat(testAnimal.getTnvedCode()).isEqualTo(UPDATED_TNVED_CODE);
        assertThat(testAnimal.getColor()).isEqualTo(UPDATED_COLOR);
        assertThat(testAnimal.getColorEng()).isEqualTo(UPDATED_COLOR_ENG);
        assertThat(testAnimal.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingAnimal() throws Exception {
        int databaseSizeBeforeUpdate = animalRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnimalMockMvc.perform(put("/api/animals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(animal)))
            .andExpect(status().isBadRequest());

        // Validate the Animal in the database
        List<Animal> animalList = animalRepository.findAll();
        assertThat(animalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAnimal() throws Exception {
        // Initialize the database
        animalRepository.saveAndFlush(animal);

        int databaseSizeBeforeDelete = animalRepository.findAll().size();

        // Delete the animal
        restAnimalMockMvc.perform(delete("/api/animals/{id}", animal.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Animal> animalList = animalRepository.findAll();
        assertThat(animalList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
