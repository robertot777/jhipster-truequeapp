package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.TruequeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Trueque.
 */
public interface TruequeService {

    /**
     * Save a trueque.
     *
     * @param truequeDTO the entity to save
     * @return the persisted entity
     */
    TruequeDTO save(TruequeDTO truequeDTO);

    /**
     * Get all the trueques.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TruequeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" trueque.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TruequeDTO> findOne(Long id);

    /**
     * Delete the "id" trueque.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
