package com.gavilan.amapolaapirest.servicios;

import com.gavilan.amapolaapirest.Productos.dominio.Bolsa;
import com.gavilan.amapolaapirest.Productos.dominio.Producto;
import com.gavilan.amapolaapirest.Productos.repositorios.BolsaRepository;
import com.gavilan.amapolaapirest.Productos.repositorios.ProductoRepository;
import com.gavilan.amapolaapirest.Productos.servicios.ProductoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ProductoServiceImplTest {

    @Autowired
    ProductoService productoService;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    BolsaRepository bolsaRepository;

    Bolsa bolsa;

    Producto productoSinBolsa;

    Producto productoConBolsa;

    @BeforeEach
    void setUp() {

        bolsa = new Bolsa();
        bolsa.setNombre("Bolsa 1KG");
        bolsa.setCapacidad(1000);

        Bolsa bolsaGuardada = this.productoService.registrarBolsa(bolsa);
        log.info(bolsaGuardada.toString());

        productoSinBolsa = new Producto();
        productoSinBolsa.setNombre("Alfajores maicena");
        productoSinBolsa.setStock(500);
        productoSinBolsa.setPrecio(30.0);

        Producto productoSinBolsaGuardado = this.productoService.registrarProducto(productoSinBolsa);
        log.info("Producto sin bolsa: ".concat(productoSinBolsaGuardado.toString()));

        productoConBolsa = new Producto();
        productoConBolsa.setNombre("Almohaditas de frutilla");
        productoConBolsa.setStock(150);
        productoConBolsa.setPrecio(15.0);
        productoConBolsa.setBolsa(bolsaGuardada);

        Producto productoConBolsaGuardado = this.productoService.registrarProducto(productoConBolsa);
        log.info("Producto con bolsa: ".concat(productoConBolsaGuardado.toString()));
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