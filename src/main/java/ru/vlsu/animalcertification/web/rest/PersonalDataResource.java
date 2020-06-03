package ru.vlsu.animalcertification.web.rest;

import org.springframework.data.domain.Page;
import ru.vlsu.animalcertification.domain.PersonalData;
import ru.vlsu.animalcertification.repository.PersonalDataRepository;
import ru.vlsu.animalcertification.service.PersonalDataService;
import ru.vlsu.animalcertification.service.dto.PersonalDataDTO;
import ru.vlsu.animalcertification.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link PersonalData}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PersonalDataResource {

    private final Logger log = LoggerFactory.getLogger(PersonalDataResource.class);

    private static final String ENTITY_NAME = "personData";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PersonalDataRepository personalDataRepository;

    private final PersonalDataService personalDataService;

    public PersonalDataResource(PersonalDataRepository personalDataRepository, PersonalDataService personalDataService) {
        this.personalDataRepository = personalDataRepository;
        this.personalDataService = personalDataService;
    }

    /**
     * {@code POST  /person-data} : Create a new personalData.
     *
     * @param personalData the personalData to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new personalData, or with status {@code 400 (Bad Request)} if the personalData has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/person-data")
    public ResponseEntity<PersonalData> createPersonData(@Valid @RequestBody PersonalData personalData) throws URISyntaxException {
        log.debug("REST request to save PersonalData : {}", personalData);
        if (personalData.getId() != null) {
            throw new BadRequestAlertException("A new personalData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PersonalData result = personalDataRepository.save(personalData);
        return ResponseEntity.created(new URI("/api/person-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /person-data} : Updates an existing personalData.
     *
     * @param personalData the personalData to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated personalData,
     * or with status {@code 400 (Bad Request)} if the personalData is not valid,
     * or with status {@code 500 (Internal Server Error)} if the personalData couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/person-data")
    public ResponseEntity<PersonalData> updatePersonData(@Valid @RequestBody PersonalData personalData) throws URISyntaxException {
        log.debug("REST request to update PersonalData : {}", personalData);
        if (personalData.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PersonalData result = personalDataRepository.save(personalData);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, personalData.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /person-data} : get all the personData.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of personData in body.
     */
    @GetMapping("/person-data")
    public ResponseEntity getAllPersonData() {
        log.debug("REST request to get all PersonalData");
        return ResponseEntity.ok(personalDataService.findAll().getContent());
    }

    /**
     * {@code GET  /person-data/:id} : get the "id" personData.
     *
     * @param id the id of the personData to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the personData, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/person-data/{id}")
    public ResponseEntity<PersonalData> getPersonData(@PathVariable Long id) {
        log.debug("REST request to get PersonalData : {}", id);
        Optional<PersonalData> personData = personalDataRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(personData);
    }

    /**
     * {@code DELETE  /person-data/:id} : delete the "id" personData.
     *
     * @param id the id of the personData to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/person-data/{id}")
    public ResponseEntity<Void> deletePersonData(@PathVariable Long id) {
        log.debug("REST request to delete PersonalData : {}", id);

        personalDataRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
