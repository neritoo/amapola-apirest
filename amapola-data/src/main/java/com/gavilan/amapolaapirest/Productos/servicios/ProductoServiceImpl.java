package com.gavilan.amapolaapirest.Productos.servicios;

import com.gavilan.amapolaapirest.Excepciones.ProductoException;
import com.gavilan.amapolaapirest.Productos.dominio.Bolsa;
import com.gavilan.amapolaapirest.Productos.dominio.Categoria;
import com.gavilan.amapolaapirest.Productos.dominio.EnStock;
import com.gavilan.amapolaapirest.Productos.dominio.Producto;
import com.gavilan.amapolaapirest.Productos.repositorios.CategoriaRepository;
import com.gavilan.amapolaapirest.Productos.repositorios.ProductoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final BolsaService bolsaService;
    private final CategoriaRepository categoriaRepository;

    @Transactional
    @Override
    public Producto registrarProducto(Producto producto) {

        Bolsa bolsa = null;

        if (producto.getBolsa() != null) {
            bolsa = this.bolsaService.obtenerBolsa(producto.getBolsa().getId());
        }

        /*
        TipoPrecio --> Implementado a travéz de Bolsa: Si tiene bolsa: Precio en gramos; else: Precio en unidad.
        TipoPrecio tipoPrecio = null;

        if (producto.getTipoPrecio() != null) {
            tipoPrecio = this.tipoPrecioRepository.findById(producto.getTipoPrecio().getId())
                    .orElseThrow(() -> new ProductoException("No existe el tipo de precio" + producto.getTipoPrecio().getNombre()));

        }
         */

        Categoria categoria = this.categoriaRepository.findById(producto.getCategoria().getId())
                .orElseThrow(() -> new ProductoException("No existe la categoría" + producto.getCategoria().getNombre()));

        Producto nuevoProducto = new Producto();

        nuevoProducto.setNombre(producto.getNombre());
        nuevoProducto.setFechaCreacion(new Date());
        nuevoProducto.setStock(producto.getStock());
        nuevoProducto.setBolsa(bolsa);
        nuevoProducto.setPrecio(producto.getPrecio());
        // nuevoProducto.setTipoPrecio(tipoPrecio);
        nuevoProducto.setCategoria(categoria);
        nuevoProducto.setEstado(new EnStock());

        return this.productoRepository.save(nuevoProducto);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Producto> obtenerProductos() {

        return this.productoRepository.findAll();
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