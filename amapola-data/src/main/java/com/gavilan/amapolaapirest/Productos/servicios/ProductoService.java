package com.gavilan.amapolaapirest.Productos.servicios;

import com.gavilan.amapolaapirest.Productos.dominio.Producto;

import java.util.List;

public interface ProductoService {

    Producto registrarProducto(Producto producto);

    List<Producto> obtenerProductos();
}
