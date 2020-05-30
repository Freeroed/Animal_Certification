package ru.vlsu.animalcertification.web.rest;

import ru.vlsu.animalcertification.domain.Vaccine;
import ru.vlsu.animalcertification.repository.VaccineRepository;
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
 * REST controller for managing {@link ru.vlsu.animalcertification.domain.Vaccine}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class VaccineResource {

    private final Logger log = LoggerFactory.getLogger(VaccineResource.class);

    private static final String ENTITY_NAME = "vaccine";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VaccineRepository vaccineRepository;

    public VaccineResource(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }

    /**
     * {@code POST  /vaccines} : Create a new vaccine.
     *
     * @param vaccine the vaccine to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vaccine, or with status {@code 400 (Bad Request)} if the vaccine has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vaccines")
    public ResponseEntity<Vaccine> createVaccine(@Valid @RequestBody Vaccine vaccine) throws URISyntaxException {
        log.debug("REST request to save Vaccine : {}", vaccine);
        if (vaccine.getId() != null) {
            throw new BadRequestAlertException("A new vaccine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Vaccine result = vaccineRepository.save(vaccine);
        return ResponseEntity.created(new URI("/api/vaccines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /vaccines} : Updates an existing vaccine.
     *
     * @param vaccine the vaccine to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vaccine,
     * or with status {@code 400 (Bad Request)} if the vaccine is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vaccine couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vaccines")
    public ResponseEntity<Vaccine> updateVaccine(@Valid @RequestBody Vaccine vaccine) throws URISyntaxException {
        log.debug("REST request to update Vaccine : {}", vaccine);
        if (vaccine.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Vaccine result = vaccineRepository.save(vaccine);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, vaccine.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /vaccines} : get all the vaccines.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vaccines in body.
     */
    @GetMapping("/vaccines")
    public List<Vaccine> getAllVaccines() {
        log.debug("REST request to get all Vaccines");
        return vaccineRepository.findAll();
    }

    /**
     * {@code GET  /vaccines/:id} : get the "id" vaccine.
     *
     * @param id the id of the vaccine to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vaccine, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vaccines/{id}")
    public ResponseEntity<Vaccine> getVaccine(@PathVariable Long id) {
        log.debug("REST request to get Vaccine : {}", id);
        Optional<Vaccine> vaccine = vaccineRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(vaccine);
    }

    /**
     * {@code DELETE  /vaccines/:id} : delete the "id" vaccine.
     *
     * @param id the id of the vaccine to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vaccines/{id}")
    public ResponseEntity<Void> deleteVaccine(@PathVariable Long id) {
        log.debug("REST request to delete Vaccine : {}", id);

        vaccineRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
