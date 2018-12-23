package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.ImagenService;
import io.github.jhipster.application.domain.Imagen;
import io.github.jhipster.application.repository.ImagenRepository;
import io.github.jhipster.application.service.dto.ImagenDTO;
import io.github.jhipster.application.service.mapper.ImagenMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Imagen.
 */
@Service
@Transactional
public class ImagenServiceImpl implements ImagenService {

    private final Logger log = LoggerFactory.getLogger(ImagenServiceImpl.class);

    private final ImagenRepository imagenRepository;

    private final ImagenMapper imagenMapper;

    public ImagenServiceImpl(ImagenRepository imagenRepository, ImagenMapper imagenMapper) {
        this.imagenRepository = imagenRepository;
        this.imagenMapper = imagenMapper;
    }

    /**
     * Save a imagen.
     *
     * @param imagenDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ImagenDTO save(ImagenDTO imagenDTO) {
        log.debug("Request to save Imagen : {}", imagenDTO);

        Imagen imagen = imagenMapper.toEntity(imagenDTO);
        imagen = imagenRepository.save(imagen);
        return imagenMapper.toDto(imagen);
    }

    /**
     * Get all the imagens.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ImagenDTO> findAll() {
        log.debug("Request to get all Imagens");
        return imagenRepository.findAll().stream()
            .map(imagenMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one imagen by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ImagenDTO> findOne(Long id) {
        log.debug("Request to get Imagen : {}", id);
        return imagenRepository.findById(id)
            .map(imagenMapper::toDto);
    }

    /**
     * Delete the imagen by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Imagen : {}", id);
        imagenRepository.deleteById(id);
    }
}
