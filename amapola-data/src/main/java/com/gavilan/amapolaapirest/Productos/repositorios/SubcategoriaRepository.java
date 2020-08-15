package com.gavilan.amapolaapirest.Productos.repositorios;

import com.gavilan.amapolaapirest.Productos.dominio.Subcategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoriaRepository extends JpaRepository<Subcategoria, Long> {

}
