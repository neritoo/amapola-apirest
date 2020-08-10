package com.gavilan.amapolaapirest.Bootstrap;

import com.gavilan.amapolaapirest.Productos.dominio.EnStock;
import com.gavilan.amapolaapirest.Productos.dominio.Estado;
import com.gavilan.amapolaapirest.Productos.dominio.SinStock;
import com.gavilan.amapolaapirest.Productos.dominio.StockMinimo;
import com.gavilan.amapolaapirest.Productos.servicios.ProductoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductoService productoService;

    public DataLoader(ProductoService productoService) {
        this.productoService = productoService;
    }

    @Override
    public void run(String... args) throws Exception {

        Estado enStock = new EnStock();
        Estado stockMinimo = new StockMinimo();
        Estado sinStock = new SinStock();

        this.productoService.guardarEstado(enStock);
        this.productoService.guardarEstado(stockMinimo);
        this.productoService.guardarEstado(sinStock);
    }
}
