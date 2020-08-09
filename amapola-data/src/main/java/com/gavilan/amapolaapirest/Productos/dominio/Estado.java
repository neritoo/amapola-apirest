package com.gavilan.amapolaapirest.Productos.dominio;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Ezequiel Gavilán
 */

@Entity
@Data
public abstract class Estado implements Serializable {

    private final static long serialVersionUID = 1L;

    @Id
    private String nombre;

}
