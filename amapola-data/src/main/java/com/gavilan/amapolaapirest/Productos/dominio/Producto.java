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

@Entity(name = "productos")
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

    @NotNull
    private Double precio;

    /*
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_precio_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TipoPrecio tipoPrecio;
     */

    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date fechaCreacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bolsa_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Bolsa bolsa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "estado_nombre")
    private Estado estado;

    public Integer getBolsas() {

        if (bolsa == null) {
            return null;
        }

        return this.calcularCantidadBolsas(this.bolsa.getCapacidad(), this.stock);
    }

    private Integer calcularCantidadBolsas(Integer capacidad, Integer stock) {

        return stock / capacidad;
    }
}
