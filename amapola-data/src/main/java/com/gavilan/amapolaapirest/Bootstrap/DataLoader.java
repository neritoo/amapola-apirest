package com.gavilan.amapolaapirest.Bootstrap;

import com.gavilan.amapolaapirest.Productos.dominio.EnStock;
import com.gavilan.amapolaapirest.Productos.dominio.Estado;
import com.gavilan.amapolaapirest.Productos.dominio.SinStock;
import com.gavilan.amapolaapirest.Productos.dominio.StockMinimo;
import com.gavilan.amapolaapirest.Productos.repositorios.EstadoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final EstadoRepository estadoRepository;
    // private final TipoPrecioRepository tipoPrecioRepository;


    public DataLoader(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Estado enStock = new EnStock();
        Estado stockMinimo = new StockMinimo();
        Estado sinStock = new SinStock();

        this.estadoRepository.save(enStock);
        this.estadoRepository.save(stockMinimo);
        this.estadoRepository.save(sinStock);

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
