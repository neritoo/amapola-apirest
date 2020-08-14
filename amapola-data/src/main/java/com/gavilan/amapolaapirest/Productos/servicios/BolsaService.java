package com.gavilan.amapolaapirest.Productos.servicios;

import com.gavilan.amapolaapirest.Productos.dominio.Bolsa;

import java.util.List;

/**
 * @author Ezequiel Gavilán
 */
public interface BolsaService {

    Bolsa registrarBolsa(Bolsa bolsa);

    List<Bolsa> obtenerBolsas();

    Bolsa obtenerBolsa(Long bolsaId);

    void eliminarBolsa(Long bolsaId);
}
