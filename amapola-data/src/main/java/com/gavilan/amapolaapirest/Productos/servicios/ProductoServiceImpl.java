package com.gavilan.amapolaapirest.Productos.servicios;

import com.gavilan.amapolaapirest.Excepciones.ProductoException;
import com.gavilan.amapolaapirest.Productos.dominio.Bolsa;
import com.gavilan.amapolaapirest.Productos.dominio.EnStock;
import com.gavilan.amapolaapirest.Productos.dominio.Producto;
import com.gavilan.amapolaapirest.Productos.repositorios.BolsaRepository;
import com.gavilan.amapolaapirest.Productos.repositorios.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final BolsaRepository bolsaRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository, BolsaRepository bolsaRepository) {
        this.productoRepository = productoRepository;
        this.bolsaRepository = bolsaRepository;
    }

    @Transactional
    @Override
    public Producto registrarProducto(Producto producto) {

        Bolsa bolsa = null;

        if (producto.getBolsa() != null) {
            bolsa = this.bolsaRepository.findById(producto.getBolsa().getId())
                    .orElseThrow(() -> new ProductoException("No existe la bolsa: " + producto.getBolsa().getNombre()));
        }

        /*
        TipoPrecio --> Implementado a travéz de Bolsa: Si tiene bolsa: Precio en gramos; else: Precio en unidad.
        TipoPrecio tipoPrecio = null;

        if (producto.getTipoPrecio() != null) {
            tipoPrecio = this.tipoPrecioRepository.findById(producto.getTipoPrecio().getId())
                    .orElseThrow(() -> new ProductoException("No existe el tipo de precio" + producto.getTipoPrecio().getNombre()));

        }
         */

        Producto nuevoProducto = new Producto();

        nuevoProducto.setNombre(producto.getNombre());
        nuevoProducto.setFechaCreacion(new Date());
        nuevoProducto.setStock(producto.getStock());
        nuevoProducto.setBolsa(bolsa);
        nuevoProducto.setPrecio(producto.getPrecio());
        // nuevoProducto.setTipoPrecio(tipoPrecio);
        nuevoProducto.setCategoria(producto.getCategoria());
        nuevoProducto.setEstado(new EnStock());

        return this.productoRepository.save(nuevoProducto);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Producto> obtenerProductos() {

        return this.productoRepository.findAll();
    }

    @Transactional
    @Override
    public Bolsa registrarBolsa(Bolsa bolsa) {

        Bolsa nuevaBolsa = new Bolsa();

        nuevaBolsa.setNombre(bolsa.getNombre());
        nuevaBolsa.setCapacidad(bolsa.getCapacidad());

        return this.bolsaRepository.save(nuevaBolsa);
    }

    /*
    Método a eliminar: Para probar el calcularSubTotal de la venta antes de crear la venta.
    @Override
    public Double calcularSubTotal(Producto producto, Integer cantidad) {

        double subTotal;

        if (producto.getBolsa() != null) {
            subTotal = (producto.getPrecio() * cantidad) / 100;
        } else {
            subTotal = producto.getPrecio() * cantidad;
        }

        return subTotal;
    }
     */
}
