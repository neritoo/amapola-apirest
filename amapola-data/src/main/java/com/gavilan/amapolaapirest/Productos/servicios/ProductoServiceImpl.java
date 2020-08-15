package com.gavilan.amapolaapirest.Productos.servicios;

import com.gavilan.amapolaapirest.Excepciones.ProductoException;
import com.gavilan.amapolaapirest.Productos.dominio.*;
import com.gavilan.amapolaapirest.Productos.repositorios.CategoriaRepository;
import com.gavilan.amapolaapirest.Productos.repositorios.ProductoRepository;
import com.gavilan.amapolaapirest.Productos.repositorios.SubcategoriaRepository;
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
    private final SubcategoriaRepository subcategoriaRepository;

    @Transactional
    @Override
    public Producto registrarProducto(Producto producto) {

        Bolsa bolsa = null;

        if (producto.getBolsa() != null) {
            bolsa = this.bolsaService.obtenerBolsa(producto.getBolsa().getId());
        }

        Subcategoria subcategoria = null;

        if (producto.getSubcategoria() != null) {
            subcategoria = this.subcategoriaRepository.findById(producto.getSubcategoria().getId())
                    .orElseThrow(() -> new ProductoException("Subcategoria no existente con ID: " + producto.getSubcategoria().getId()));
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
        nuevoProducto.setSubcategoria(subcategoria);
        nuevoProducto.setEstado(new EnStock());

        return this.productoRepository.save(nuevoProducto);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Producto> obtenerProductos() {

        return this.productoRepository.findAll();
    }

    @Override
    public List<Producto> obtenerProductosPorCategoria(Long categoriaId) {

        Categoria categoria = this.categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new ProductoException("Categoria no existente con ID: " + categoriaId));

        return this.productoRepository.findByCategoria(categoria);
    }

    @Override
    public Producto obtenerProducto(Long productoId) {
        return null;
    }

    @Override
    public Categoria obtenerCategoria(Long categoriaId) {
        return null;
    }

    @Override
    public List<Categoria> obtenerCategorias() {
        return this.categoriaRepository.findAll();
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
