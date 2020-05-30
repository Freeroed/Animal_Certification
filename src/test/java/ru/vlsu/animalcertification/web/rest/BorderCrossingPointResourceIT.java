package ru.vlsu.animalcertification.web.rest;

import ru.vlsu.animalcertification.AnimalCretificationApp;
import ru.vlsu.animalcertification.domain.BorderCrossingPoint;
import ru.vlsu.animalcertification.repository.BorderCrossingPointRepository;

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
 * Integration tests for the {@link BorderCrossingPointResource} REST controller.
 */
@SpringBootTest(classes = AnimalCretificationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BorderCrossingPointResourceIT {

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_ADJACENT_POINT = "AAAAAAAAAA";
    private static final String UPDATED_ADJACENT_POINT = "BBBBBBBBBB";

    private static final String DEFAULT_CLASSIFICATION = "AAAAAAAAAA";
    private static final String UPDATED_CLASSIFICATION = "BBBBBBBBBB";

    private static final String DEFAULT_SCHEDULE = "AAAAAAAAAA";
    private static final String UPDATED_SCHEDULE = "BBBBBBBBBB";

    private static final String DEFAULT_SCHEDULE_OF_OFFICALS = "AAAAAAAAAA";
    private static final String UPDATED_SCHEDULE_OF_OFFICALS = "BBBBBBBBBB";

    @Autowired
    private BorderCrossingPointRepository borderCrossingPointRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBorderCrossingPointMockMvc;

    private BorderCrossingPoint borderCrossingPoint;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BorderCrossingPoint createEntity(EntityManager em) {
        BorderCrossingPoint borderCrossingPoint = new BorderCrossingPoint()
            .location(DEFAULT_LOCATION)
            .adjacentPoint(DEFAULT_ADJACENT_POINT)
            .classification(DEFAULT_CLASSIFICATION)
            .schedule(DEFAULT_SCHEDULE)
            .scheduleOfOfficals(DEFAULT_SCHEDULE_OF_OFFICALS);
        return borderCrossingPoint;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BorderCrossingPoint createUpdatedEntity(EntityManager em) {
        BorderCrossingPoint borderCrossingPoint = new BorderCrossingPoint()
            .location(UPDATED_LOCATION)
            .adjacentPoint(UPDATED_ADJACENT_POINT)
            .classification(UPDATED_CLASSIFICATION)
            .schedule(UPDATED_SCHEDULE)
            .scheduleOfOfficals(UPDATED_SCHEDULE_OF_OFFICALS);
        return borderCrossingPoint;
    }

    @BeforeEach
    public void initTest() {
        borderCrossingPoint = createEntity(em);
    }

    @Test
    @Transactional
    public void createBorderCrossingPoint() throws Exception {
        int databaseSizeBeforeCreate = borderCrossingPointRepository.findAll().size();
        // Create the BorderCrossingPoint
        restBorderCrossingPointMockMvc.perform(post("/api/border-crossing-points")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(borderCrossingPoint)))
            .andExpect(status().isCreated());

        // Validate the BorderCrossingPoint in the database
        List<BorderCrossingPoint> borderCrossingPointList = borderCrossingPointRepository.findAll();
        assertThat(borderCrossingPointList).hasSize(databaseSizeBeforeCreate + 1);
        BorderCrossingPoint testBorderCrossingPoint = borderCrossingPointList.get(borderCrossingPointList.size() - 1);
        assertThat(testBorderCrossingPoint.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testBorderCrossingPoint.getAdjacentPoint()).isEqualTo(DEFAULT_ADJACENT_POINT);
        assertThat(testBorderCrossingPoint.getClassification()).isEqualTo(DEFAULT_CLASSIFICATION);
        assertThat(testBorderCrossingPoint.getSchedule()).isEqualTo(DEFAULT_SCHEDULE);
        assertThat(testBorderCrossingPoint.getScheduleOfOfficals()).isEqualTo(DEFAULT_SCHEDULE_OF_OFFICALS);
    }

    @Test
    @Transactional
    public void createBorderCrossingPointWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = borderCrossingPointRepository.findAll().size();

        // Create the BorderCrossingPoint with an existing ID
        borderCrossingPoint.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBorderCrossingPointMockMvc.perform(post("/api/border-crossing-points")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(borderCrossingPoint)))
            .andExpect(status().isBadRequest());

        // Validate the BorderCrossingPoint in the database
        List<BorderCrossingPoint> borderCrossingPointList = borderCrossingPointRepository.findAll();
        assertThat(borderCrossingPointList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLocationIsRequired() throws Exception {
        int databaseSizeBeforeTest = borderCrossingPointRepository.findAll().size();
        // set the field null
        borderCrossingPoint.setLocation(null);

        // Create the BorderCrossingPoint, which fails.


        restBorderCrossingPointMockMvc.perform(post("/api/border-crossing-points")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(borderCrossingPoint)))
            .andExpect(status().isBadRequest());

        List<BorderCrossingPoint> borderCrossingPointList = borderCrossingPointRepository.findAll();
        assertThat(borderCrossingPointList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAdjacentPointIsRequired() throws Exception {
        int databaseSizeBeforeTest = borderCrossingPointRepository.findAll().size();
        // set the field null
        borderCrossingPoint.setAdjacentPoint(null);

        // Create the BorderCrossingPoint, which fails.


        restBorderCrossingPointMockMvc.perform(post("/api/border-crossing-points")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(borderCrossingPoint)))
            .andExpect(status().isBadRequest());

        List<BorderCrossingPoint> borderCrossingPointList = borderCrossingPointRepository.findAll();
        assertThat(borderCrossingPointList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClassificationIsRequired() throws Exception {
        int databaseSizeBeforeTest = borderCrossingPointRepository.findAll().size();
        // set the field null
        borderCrossingPoint.setClassification(null);

        // Create the BorderCrossingPoint, which fails.


        restBorderCrossingPointMockMvc.perform(post("/api/border-crossing-points")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(borderCrossingPoint)))
            .andExpect(status().isBadRequest());

        List<BorderCrossingPoint> borderCrossingPointList = borderCrossingPointRepository.findAll();
        assertThat(borderCrossingPointList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkScheduleIsRequired() throws Exception {
        int databaseSizeBeforeTest = borderCrossingPointRepository.findAll().size();
        // set the field null
        borderCrossingPoint.setSchedule(null);

        // Create the BorderCrossingPoint, which fails.


        restBorderCrossingPointMockMvc.perform(post("/api/border-crossing-points")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(borderCrossingPoint)))
            .andExpect(status().isBadRequest());

        List<BorderCrossingPoint> borderCrossingPointList = borderCrossingPointRepository.findAll();
        assertThat(borderCrossingPointList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkScheduleOfOfficalsIsRequired() throws Exception {
        int databaseSizeBeforeTest = borderCrossingPointRepository.findAll().size();
        // set the field null
        borderCrossingPoint.setScheduleOfOfficals(null);

        // Create the BorderCrossingPoint, which fails.


        restBorderCrossingPointMockMvc.perform(post("/api/border-crossing-points")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(borderCrossingPoint)))
            .andExpect(status().isBadRequest());

        List<BorderCrossingPoint> borderCrossingPointList = borderCrossingPointRepository.findAll();
        assertThat(borderCrossingPointList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBorderCrossingPoints() throws Exception {
        // Initialize the database
        borderCrossingPointRepository.saveAndFlush(borderCrossingPoint);

        // Get all the borderCrossingPointList
        restBorderCrossingPointMockMvc.perform(get("/api/border-crossing-points?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(borderCrossingPoint.getId().intValue())))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION)))
            .andExpect(jsonPath("$.[*].adjacentPoint").value(hasItem(DEFAULT_ADJACENT_POINT)))
            .andExpect(jsonPath("$.[*].classification").value(hasItem(DEFAULT_CLASSIFICATION)))
            .andExpect(jsonPath("$.[*].schedule").value(hasItem(DEFAULT_SCHEDULE)))
            .andExpect(jsonPath("$.[*].scheduleOfOfficals").value(hasItem(DEFAULT_SCHEDULE_OF_OFFICALS)));
    }
    
    @Test
    @Transactional
    public void getBorderCrossingPoint() throws Exception {
        // Initialize the database
        borderCrossingPointRepository.saveAndFlush(borderCrossingPoint);

        // Get the borderCrossingPoint
        restBorderCrossingPointMockMvc.perform(get("/api/border-crossing-points/{id}", borderCrossingPoint.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(borderCrossingPoint.getId().intValue()))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION))
            .andExpect(jsonPath("$.adjacentPoint").value(DEFAULT_ADJACENT_POINT))
            .andExpect(jsonPath("$.classification").value(DEFAULT_CLASSIFICATION))
            .andExpect(jsonPath("$.schedule").value(DEFAULT_SCHEDULE))
            .andExpect(jsonPath("$.scheduleOfOfficals").value(DEFAULT_SCHEDULE_OF_OFFICALS));
    }
    @Test
    @Transactional
    public void getNonExistingBorderCrossingPoint() throws Exception {
        // Get the borderCrossingPoint
        restBorderCrossingPointMockMvc.perform(get("/api/border-crossing-points/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBorderCrossingPoint() throws Exception {
        // Initialize the database
        borderCrossingPointRepository.saveAndFlush(borderCrossingPoint);

        int databaseSizeBeforeUpdate = borderCrossingPointRepository.findAll().size();

        // Update the borderCrossingPoint
        BorderCrossingPoint updatedBorderCrossingPoint = borderCrossingPointRepository.findById(borderCrossingPoint.getId()).get();
        // Disconnect from session so that the updates on updatedBorderCrossingPoint are not directly saved in db
        em.detach(updatedBorderCrossingPoint);
        updatedBorderCrossingPoint
            .location(UPDATED_LOCATION)
            .adjacentPoint(UPDATED_ADJACENT_POINT)
            .classification(UPDATED_CLASSIFICATION)
            .schedule(UPDATED_SCHEDULE)
            .scheduleOfOfficals(UPDATED_SCHEDULE_OF_OFFICALS);

        restBorderCrossingPointMockMvc.perform(put("/api/border-crossing-points")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBorderCrossingPoint)))
            .andExpect(status().isOk());

        // Validate the BorderCrossingPoint in the database
        List<BorderCrossingPoint> borderCrossingPointList = borderCrossingPointRepository.findAll();
        assertThat(borderCrossingPointList).hasSize(databaseSizeBeforeUpdate);
        BorderCrossingPoint testBorderCrossingPoint = borderCrossingPointList.get(borderCrossingPointList.size() - 1);
        assertThat(testBorderCrossingPoint.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testBorderCrossingPoint.getAdjacentPoint()).isEqualTo(UPDATED_ADJACENT_POINT);
        assertThat(testBorderCrossingPoint.getClassification()).isEqualTo(UPDATED_CLASSIFICATION);
        assertThat(testBorderCrossingPoint.getSchedule()).isEqualTo(UPDATED_SCHEDULE);
        assertThat(testBorderCrossingPoint.getScheduleOfOfficals()).isEqualTo(UPDATED_SCHEDULE_OF_OFFICALS);
    }

    @Test
    @Transactional
    public void updateNonExistingBorderCrossingPoint() throws Exception {
        int databaseSizeBeforeUpdate = borderCrossingPointRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBorderCrossingPointMockMvc.perform(put("/api/border-crossing-points")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(borderCrossingPoint)))
            .andExpect(status().isBadRequest());

        // Validate the BorderCrossingPoint in the database
        List<BorderCrossingPoint> borderCrossingPointList = borderCrossingPointRepository.findAll();
        assertThat(borderCrossingPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBorderCrossingPoint() throws Exception {
        // Initialize the database
        borderCrossingPointRepository.saveAndFlush(borderCrossingPoint);

        int databaseSizeBeforeDelete = borderCrossingPointRepository.findAll().size();

        // Delete the borderCrossingPoint
        restBorderCrossingPointMockMvc.perform(delete("/api/border-crossing-points/{id}", borderCrossingPoint.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BorderCrossingPoint> borderCrossingPointList = borderCrossingPointRepository.findAll();
        assertThat(borderCrossingPointList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
