package ru.vlsu.animalcertification.web.rest;

import ru.vlsu.animalcertification.domain.BorderCrossingPoint;
import ru.vlsu.animalcertification.repository.BorderCrossingPointRepository;
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
 * REST controller for managing {@link ru.vlsu.animalcertification.domain.BorderCrossingPoint}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BorderCrossingPointResource {

    private final Logger log = LoggerFactory.getLogger(BorderCrossingPointResource.class);

    private static final String ENTITY_NAME = "borderCrossingPoint";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BorderCrossingPointRepository borderCrossingPointRepository;

    public BorderCrossingPointResource(BorderCrossingPointRepository borderCrossingPointRepository) {
        this.borderCrossingPointRepository = borderCrossingPointRepository;
    }

    /**
     * {@code POST  /border-crossing-points} : Create a new borderCrossingPoint.
     *
     * @param borderCrossingPoint the borderCrossingPoint to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new borderCrossingPoint, or with status {@code 400 (Bad Request)} if the borderCrossingPoint has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/border-crossing-points")
    public ResponseEntity<BorderCrossingPoint> createBorderCrossingPoint(@Valid @RequestBody BorderCrossingPoint borderCrossingPoint) throws URISyntaxException {
        log.debug("REST request to save BorderCrossingPoint : {}", borderCrossingPoint);
        if (borderCrossingPoint.getId() != null) {
            throw new BadRequestAlertException("A new borderCrossingPoint cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BorderCrossingPoint result = borderCrossingPointRepository.save(borderCrossingPoint);
        return ResponseEntity.created(new URI("/api/border-crossing-points/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /border-crossing-points} : Updates an existing borderCrossingPoint.
     *
     * @param borderCrossingPoint the borderCrossingPoint to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated borderCrossingPoint,
     * or with status {@code 400 (Bad Request)} if the borderCrossingPoint is not valid,
     * or with status {@code 500 (Internal Server Error)} if the borderCrossingPoint couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/border-crossing-points")
    public ResponseEntity<BorderCrossingPoint> updateBorderCrossingPoint(@Valid @RequestBody BorderCrossingPoint borderCrossingPoint) throws URISyntaxException {
        log.debug("REST request to update BorderCrossingPoint : {}", borderCrossingPoint);
        if (borderCrossingPoint.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BorderCrossingPoint result = borderCrossingPointRepository.save(borderCrossingPoint);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, borderCrossingPoint.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /border-crossing-points} : get all the borderCrossingPoints.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of borderCrossingPoints in body.
     */
    @GetMapping("/border-crossing-points")
    public List<BorderCrossingPoint> getAllBorderCrossingPoints() {
        log.debug("REST request to get all BorderCrossingPoints");
        return borderCrossingPointRepository.findAll();
    }

    /**
     * {@code GET  /border-crossing-points/:id} : get the "id" borderCrossingPoint.
     *
     * @param id the id of the borderCrossingPoint to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the borderCrossingPoint, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/border-crossing-points/{id}")
    public ResponseEntity<BorderCrossingPoint> getBorderCrossingPoint(@PathVariable Long id) {
        log.debug("REST request to get BorderCrossingPoint : {}", id);
        Optional<BorderCrossingPoint> borderCrossingPoint = borderCrossingPointRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(borderCrossingPoint);
    }

    /**
     * {@code DELETE  /border-crossing-points/:id} : delete the "id" borderCrossingPoint.
     *
     * @param id the id of the borderCrossingPoint to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/border-crossing-points/{id}")
    public ResponseEntity<Void> deleteBorderCrossingPoint(@PathVariable Long id) {
        log.debug("REST request to delete BorderCrossingPoint : {}", id);

        borderCrossingPointRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
