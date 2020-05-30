package ru.vlsu.animalcertification.web.rest;

import ru.vlsu.animalcertification.AnimalCretificationApp;
import ru.vlsu.animalcertification.domain.LaboratoryResearch;
import ru.vlsu.animalcertification.repository.LaboratoryResearchRepository;

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

import ru.vlsu.animalcertification.domain.enumeration.LaboratoryTestResult;
/**
 * Integration tests for the {@link LaboratoryResearchResource} REST controller.
 */
@SpringBootTest(classes = AnimalCretificationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class LaboratoryResearchResourceIT {

    private static final String DEFAULT_LABORATOTY = "AAAAAAAAAA";
    private static final String UPDATED_LABORATOTY = "BBBBBBBBBB";

    private static final String DEFAULT_INDICATOR = "AAAAAAAAAA";
    private static final String UPDATED_INDICATOR = "BBBBBBBBBB";

    private static final String DEFAULT_RESULT_RECEIPT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_RESULT_RECEIPT_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_RESURCH_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_RESURCH_METHOD = "BBBBBBBBBB";

    private static final String DEFAULT_EXAMINATION_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_EXAMINATION_NUMBER = "BBBBBBBBBB";

    private static final LaboratoryTestResult DEFAULT_RESULT = LaboratoryTestResult.POSITIVE;
    private static final LaboratoryTestResult UPDATED_RESULT = LaboratoryTestResult.NEGATIVE;

    @Autowired
    private LaboratoryResearchRepository laboratoryResearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLaboratoryResearchMockMvc;

    private LaboratoryResearch laboratoryResearch;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LaboratoryResearch createEntity(EntityManager em) {
        LaboratoryResearch laboratoryResearch = new LaboratoryResearch()
            .laboratoty(DEFAULT_LABORATOTY)
            .indicator(DEFAULT_INDICATOR)
            .resultReceiptDate(DEFAULT_RESULT_RECEIPT_DATE)
            .resurchMethod(DEFAULT_RESURCH_METHOD)
            .examinationNumber(DEFAULT_EXAMINATION_NUMBER)
            .result(DEFAULT_RESULT);
        return laboratoryResearch;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LaboratoryResearch createUpdatedEntity(EntityManager em) {
        LaboratoryResearch laboratoryResearch = new LaboratoryResearch()
            .laboratoty(UPDATED_LABORATOTY)
            .indicator(UPDATED_INDICATOR)
            .resultReceiptDate(UPDATED_RESULT_RECEIPT_DATE)
            .resurchMethod(UPDATED_RESURCH_METHOD)
            .examinationNumber(UPDATED_EXAMINATION_NUMBER)
            .result(UPDATED_RESULT);
        return laboratoryResearch;
    }

    @BeforeEach
    public void initTest() {
        laboratoryResearch = createEntity(em);
    }

    @Test
    @Transactional
    public void createLaboratoryResearch() throws Exception {
        int databaseSizeBeforeCreate = laboratoryResearchRepository.findAll().size();
        // Create the LaboratoryResearch
        restLaboratoryResearchMockMvc.perform(post("/api/laboratory-researches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(laboratoryResearch)))
            .andExpect(status().isCreated());

        // Validate the LaboratoryResearch in the database
        List<LaboratoryResearch> laboratoryResearchList = laboratoryResearchRepository.findAll();
        assertThat(laboratoryResearchList).hasSize(databaseSizeBeforeCreate + 1);
        LaboratoryResearch testLaboratoryResearch = laboratoryResearchList.get(laboratoryResearchList.size() - 1);
        assertThat(testLaboratoryResearch.getLaboratoty()).isEqualTo(DEFAULT_LABORATOTY);
        assertThat(testLaboratoryResearch.getIndicator()).isEqualTo(DEFAULT_INDICATOR);
        assertThat(testLaboratoryResearch.getResultReceiptDate()).isEqualTo(DEFAULT_RESULT_RECEIPT_DATE);
        assertThat(testLaboratoryResearch.getResurchMethod()).isEqualTo(DEFAULT_RESURCH_METHOD);
        assertThat(testLaboratoryResearch.getExaminationNumber()).isEqualTo(DEFAULT_EXAMINATION_NUMBER);
        assertThat(testLaboratoryResearch.getResult()).isEqualTo(DEFAULT_RESULT);
    }

    @Test
    @Transactional
    public void createLaboratoryResearchWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = laboratoryResearchRepository.findAll().size();

        // Create the LaboratoryResearch with an existing ID
        laboratoryResearch.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLaboratoryResearchMockMvc.perform(post("/api/laboratory-researches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(laboratoryResearch)))
            .andExpect(status().isBadRequest());

        // Validate the LaboratoryResearch in the database
        List<LaboratoryResearch> laboratoryResearchList = laboratoryResearchRepository.findAll();
        assertThat(laboratoryResearchList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLaboratotyIsRequired() throws Exception {
        int databaseSizeBeforeTest = laboratoryResearchRepository.findAll().size();
        // set the field null
        laboratoryResearch.setLaboratoty(null);

        // Create the LaboratoryResearch, which fails.


        restLaboratoryResearchMockMvc.perform(post("/api/laboratory-researches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(laboratoryResearch)))
            .andExpect(status().isBadRequest());

        List<LaboratoryResearch> laboratoryResearchList = laboratoryResearchRepository.findAll();
        assertThat(laboratoryResearchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIndicatorIsRequired() throws Exception {
        int databaseSizeBeforeTest = laboratoryResearchRepository.findAll().size();
        // set the field null
        laboratoryResearch.setIndicator(null);

        // Create the LaboratoryResearch, which fails.


        restLaboratoryResearchMockMvc.perform(post("/api/laboratory-researches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(laboratoryResearch)))
            .andExpect(status().isBadRequest());

        List<LaboratoryResearch> laboratoryResearchList = laboratoryResearchRepository.findAll();
        assertThat(laboratoryResearchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkResultReceiptDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = laboratoryResearchRepository.findAll().size();
        // set the field null
        laboratoryResearch.setResultReceiptDate(null);

        // Create the LaboratoryResearch, which fails.


        restLaboratoryResearchMockMvc.perform(post("/api/laboratory-researches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(laboratoryResearch)))
            .andExpect(status().isBadRequest());

        List<LaboratoryResearch> laboratoryResearchList = laboratoryResearchRepository.findAll();
        assertThat(laboratoryResearchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkResurchMethodIsRequired() throws Exception {
        int databaseSizeBeforeTest = laboratoryResearchRepository.findAll().size();
        // set the field null
        laboratoryResearch.setResurchMethod(null);

        // Create the LaboratoryResearch, which fails.


        restLaboratoryResearchMockMvc.perform(post("/api/laboratory-researches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(laboratoryResearch)))
            .andExpect(status().isBadRequest());

        List<LaboratoryResearch> laboratoryResearchList = laboratoryResearchRepository.findAll();
        assertThat(laboratoryResearchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExaminationNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = laboratoryResearchRepository.findAll().size();
        // set the field null
        laboratoryResearch.setExaminationNumber(null);

        // Create the LaboratoryResearch, which fails.


        restLaboratoryResearchMockMvc.perform(post("/api/laboratory-researches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(laboratoryResearch)))
            .andExpect(status().isBadRequest());

        List<LaboratoryResearch> laboratoryResearchList = laboratoryResearchRepository.findAll();
        assertThat(laboratoryResearchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkResultIsRequired() throws Exception {
        int databaseSizeBeforeTest = laboratoryResearchRepository.findAll().size();
        // set the field null
        laboratoryResearch.setResult(null);

        // Create the LaboratoryResearch, which fails.


        restLaboratoryResearchMockMvc.perform(post("/api/laboratory-researches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(laboratoryResearch)))
            .andExpect(status().isBadRequest());

        List<LaboratoryResearch> laboratoryResearchList = laboratoryResearchRepository.findAll();
        assertThat(laboratoryResearchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLaboratoryResearches() throws Exception {
        // Initialize the database
        laboratoryResearchRepository.saveAndFlush(laboratoryResearch);

        // Get all the laboratoryResearchList
        restLaboratoryResearchMockMvc.perform(get("/api/laboratory-researches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(laboratoryResearch.getId().intValue())))
            .andExpect(jsonPath("$.[*].laboratoty").value(hasItem(DEFAULT_LABORATOTY)))
            .andExpect(jsonPath("$.[*].indicator").value(hasItem(DEFAULT_INDICATOR)))
            .andExpect(jsonPath("$.[*].resultReceiptDate").value(hasItem(DEFAULT_RESULT_RECEIPT_DATE)))
            .andExpect(jsonPath("$.[*].resurchMethod").value(hasItem(DEFAULT_RESURCH_METHOD)))
            .andExpect(jsonPath("$.[*].examinationNumber").value(hasItem(DEFAULT_EXAMINATION_NUMBER)))
            .andExpect(jsonPath("$.[*].result").value(hasItem(DEFAULT_RESULT.toString())));
    }
    
    @Test
    @Transactional
    public void getLaboratoryResearch() throws Exception {
        // Initialize the database
        laboratoryResearchRepository.saveAndFlush(laboratoryResearch);

        // Get the laboratoryResearch
        restLaboratoryResearchMockMvc.perform(get("/api/laboratory-researches/{id}", laboratoryResearch.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(laboratoryResearch.getId().intValue()))
            .andExpect(jsonPath("$.laboratoty").value(DEFAULT_LABORATOTY))
            .andExpect(jsonPath("$.indicator").value(DEFAULT_INDICATOR))
            .andExpect(jsonPath("$.resultReceiptDate").value(DEFAULT_RESULT_RECEIPT_DATE))
            .andExpect(jsonPath("$.resurchMethod").value(DEFAULT_RESURCH_METHOD))
            .andExpect(jsonPath("$.examinationNumber").value(DEFAULT_EXAMINATION_NUMBER))
            .andExpect(jsonPath("$.result").value(DEFAULT_RESULT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingLaboratoryResearch() throws Exception {
        // Get the laboratoryResearch
        restLaboratoryResearchMockMvc.perform(get("/api/laboratory-researches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLaboratoryResearch() throws Exception {
        // Initialize the database
        laboratoryResearchRepository.saveAndFlush(laboratoryResearch);

        int databaseSizeBeforeUpdate = laboratoryResearchRepository.findAll().size();

        // Update the laboratoryResearch
        LaboratoryResearch updatedLaboratoryResearch = laboratoryResearchRepository.findById(laboratoryResearch.getId()).get();
        // Disconnect from session so that the updates on updatedLaboratoryResearch are not directly saved in db
        em.detach(updatedLaboratoryResearch);
        updatedLaboratoryResearch
            .laboratoty(UPDATED_LABORATOTY)
            .indicator(UPDATED_INDICATOR)
            .resultReceiptDate(UPDATED_RESULT_RECEIPT_DATE)
            .resurchMethod(UPDATED_RESURCH_METHOD)
            .examinationNumber(UPDATED_EXAMINATION_NUMBER)
            .result(UPDATED_RESULT);

        restLaboratoryResearchMockMvc.perform(put("/api/laboratory-researches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedLaboratoryResearch)))
            .andExpect(status().isOk());

        // Validate the LaboratoryResearch in the database
        List<LaboratoryResearch> laboratoryResearchList = laboratoryResearchRepository.findAll();
        assertThat(laboratoryResearchList).hasSize(databaseSizeBeforeUpdate);
        LaboratoryResearch testLaboratoryResearch = laboratoryResearchList.get(laboratoryResearchList.size() - 1);
        assertThat(testLaboratoryResearch.getLaboratoty()).isEqualTo(UPDATED_LABORATOTY);
        assertThat(testLaboratoryResearch.getIndicator()).isEqualTo(UPDATED_INDICATOR);
        assertThat(testLaboratoryResearch.getResultReceiptDate()).isEqualTo(UPDATED_RESULT_RECEIPT_DATE);
        assertThat(testLaboratoryResearch.getResurchMethod()).isEqualTo(UPDATED_RESURCH_METHOD);
        assertThat(testLaboratoryResearch.getExaminationNumber()).isEqualTo(UPDATED_EXAMINATION_NUMBER);
        assertThat(testLaboratoryResearch.getResult()).isEqualTo(UPDATED_RESULT);
    }

    @Test
    @Transactional
    public void updateNonExistingLaboratoryResearch() throws Exception {
        int databaseSizeBeforeUpdate = laboratoryResearchRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLaboratoryResearchMockMvc.perform(put("/api/laboratory-researches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(laboratoryResearch)))
            .andExpect(status().isBadRequest());

        // Validate the LaboratoryResearch in the database
        List<LaboratoryResearch> laboratoryResearchList = laboratoryResearchRepository.findAll();
        assertThat(laboratoryResearchList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLaboratoryResearch() throws Exception {
        // Initialize the database
        laboratoryResearchRepository.saveAndFlush(laboratoryResearch);

        int databaseSizeBeforeDelete = laboratoryResearchRepository.findAll().size();

        // Delete the laboratoryResearch
        restLaboratoryResearchMockMvc.perform(delete("/api/laboratory-researches/{id}", laboratoryResearch.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LaboratoryResearch> laboratoryResearchList = laboratoryResearchRepository.findAll();
        assertThat(laboratoryResearchList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
