package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.ImagenService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.service.dto.ImagenDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Imagen.
 */
@RestController
@RequestMapping("/api")
public class ImagenResource {

    private final Logger log = LoggerFactory.getLogger(ImagenResource.class);

    private static final String ENTITY_NAME = "imagen";

    private final ImagenService imagenService;

    public ImagenResource(ImagenService imagenService) {
        this.imagenService = imagenService;
    }

    /**
     * POST  /imagens : Create a new imagen.
     *
     * @param imagenDTO the imagenDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new imagenDTO, or with status 400 (Bad Request) if the imagen has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/imagens")
    @Timed
    public ResponseEntity<ImagenDTO> createImagen(@Valid @RequestBody ImagenDTO imagenDTO) throws URISyntaxException {
        log.debug("REST request to save Imagen : {}", imagenDTO);
        if (imagenDTO.getId() != null) {
            throw new BadRequestAlertException("A new imagen cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ImagenDTO result = imagenService.save(imagenDTO);
        return ResponseEntity.created(new URI("/api/imagens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /imagens : Updates an existing imagen.
     *
     * @param imagenDTO the imagenDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated imagenDTO,
     * or with status 400 (Bad Request) if the imagenDTO is not valid,
     * or with status 500 (Internal Server Error) if the imagenDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/imagens")
    @Timed
    public ResponseEntity<ImagenDTO> updateImagen(@Valid @RequestBody ImagenDTO imagenDTO) throws URISyntaxException {
        log.debug("REST request to update Imagen : {}", imagenDTO);
        if (imagenDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ImagenDTO result = imagenService.save(imagenDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, imagenDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /imagens : get all the imagens.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of imagens in body
     */
    @GetMapping("/imagens")
    @Timed
    public List<ImagenDTO> getAllImagens() {
        log.debug("REST request to get all Imagens");
        return imagenService.findAll();
    }

    /**
     * GET  /imagens/:id : get the "id" imagen.
     *
     * @param id the id of the imagenDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the imagenDTO, or with status 404 (Not Found)
     */
    @GetMapping("/imagens/{id}")
    @Timed
    public ResponseEntity<ImagenDTO> getImagen(@PathVariable Long id) {
        log.debug("REST request to get Imagen : {}", id);
        Optional<ImagenDTO> imagenDTO = imagenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(imagenDTO);
    }

    /**
     * DELETE  /imagens/:id : delete the "id" imagen.
     *
     * @param id the id of the imagenDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/imagens/{id}")
    @Timed
    public ResponseEntity<Void> deleteImagen(@PathVariable Long id) {
        log.debug("REST request to delete Imagen : {}", id);
        imagenService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
