package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.TruequeService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.TruequeDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Trueque.
 */
@RestController
@RequestMapping("/api")
public class TruequeResource {

    private final Logger log = LoggerFactory.getLogger(TruequeResource.class);

    private static final String ENTITY_NAME = "trueque";

    private final TruequeService truequeService;

    public TruequeResource(TruequeService truequeService) {
        this.truequeService = truequeService;
    }

    /**
     * POST  /trueques : Create a new trueque.
     *
     * @param truequeDTO the truequeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new truequeDTO, or with status 400 (Bad Request) if the trueque has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/trueques")
    @Timed
    public ResponseEntity<TruequeDTO> createTrueque(@Valid @RequestBody TruequeDTO truequeDTO) throws URISyntaxException {
        log.debug("REST request to save Trueque : {}", truequeDTO);
        if (truequeDTO.getId() != null) {
            throw new BadRequestAlertException("A new trueque cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TruequeDTO result = truequeService.save(truequeDTO);
        return ResponseEntity.created(new URI("/api/trueques/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /trueques : Updates an existing trueque.
     *
     * @param truequeDTO the truequeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated truequeDTO,
     * or with status 400 (Bad Request) if the truequeDTO is not valid,
     * or with status 500 (Internal Server Error) if the truequeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/trueques")
    @Timed
    public ResponseEntity<TruequeDTO> updateTrueque(@Valid @RequestBody TruequeDTO truequeDTO) throws URISyntaxException {
        log.debug("REST request to update Trueque : {}", truequeDTO);
        if (truequeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TruequeDTO result = truequeService.save(truequeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, truequeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /trueques : get all the trueques.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of trueques in body
     */
    @GetMapping("/trueques")
    @Timed
    public ResponseEntity<List<TruequeDTO>> getAllTrueques(Pageable pageable) {
        log.debug("REST request to get a page of Trueques");
        Page<TruequeDTO> page = truequeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/trueques");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /trueques/:id : get the "id" trueque.
     *
     * @param id the id of the truequeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the truequeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/trueques/{id}")
    @Timed
    public ResponseEntity<TruequeDTO> getTrueque(@PathVariable Long id) {
        log.debug("REST request to get Trueque : {}", id);
        Optional<TruequeDTO> truequeDTO = truequeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(truequeDTO);
    }

    /**
     * DELETE  /trueques/:id : delete the "id" trueque.
     *
     * @param id the id of the truequeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/trueques/{id}")
    @Timed
    public ResponseEntity<Void> deleteTrueque(@PathVariable Long id) {
        log.debug("REST request to delete Trueque : {}", id);
        truequeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
