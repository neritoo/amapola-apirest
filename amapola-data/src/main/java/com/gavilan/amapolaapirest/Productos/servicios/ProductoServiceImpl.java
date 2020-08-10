package com.gavilan.amapolaapirest.Productos.servicios;

import com.gavilan.amapolaapirest.Productos.dominio.Estado;
import com.gavilan.amapolaapirest.Productos.repositorios.EstadoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final EstadoRepository estadoRepository;

    public ProductoServiceImpl(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @Override
    public Estado guardarEstado(Estado estado) {

        return this.estadoRepository.save(estado);
    }
}
