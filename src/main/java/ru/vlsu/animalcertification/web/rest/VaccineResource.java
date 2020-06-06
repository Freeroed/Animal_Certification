package ru.vlsu.animalcertification.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.vlsu.animalcertification.service.VaccineService;
import ru.vlsu.animalcertification.service.dto.VaccineDTO;
import ru.vlsu.animalcertification.web.rest.errors.BadRequestAlertException;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
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

    private final VaccineService vaccineService;

    public VaccineResource(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    /**
     * {@code POST  /vaccines} : Create a new vaccine.
     *
     * @param vaccineDto the vaccine to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vaccine, or with status {@code 400 (Bad Request)} if the vaccine has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vaccines")
    public ResponseEntity<VaccineDTO> createVaccine(@Valid @RequestBody VaccineDTO vaccineDto) throws URISyntaxException {
        log.debug("REST request to save Vaccine : {}", vaccineDto);
        if (vaccineDto.getId() != null) {
            throw new BadRequestAlertException("A new vaccine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VaccineDTO result = vaccineService.save(vaccineDto);
        return ResponseEntity.created(new URI("/api/vaccines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /vaccines} : Updates an existing vaccine.
     *
     * @param vaccineDto the vaccine to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vaccine,
     * or with status {@code 400 (Bad Request)} if the vaccine is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vaccine couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vaccines")
    public ResponseEntity<VaccineDTO> updateVaccine(@Valid @RequestBody VaccineDTO vaccineDto) throws URISyntaxException {
        log.debug("REST request to update Vaccine : {}", vaccineDto);
        if (vaccineDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VaccineDTO result = vaccineService.save(vaccineDto);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, vaccineDto.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /vaccines} : get all the vaccines.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vaccines in body.
     */
    @GetMapping("/vaccines")
    public ResponseEntity getAllVaccines() {
        log.debug("REST request to get all Vaccines");
        return ResponseEntity.ok(vaccineService.findAll(Pageable.unpaged()).getContent());
    }

    /**
     * {@code GET  /vaccines/:id} : get the "id" vaccine.
     *
     * @param id the id of the vaccine to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vaccine, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vaccines/{id}")
    public ResponseEntity<VaccineDTO> getVaccine(@PathVariable Long id) {
        log.debug("REST request to get Vaccine : {}", id);
        Optional<VaccineDTO> vaccine = vaccineService.findOne(id);
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

        vaccineService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
