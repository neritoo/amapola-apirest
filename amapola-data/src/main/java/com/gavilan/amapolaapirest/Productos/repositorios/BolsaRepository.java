package com.gavilan.amapolaapirest.Productos.repositorios;

import com.gavilan.amapolaapirest.Productos.dominio.Bolsa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ezequiel Gavilán
 */

@Repository
public interface BolsaRepository extends JpaRepository<Bolsa, Long> {

}
