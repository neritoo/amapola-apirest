package com.gavilan.amapolaapirest.Autenticación.repositorios;

import com.gavilan.amapolaapirest.Autenticación.dominio.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

}
