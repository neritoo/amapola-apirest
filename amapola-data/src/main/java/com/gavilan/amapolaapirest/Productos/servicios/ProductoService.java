package com.gavilan.amapolaapirest.Productos.servicios;

import com.gavilan.amapolaapirest.Productos.dominio.Bolsa;
import com.gavilan.amapolaapirest.Productos.dominio.Estado;
import com.gavilan.amapolaapirest.Productos.dominio.Producto;

import java.util.List;

public interface ProductoService {

    Estado guardarEstado(Estado estado);

    Producto registrarProducto(Producto producto);

    List<Producto> obtenerProductos();

    Bolsa registrarBolsa(Bolsa bolsa);
}
