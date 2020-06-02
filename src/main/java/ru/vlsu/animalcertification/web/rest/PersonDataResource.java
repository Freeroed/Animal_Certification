package ru.vlsu.animalcertification.web.rest;

import ru.vlsu.animalcertification.domain.PersonData;
import ru.vlsu.animalcertification.repository.PersonDataRepository;
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
 * REST controller for managing {@link ru.vlsu.animalcertification.domain.PersonData}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PersonDataResource {

    private final Logger log = LoggerFactory.getLogger(PersonDataResource.class);

    private static final String ENTITY_NAME = "personData";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PersonDataRepository personDataRepository;

    public PersonDataResource(PersonDataRepository personDataRepository) {
        this.personDataRepository = personDataRepository;
    }

    /**
     * {@code POST  /person-data} : Create a new personData.
     *
     * @param personData the personData to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new personData, or with status {@code 400 (Bad Request)} if the personData has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/person-data")
    public ResponseEntity<PersonData> createPersonData(@Valid @RequestBody PersonData personData) throws URISyntaxException {
        log.debug("REST request to save PersonData : {}", personData);
        if (personData.getId() != null) {
            throw new BadRequestAlertException("A new personData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PersonData result = personDataRepository.save(personData);
        return ResponseEntity.created(new URI("/api/person-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /person-data} : Updates an existing personData.
     *
     * @param personData the personData to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated personData,
     * or with status {@code 400 (Bad Request)} if the personData is not valid,
     * or with status {@code 500 (Internal Server Error)} if the personData couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/person-data")
    public ResponseEntity<PersonData> updatePersonData(@Valid @RequestBody PersonData personData) throws URISyntaxException {
        log.debug("REST request to update PersonData : {}", personData);
        if (personData.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PersonData result = personDataRepository.save(personData);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, personData.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /person-data} : get all the personData.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of personData in body.
     */
    @GetMapping("/person-data")
    public List<PersonData> getAllPersonData() {
        log.debug("REST request to get all PersonData");
        return personDataRepository.findAll();
    }

    /**
     * {@code GET  /person-data/:id} : get the "id" personData.
     *
     * @param id the id of the personData to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the personData, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/person-data/{id}")
    public ResponseEntity<PersonData> getPersonData(@PathVariable Long id) {
        log.debug("REST request to get PersonData : {}", id);
        Optional<PersonData> personData = personDataRepository.findById(id);
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
        log.debug("REST request to delete PersonData : {}", id);

        personDataRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
