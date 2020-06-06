package ru.vlsu.animalcertification.web.rest;

import org.springframework.data.domain.Pageable;
import ru.vlsu.animalcertification.domain.Breed;
import ru.vlsu.animalcertification.repository.BreedRepository;
import ru.vlsu.animalcertification.service.BreedService;
import ru.vlsu.animalcertification.service.dto.BreedDTO;
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
 * REST controller for managing {@link ru.vlsu.animalcertification.domain.Breed}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BreedResource {

    private final Logger log = LoggerFactory.getLogger(BreedResource.class);

    private static final String ENTITY_NAME = "breed";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BreedService breedService;

    public BreedResource(BreedService breedService) {
        this.breedService = breedService;
    }

    /**
     * {@code POST  /breeds} : Create a new breed.
     *
     * @param breedDTO the breed to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new breed, or with status {@code 400 (Bad Request)} if the breed has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/breeds")
    public ResponseEntity<BreedDTO> createBreed(@Valid @RequestBody BreedDTO breedDTO) throws URISyntaxException {
        log.debug("REST request to save Breed : {}", breedDTO);
        if (breedDTO.getId() != null) {
            throw new BadRequestAlertException("A new breed cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BreedDTO result = breedService.save(breedDTO);
        return ResponseEntity.created(new URI("/api/breeds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /breeds} : Updates an existing breed.
     *
     * @param breedDto the breed to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated breed,
     * or with status {@code 400 (Bad Request)} if the breed is not valid,
     * or with status {@code 500 (Internal Server Error)} if the breed couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/breeds")
    public ResponseEntity<BreedDTO> updateBreed(@Valid @RequestBody BreedDTO breedDto) throws URISyntaxException {
        log.debug("REST request to update Breed : {}", breedDto);
        if (breedDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BreedDTO result = breedService.save(breedDto);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, breedDto.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /breeds} : get all the breeds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of breeds in body.
     */
    @GetMapping("/breeds")
    public ResponseEntity getAllBreeds() {
        log.debug("REST request to get all Breeds");
        return ResponseEntity.ok(breedService.findAll(Pageable.unpaged()).getContent());
    }

    /**
     * {@code GET  /breeds/:id} : get the "id" breed.
     *
     * @param id the id of the breed to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the breed, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/breeds/{id}")
    public ResponseEntity<BreedDTO> getBreed(@PathVariable Long id) {
        log.debug("REST request to get Breed : {}", id);
        Optional<BreedDTO> breed = breedService.findOne(id);
        return ResponseUtil.wrapOrNotFound(breed);
    }

    /**
     * {@code DELETE  /breeds/:id} : delete the "id" breed.
     *
     * @param id the id of the breed to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/breeds/{id}")
    public ResponseEntity<Void> deleteBreed(@PathVariable Long id) {
        log.debug("REST request to delete Breed : {}", id);
        breedService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
