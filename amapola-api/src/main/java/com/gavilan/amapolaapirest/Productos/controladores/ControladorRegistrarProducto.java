package com.gavilan.amapolaapirest.Productos.controladores;

import com.gavilan.amapolaapirest.Excepciones.ProductoException;
import com.gavilan.amapolaapirest.Productos.dominio.Producto;
import com.gavilan.amapolaapirest.Productos.servicios.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ControladorRegistrarProducto {

    private final ProductoService productoService;

    public ControladorRegistrarProducto(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("/productos")
    public ResponseEntity<?> nuevoProducto(@Valid @RequestBody Producto producto) {

        Map<String, Object> response = new HashMap<>();
        Producto nuevoProducto;

        try {
            nuevoProducto = this.productoService.registrarProducto(producto);
        } catch (ProductoException e) {
            response.put("mensaje", "Error al guardar producto");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }
}
