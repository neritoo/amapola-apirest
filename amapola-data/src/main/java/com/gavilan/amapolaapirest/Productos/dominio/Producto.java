package com.gavilan.amapolaapirest.Productos.dominio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Ezequiel Gavilán
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto implements Serializable {

    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    private Integer stock;

    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bolsa_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Bolsa bolsa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "nombre")
    private Estado estado;
}
