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
import ru.vlsu.animalcertification.domain.AnimalType;
import ru.vlsu.animalcertification.service.AnimalTypeService;
import ru.vlsu.animalcertification.service.dto.AnimalTypeDTO;
import ru.vlsu.animalcertification.web.rest.errors.BadRequestAlertException;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link ru.vlsu.animalcertification.domain.AnimalType}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AnimalTypeResource {

    private final Logger log = LoggerFactory.getLogger(AnimalTypeResource.class);

    private static final String ENTITY_NAME = "animalType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AnimalTypeService animalTypeService;

    public AnimalTypeResource(AnimalTypeService animalTypeService) {
        this.animalTypeService = animalTypeService;
    }

    /**
     * {@code POST  /animal-types} : Create a new animalType.
     *
     * @param animalTypeDto the animalType to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new animalType, or with status {@code 400 (Bad Request)} if the animalType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/animal-AnimalTypeDTO")
    public ResponseEntity<AnimalTypeDTO> createAnimalType(@Valid @RequestBody AnimalTypeDTO animalTypeDto) throws URISyntaxException {
        log.debug("REST request to save AnimalType : {}", animalTypeDto);
        if (animalTypeDto.getId() != null) {
            throw new BadRequestAlertException("A new animalType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AnimalTypeDTO result = animalTypeService.save(animalTypeDto);
        return ResponseEntity.created(new URI("/api/animal-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /animal-types} : Updates an existing animalType.
     *
     * @param animalTypeDto the animalType to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated animalType,
     * or with status {@code 400 (Bad Request)} if the animalType is not valid,
     * or with status {@code 500 (Internal Server Error)} if the animalType couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/animal-types")
    public ResponseEntity<AnimalTypeDTO> updateAnimalType(@Valid @RequestBody AnimalTypeDTO animalTypeDto) throws URISyntaxException {
        log.debug("REST request to update AnimalType : {}", animalTypeDto);
        if (animalTypeDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AnimalTypeDTO result = animalTypeService.save(animalTypeDto);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, animalTypeDto.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /animal-types} : get all the animalTypes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of animalTypes in body.
     */
    @GetMapping("/animal-types")
    public ResponseEntity getAllAnimalTypes() {
        log.debug("REST request to get all AnimalTypes");
        return ResponseEntity.ok(animalTypeService.findAll(Pageable.unpaged()).getContent());
    }

    /**
     * {@code GET  /animal-types/:id} : get the "id" animalType.
     *
     * @param id the id of the animalType to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the animalType, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/animal-types/{id}")
    public ResponseEntity<AnimalTypeDTO> getAnimalType(@PathVariable Long id) {
        log.debug("REST request to get AnimalType : {}", id);
        Optional<AnimalTypeDTO> animalType = animalTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(animalType);
    }

    /**
     * {@code DELETE  /animal-types/:id} : delete the "id" animalType.
     *
     * @param id the id of the animalType to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/animal-types/{id}")
    public ResponseEntity<Void> deleteAnimalType(@PathVariable Long id) {
        log.debug("REST request to delete AnimalType : {}", id);
        animalTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
