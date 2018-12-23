package io.github.jhipster.application.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.application.domain.enumeration.Estado;

/**
 * A DTO for the Trueque entity.
 */
public class TruequeDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate fecha;

    private Estado estado;

    private Long entregaId;

    private Long recibeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Long getEntregaId() {
        return entregaId;
    }

    public void setEntregaId(Long productoId) {
        this.entregaId = productoId;
    }

    public Long getRecibeId() {
        return recibeId;
    }

    public void setRecibeId(Long productoId) {
        this.recibeId = productoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TruequeDTO truequeDTO = (TruequeDTO) o;
        if (truequeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), truequeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TruequeDTO{" +
            "id=" + getId() +
            ", fecha='" + getFecha() + "'" +
            ", estado='" + getEstado() + "'" +
            ", entrega=" + getEntregaId() +
            ", recibe=" + getRecibeId() +
            "}";
    }
}
