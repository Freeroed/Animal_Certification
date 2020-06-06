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
import ru.vlsu.animalcertification.service.BorderCrossingPointService;
import ru.vlsu.animalcertification.service.dto.BorderCrossingPointDTO;
import ru.vlsu.animalcertification.web.rest.errors.BadRequestAlertException;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
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

    private final BorderCrossingPointService borderCrossingPointService;

    public BorderCrossingPointResource(BorderCrossingPointService borderCrossingPointService) {
        this.borderCrossingPointService = borderCrossingPointService;
    }

    /**
     * {@code POST  /border-crossing-points} : Create a new borderCrossingPoint.
     *
     * @param borderCrossingPointDto the borderCrossingPoint to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new borderCrossingPoint, or with status {@code 400 (Bad Request)} if the borderCrossingPoint has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/border-crossing-points")
    public ResponseEntity<BorderCrossingPointDTO> createBorderCrossingPoint(@Valid @RequestBody BorderCrossingPointDTO borderCrossingPointDto) throws URISyntaxException {
        log.debug("REST request to save BorderCrossingPoint : {}", borderCrossingPointDto);
        if (borderCrossingPointDto.getId() != null) {
            throw new BadRequestAlertException("A new borderCrossingPoint cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BorderCrossingPointDTO result = borderCrossingPointService.save(borderCrossingPointDto);
        return ResponseEntity.created(new URI("/api/border-crossing-points/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /border-crossing-points} : Updates an existing borderCrossingPoint.
     *
     * @param borderCrossingPointDto the borderCrossingPoint to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated borderCrossingPoint,
     * or with status {@code 400 (Bad Request)} if the borderCrossingPoint is not valid,
     * or with status {@code 500 (Internal Server Error)} if the borderCrossingPoint couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/border-crossing-points")
    public ResponseEntity<BorderCrossingPointDTO> updateBorderCrossingPoint(@Valid @RequestBody BorderCrossingPointDTO borderCrossingPointDto) throws URISyntaxException {
        log.debug("REST request to update BorderCrossingPoint : {}", borderCrossingPointDto);
        if (borderCrossingPointDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BorderCrossingPointDTO result = borderCrossingPointService.save(borderCrossingPointDto);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, borderCrossingPointDto.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /border-crossing-points} : get all the borderCrossingPoints.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of borderCrossingPoints in body.
     */
    @GetMapping("/border-crossing-points")
    public ResponseEntity getAllBorderCrossingPoints() {
        log.debug("REST request to get all BorderCrossingPoints");
        return ResponseEntity.ok(borderCrossingPointService.findAll(Pageable.unpaged()).getContent());
    }

    /**
     * {@code GET  /border-crossing-points/:id} : get the "id" borderCrossingPoint.
     *
     * @param id the id of the borderCrossingPoint to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the borderCrossingPoint, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/border-crossing-points/{id}")
    public ResponseEntity<BorderCrossingPointDTO> getBorderCrossingPoint(@PathVariable Long id) {
        log.debug("REST request to get BorderCrossingPoint : {}", id);
        Optional<BorderCrossingPointDTO> borderCrossingPoint = borderCrossingPointService.findOne(id);
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

        borderCrossingPointService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
