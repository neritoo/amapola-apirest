package com.gavilan.amapolaapirest.Productos.repositorios;

import com.gavilan.amapolaapirest.Productos.dominio.Categoria;
import com.gavilan.amapolaapirest.Productos.dominio.Producto;
import com.gavilan.amapolaapirest.Productos.dominio.Subcategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ezequiel Gavilán
 */

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByCategoria(Categoria categoria);

    List<Producto> findBySubcategoria(Subcategoria subcategoria);
}
