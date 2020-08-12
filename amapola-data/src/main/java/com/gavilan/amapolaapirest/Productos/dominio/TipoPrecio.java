package com.gavilan.amapolaapirest.Productos.dominio;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import javax.persistence.*;

@Deprecated
@Entity
@Data
@Inheritance
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Gramos100.class, name = "Gramos100"),
        @JsonSubTypes.Type(value = Unidad.class, name = "Unidad")
})
@Table(name = "tipos_precio")
public abstract class TipoPrecio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String nombre;

    abstract Double calcularSubTotal();
}
