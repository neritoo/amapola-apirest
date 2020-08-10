package com.gavilan.amapolaapirest.Productos.controladores;

import com.gavilan.amapolaapirest.Productos.servicios.ProductoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ControladorObtenerProductos {

    private final ProductoService productoService;

    public ControladorObtenerProductos(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/productos")
    public String pruebaAPIProductos() {
        return "Funciona";
    }
}
