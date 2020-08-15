package com.gavilan.amapolaapirest.Productos.servicios;

import com.gavilan.amapolaapirest.Productos.dominio.Categoria;
import com.gavilan.amapolaapirest.Productos.dominio.Producto;
import com.gavilan.amapolaapirest.Productos.dominio.Subcategoria;

import java.util.List;

public interface ProductoService {

    Producto registrarProducto(Producto producto);

    List<Producto> obtenerProductos();

    Producto obtenerProducto(Long productoId);


    List<Categoria> obtenerCategorias();

    Categoria obtenerCategoria(Long categoriaId);

    List<Producto> obtenerProductosPorCategoria(Long categoriaId);

    List<Subcategoria> obtenerSubcategorias();

    Subcategoria obtenerSubcategoria(Long subcategoriaId);

    List<Producto> obtenerProductosPorSubcategoria(Long subcategoriaId);

    List<Subcategoria> obtenerSubcategoriasPorCategoria(Long categoriaId);
}
