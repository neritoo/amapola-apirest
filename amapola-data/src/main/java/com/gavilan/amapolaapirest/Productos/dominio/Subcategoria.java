package com.gavilan.amapolaapirest.Productos.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Ezequiel Gavilán
 */

@Entity
@Table(name = "subcategorias")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subcategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;
}
