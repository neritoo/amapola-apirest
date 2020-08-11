package com.gavilan.amapolaapirest.Productos.servicios;

import com.gavilan.amapolaapirest.Excepciones.ProductoException;
import com.gavilan.amapolaapirest.Productos.dominio.Bolsa;
import com.gavilan.amapolaapirest.Productos.dominio.EnStock;
import com.gavilan.amapolaapirest.Productos.dominio.Estado;
import com.gavilan.amapolaapirest.Productos.dominio.Producto;
import com.gavilan.amapolaapirest.Productos.repositorios.BolsaRepository;
import com.gavilan.amapolaapirest.Productos.repositorios.EstadoRepository;
import com.gavilan.amapolaapirest.Productos.repositorios.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final EstadoRepository estadoRepository;
    private final ProductoRepository productoRepository;
    private final BolsaRepository bolsaRepository;

    public ProductoServiceImpl(EstadoRepository estadoRepository, ProductoRepository productoRepository, BolsaRepository bolsaRepository) {
        this.estadoRepository = estadoRepository;
        this.productoRepository = productoRepository;
        this.bolsaRepository = bolsaRepository;
    }

    @Override
    public Estado guardarEstado(Estado estado) {

        return this.estadoRepository.save(estado);
    }

    @Override
    public Producto registrarProducto(Producto producto) {

        Bolsa bolsa = null;

        if (producto.getBolsa() != null) {
            bolsa = this.bolsaRepository.findById(producto.getBolsa().getId())
                    .orElseThrow(() -> new ProductoException("No existe la bolsa: " + producto.getBolsa().getNombre()));
        }
        Producto nuevoProducto = new Producto();

        nuevoProducto.setNombre(producto.getNombre());
        nuevoProducto.setFechaCreacion(new Date());
        nuevoProducto.setStock(producto.getStock());
        nuevoProducto.setBolsa(bolsa);
        nuevoProducto.setPrecio(producto.getPrecio());
        nuevoProducto.setEstado(new EnStock());

        return this.productoRepository.save(nuevoProducto);
    }

    @Override
    public List<Producto> obtenerProductos() {

        return this.productoRepository.findAll();
    }

    @Override
    public Bolsa registrarBolsa(Bolsa bolsa) {

        Bolsa nuevaBolsa = new Bolsa();

        nuevaBolsa.setNombre(bolsa.getNombre());
        nuevaBolsa.setCapacidad(bolsa.getCapacidad());

        return this.bolsaRepository.save(nuevaBolsa);
    }
}
