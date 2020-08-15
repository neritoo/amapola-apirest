package com.gavilan.amapolaapirest.Bootstrap;

import com.gavilan.amapolaapirest.Excepciones.ProductoException;
import com.gavilan.amapolaapirest.Productos.dominio.*;
import com.gavilan.amapolaapirest.Productos.repositorios.CategoriaRepository;
import com.gavilan.amapolaapirest.Productos.repositorios.EstadoRepository;
import com.gavilan.amapolaapirest.Productos.repositorios.SubcategoriaRepository;
import com.gavilan.amapolaapirest.Productos.servicios.BolsaService;
import com.gavilan.amapolaapirest.Productos.servicios.ProductoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final EstadoRepository estadoRepository;
    private final ProductoService productoService;
    private final BolsaService bolsaService;
    private final CategoriaRepository categoriaRepository;
    private final SubcategoriaRepository subcategoriaRepository;
    // private final TipoPrecioRepository tipoPrecioRepository;


    public DataLoader(EstadoRepository estadoRepository, ProductoService productoService, BolsaService bolsaService, CategoriaRepository categoriaRepository, SubcategoriaRepository subcategoriaRepository) {
        this.estadoRepository = estadoRepository;
        this.productoService = productoService;
        this.bolsaService = bolsaService;
        this.categoriaRepository = categoriaRepository;
        this.subcategoriaRepository = subcategoriaRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Estado enStock = new EnStock();
        Estado stockMinimo = new StockMinimo();
        Estado sinStock = new SinStock();

        this.estadoRepository.save(enStock);
        this.estadoRepository.save(stockMinimo);
        this.estadoRepository.save(sinStock);

        Bolsa bolsa = new Bolsa();
        bolsa.setNombre("Bolsa 1KG");
        bolsa.setCapacidad(1000);
        this.bolsaService.registrarBolsa(bolsa);

        Producto alfajor = new Producto();
        alfajor.setNombre("Alfajores maicena");
        alfajor.setStock(500);
        alfajor.setPrecio(30.0);
        alfajor.setCategoria(this.categoriaRepository.getOne(2L));
        this.productoService.registrarProducto(alfajor);

        Producto hamburguesa = new Producto();
        hamburguesa.setNombre("Hamburguesa de lenteja");
        hamburguesa.setStock(250);
        hamburguesa.setPrecio(70.0);
        hamburguesa.setCategoria(this.categoriaRepository.getOne(3L));
        Subcategoria subcategoria = this.subcategoriaRepository.findById(1L).orElseThrow(() -> new ProductoException("Error subcategoria"));
        hamburguesa.setSubcategoria(subcategoria);
        this.productoService.registrarProducto(hamburguesa);

        /*
        TipoPrecio gr100 = new Gramos100();
        TipoPrecio unidad = new Unidad();

        gr100.setNombre("100Gr");
        unidad.setNombre("Unidad");

        this.tipoPrecioRepository.save(gr100);
        this.tipoPrecioRepository.save(unidad);

         */
    }
}
