package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ImagenDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Imagen and its DTO ImagenDTO.
 */
@Mapper(componentModel = "spring", uses = {ProductoMapper.class, UserMapper.class})
public interface ImagenMapper extends EntityMapper<ImagenDTO, Imagen> {

    @Mapping(source = "producto.id", target = "productoId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    ImagenDTO toDto(Imagen imagen);

    @Mapping(source = "productoId", target = "producto")
    @Mapping(source = "userId", target = "user")
    Imagen toEntity(ImagenDTO imagenDTO);

    default Imagen fromId(Long id) {
        if (id == null) {
            return null;
        }
        Imagen imagen = new Imagen();
        imagen.setId(id);
        return imagen;
    }
}
