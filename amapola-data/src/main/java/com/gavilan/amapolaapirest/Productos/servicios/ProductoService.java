package com.gavilan.amapolaapirest.Productos.servicios;

import com.gavilan.amapolaapirest.Productos.dominio.Categoria;
import com.gavilan.amapolaapirest.Productos.dominio.Producto;

import java.util.List;

public interface ProductoService {

    Producto registrarProducto(Producto producto);

    List<Producto> obtenerProductos();

    List<Producto> obtenerProductosPorCategoria(Long categoriaId);

    Producto obtenerProducto(Long productoId);

    Categoria obtenerCategoria(Long categoriaId);

    List<Categoria> obtenerCategorias();
}
