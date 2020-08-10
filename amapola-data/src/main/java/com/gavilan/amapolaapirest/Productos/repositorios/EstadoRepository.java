package com.gavilan.amapolaapirest.Productos.repositorios;

import com.gavilan.amapolaapirest.Productos.dominio.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, String> {

}
