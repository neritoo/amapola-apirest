package com.gavilan.amapolaapirest.Productos.dominio;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;

/**
 * @author Ezequiel Gavilán
 */

@Entity(name = "estados")
@Inheritance
@Data
public abstract class Estado {

    @Id
    protected String nombre;

    public abstract void descontarStock();

    public abstract void aumentarStock();
}
