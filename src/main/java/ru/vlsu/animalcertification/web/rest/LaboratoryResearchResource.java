package ru.vlsu.animalcertification.web.rest;

import org.springframework.data.domain.Pageable;
import ru.vlsu.animalcertification.domain.LaboratoryResearch;
import ru.vlsu.animalcertification.repository.LaboratoryResearchRepository;
import ru.vlsu.animalcertification.service.LaboratoryResearchService;
import ru.vlsu.animalcertification.service.dto.LaboratoryResearchDTO;
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
 * REST controller for managing {@link ru.vlsu.animalcertification.domain.LaboratoryResearch}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class LaboratoryResearchResource {

    private final Logger log = LoggerFactory.getLogger(LaboratoryResearchResource.class);

    private static final String ENTITY_NAME = "laboratoryResearch";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LaboratoryResearchService laboratoryResearchService;

    public LaboratoryResearchResource(LaboratoryResearchService laboratoryResearchService) {
        this.laboratoryResearchService = laboratoryResearchService;
    }

    /**
     * {@code POST  /laboratory-researches} : Create a new laboratoryResearch.
     *
     * @param laboratoryResearchDto the laboratoryResearch to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new laboratoryResearch, or with status {@code 400 (Bad Request)} if the laboratoryResearch has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/laboratory-researches")
    public ResponseEntity<LaboratoryResearchDTO> createLaboratoryResearch(@Valid @RequestBody LaboratoryResearchDTO laboratoryResearchDto) throws URISyntaxException {
        log.debug("REST request to save LaboratoryResearch : {}", laboratoryResearchDto);
        if (laboratoryResearchDto.getId() != null) {
            throw new BadRequestAlertException("A new laboratoryResearch cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LaboratoryResearchDTO result = laboratoryResearchService.save(laboratoryResearchDto);
        return ResponseEntity.created(new URI("/api/laboratory-researches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /laboratory-researches} : Updates an existing laboratoryResearch.
     *
     * @param laboratoryResearchDto the laboratoryResearch to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated laboratoryResearch,
     * or with status {@code 400 (Bad Request)} if the laboratoryResearch is not valid,
     * or with status {@code 500 (Internal Server Error)} if the laboratoryResearch couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/laboratory-researches")
    public ResponseEntity<LaboratoryResearchDTO> updateLaboratoryResearch(@Valid @RequestBody LaboratoryResearchDTO laboratoryResearchDto) throws URISyntaxException {
        log.debug("REST request to update LaboratoryResearch : {}", laboratoryResearchDto);
        if (laboratoryResearchDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LaboratoryResearchDTO result = laboratoryResearchService.save(laboratoryResearchDto);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, laboratoryResearchDto.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /laboratory-researches} : get all the laboratoryResearches.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of laboratoryResearches in body.
     */
    @GetMapping("/laboratory-researches")
    public ResponseEntity getAllLaboratoryResearches() {
        log.debug("REST request to get all LaboratoryResearches");
        return ResponseEntity.ok(laboratoryResearchService.findAll(Pageable.unpaged()).getContent());
    }

    /**
     * {@code GET  /laboratory-researches/:id} : get the "id" laboratoryResearch.
     *
     * @param id the id of the laboratoryResearch to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the laboratoryResearch, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/laboratory-researches/{id}")
    public ResponseEntity<LaboratoryResearchDTO> getLaboratoryResearch(@PathVariable Long id) {
        log.debug("REST request to get LaboratoryResearch : {}", id);
        Optional<LaboratoryResearchDTO> laboratoryResearch = laboratoryResearchService.findOne(id);
        return ResponseUtil.wrapOrNotFound(laboratoryResearch);
    }

    /**
     * {@code DELETE  /laboratory-researches/:id} : delete the "id" laboratoryResearch.
     *
     * @param id the id of the laboratoryResearch to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/laboratory-researches/{id}")
    public ResponseEntity<Void> deleteLaboratoryResearch(@PathVariable Long id) {
        log.debug("REST request to delete LaboratoryResearch : {}", id);
        laboratoryResearchService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
