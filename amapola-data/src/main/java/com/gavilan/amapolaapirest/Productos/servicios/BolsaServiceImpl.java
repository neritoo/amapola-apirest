package com.gavilan.amapolaapirest.Productos.servicios;

import com.gavilan.amapolaapirest.Excepciones.ProductoException;
import com.gavilan.amapolaapirest.Productos.dominio.Bolsa;
import com.gavilan.amapolaapirest.Productos.repositorios.BolsaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Ezequiel Gavilán
 */

@Service
@AllArgsConstructor
public class BolsaServiceImpl implements BolsaService {

    private final BolsaRepository bolsaRepository;

    @Transactional
    @Override
    public Bolsa registrarBolsa(Bolsa bolsa) {

        Bolsa nuevaBolsa = new Bolsa();

        nuevaBolsa.setNombre(bolsa.getNombre());
        nuevaBolsa.setCapacidad(bolsa.getCapacidad());

        return this.bolsaRepository.save(nuevaBolsa);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Bolsa> obtenerBolsas() {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Bolsa obtenerBolsa(Long bolsaId) {
        return this.bolsaRepository.findById(bolsaId)
                .orElseThrow(() -> new ProductoException("No existe la bolsa con ID" +
                        bolsaId.toString()));
    }

    @Transactional
    @Override
    public void eliminarBolsa(Long bolsaId) {

    }
}
