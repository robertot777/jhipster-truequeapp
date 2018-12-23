package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.TruequeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Trueque and its DTO TruequeDTO.
 */
@Mapper(componentModel = "spring", uses = {ProductoMapper.class})
public interface TruequeMapper extends EntityMapper<TruequeDTO, Trueque> {

    @Mapping(source = "entrega.id", target = "entregaId")
    @Mapping(source = "recibe.id", target = "recibeId")
    TruequeDTO toDto(Trueque trueque);

    @Mapping(source = "entregaId", target = "entrega")
    @Mapping(source = "recibeId", target = "recibe")
    Trueque toEntity(TruequeDTO truequeDTO);

    default Trueque fromId(Long id) {
        if (id == null) {
            return null;
        }
        Trueque trueque = new Trueque();
        trueque.setId(id);
        return trueque;
    }
}
