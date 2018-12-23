package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.ImagenDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Imagen.
 */
public interface ImagenService {

    /**
     * Save a imagen.
     *
     * @param imagenDTO the entity to save
     * @return the persisted entity
     */
    ImagenDTO save(ImagenDTO imagenDTO);

    /**
     * Get all the imagens.
     *
     * @return the list of entities
     */
    List<ImagenDTO> findAll();


    /**
     * Get the "id" imagen.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ImagenDTO> findOne(Long id);

    /**
     * Delete the "id" imagen.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
