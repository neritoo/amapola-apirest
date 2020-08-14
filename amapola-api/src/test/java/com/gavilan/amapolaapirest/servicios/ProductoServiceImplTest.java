package com.gavilan.amapolaapirest.servicios;

import com.gavilan.amapolaapirest.Productos.dominio.Bolsa;
import com.gavilan.amapolaapirest.Productos.dominio.Categoria;
import com.gavilan.amapolaapirest.Productos.dominio.Producto;
import com.gavilan.amapolaapirest.Productos.repositorios.CategoriaRepository;
import com.gavilan.amapolaapirest.Productos.repositorios.ProductoRepository;
import com.gavilan.amapolaapirest.Productos.servicios.BolsaService;
import com.gavilan.amapolaapirest.Productos.servicios.ProductoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class ProductoServiceImplTest {

    @Autowired
    ProductoService productoService;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    BolsaService bolsaService;

    Bolsa bolsa;

    Producto productoSinBolsa;

    Producto productoConBolsa;

    @BeforeEach
    void setUp() {

        bolsa = new Bolsa();
        bolsa.setNombre("Bolsa 1KG");
        bolsa.setCapacidad(1000);

        Bolsa bolsaGuardada = this.bolsaService.registrarBolsa(bolsa);
        log.info(bolsaGuardada.toString());

        productoSinBolsa = new Producto();
        productoSinBolsa.setNombre("Alfajores maicena");
        productoSinBolsa.setStock(500);
        productoSinBolsa.setPrecio(30.0);
        productoSinBolsa.setCategoria(this.categoriaRepository.getOne(2L));

        Producto productoSinBolsaGuardado = this.productoService.registrarProducto(productoSinBolsa);
        log.info("Producto sin bolsa: ".concat(productoSinBolsaGuardado.toString()));

        productoConBolsa = new Producto();
        productoConBolsa.setNombre("Almohaditas de frutilla");
        productoConBolsa.setStock(150);
        productoConBolsa.setPrecio(15.0);
        productoConBolsa.setBolsa(bolsaGuardada);
        productoConBolsa.setCategoria(this.categoriaRepository.getOne(2L));

        Producto productoConBolsaGuardado = this.productoService.registrarProducto(productoConBolsa);
        log.info("Producto con bolsa: ".concat(productoConBolsaGuardado.toString()));
    }

    @Test
    void obtenerCategorias() {
        List<Categoria> categorias = this.productoService.obtenerCategorias();

        log.info(categorias.toString());
    }

    @Test
    void obtenerProductosPorCategoria() {
        List<Producto> productosPorCategoria = this.productoService.obtenerProductosPorCategoria(2L);

        log.info(productosPorCategoria.toString());
    }

    @Test
    void obtenerProductos() {

        log.info(this.productoService.obtenerProductos().toString());
    }

    /*
    Test a eliminar.
    @Test
    void calcularSubTotal() {
        Double subtotal;

        subtotal = this.productoService.calcularSubTotal(productoSinBolsa, 15);

        log.info("Sin bolsa: ".concat(subtotal.toString()));

        subtotal = this.productoService.calcularSubTotal(productoConBolsa, 200);

        log.info("Con bolsa: ".concat(subtotal.toString()));

    }

     */
}
