package com.gavilan.amapolaapirest.Productos.controladores;

import com.gavilan.amapolaapirest.Productos.dominio.Producto;
import com.gavilan.amapolaapirest.Productos.servicios.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ControladorObtenerProductos {

    private final ProductoService productoService;

    public ControladorObtenerProductos(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/productos")
    public ResponseEntity<?> pruebaAPIProductos() {

        List<Producto> productos = this.productoService.obtenerProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }
}
