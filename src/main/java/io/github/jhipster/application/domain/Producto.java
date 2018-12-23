package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import io.github.jhipster.application.domain.enumeration.TipoOferta;

/**
 * Entidad Producto
 * @author Roberto Tapia
 */
@ApiModel(description = "Entidad Producto @author Roberto Tapia")
@Entity
@Table(name = "producto")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @NotNull
    @Size(max = 100)
    @Column(name = "descripcion", length = 100, nullable = false)
    private String descripcion;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoOferta tipo;

    @Lob
    @Column(name = "detalle")
    private String detalle;

    @NotNull
    @Column(name = "valor", nullable = false)
    private Integer valor;

    @OneToOne    @JoinColumn(unique = true)
    private User user;

    @OneToMany(mappedBy = "producto")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Imagen> imagenProductos = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Producto fecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Producto descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoOferta getTipo() {
        return tipo;
    }

    public Producto tipo(TipoOferta tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(TipoOferta tipo) {
        this.tipo = tipo;
    }

    public String getDetalle() {
        return detalle;
    }

    public Producto detalle(String detalle) {
        this.detalle = detalle;
        return this;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Integer getValor() {
        return valor;
    }

    public Producto valor(Integer valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public User getUser() {
        return user;
    }

    public Producto user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Imagen> getImagenProductos() {
        return imagenProductos;
    }

    public Producto imagenProductos(Set<Imagen> imagens) {
        this.imagenProductos = imagens;
        return this;
    }

    public Producto addImagenProducto(Imagen imagen) {
        this.imagenProductos.add(imagen);
        imagen.setProducto(this);
        return this;
    }

    public Producto removeImagenProducto(Imagen imagen) {
        this.imagenProductos.remove(imagen);
        imagen.setProducto(null);
        return this;
    }

    public void setImagenProductos(Set<Imagen> imagens) {
        this.imagenProductos = imagens;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Producto producto = (Producto) o;
        if (producto.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), producto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Producto{" +
            "id=" + getId() +
            ", fecha='" + getFecha() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", tipo='" + getTipo() + "'" +
            ", detalle='" + getDetalle() + "'" +
            ", valor=" + getValor() +
            "}";
    }
}
