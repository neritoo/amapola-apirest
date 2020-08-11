package com.gavilan.amapolaapirest.Productos.repositorios;

import com.gavilan.amapolaapirest.Productos.dominio.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ezequiel Gavilán
 */

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
