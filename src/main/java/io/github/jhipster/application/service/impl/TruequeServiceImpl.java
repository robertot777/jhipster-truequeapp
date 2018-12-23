package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.TruequeService;
import io.github.jhipster.application.domain.Trueque;
import io.github.jhipster.application.repository.TruequeRepository;
import io.github.jhipster.application.service.dto.TruequeDTO;
import io.github.jhipster.application.service.mapper.TruequeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Trueque.
 */
@Service
@Transactional
public class TruequeServiceImpl implements TruequeService {

    private final Logger log = LoggerFactory.getLogger(TruequeServiceImpl.class);

    private final TruequeRepository truequeRepository;

    private final TruequeMapper truequeMapper;

    public TruequeServiceImpl(TruequeRepository truequeRepository, TruequeMapper truequeMapper) {
        this.truequeRepository = truequeRepository;
        this.truequeMapper = truequeMapper;
    }

    /**
     * Save a trueque.
     *
     * @param truequeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TruequeDTO save(TruequeDTO truequeDTO) {
        log.debug("Request to save Trueque : {}", truequeDTO);

        Trueque trueque = truequeMapper.toEntity(truequeDTO);
        trueque = truequeRepository.save(trueque);
        return truequeMapper.toDto(trueque);
    }

    /**
     * Get all the trueques.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TruequeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Trueques");
        return truequeRepository.findAll(pageable)
            .map(truequeMapper::toDto);
    }


    /**
     * Get one trueque by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TruequeDTO> findOne(Long id) {
        log.debug("Request to get Trueque : {}", id);
        return truequeRepository.findById(id)
            .map(truequeMapper::toDto);
    }

    /**
     * Delete the trueque by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Trueque : {}", id);
        truequeRepository.deleteById(id);
    }
}
