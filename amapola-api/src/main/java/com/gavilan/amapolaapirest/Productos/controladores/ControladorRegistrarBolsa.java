package com.gavilan.amapolaapirest.Productos.controladores;

import com.gavilan.amapolaapirest.Excepciones.ProductoException;
import com.gavilan.amapolaapirest.Productos.dominio.Bolsa;
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
public class ControladorRegistrarBolsa {

    private final ProductoService productoService;

    public ControladorRegistrarBolsa(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("/productos/bolsas")
    public ResponseEntity<?> nuevaBolsa(@Valid @RequestBody Bolsa bolsa) {

        Map<String, Object> response = new HashMap<>();
        Bolsa nuevaBolsa;

        try {
            nuevaBolsa = this.productoService.registrarBolsa(bolsa);
        } catch (ProductoException e) {
            response.put("mensaje", "Error al guardar la bolsa");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(nuevaBolsa, HttpStatus.CREATED);
    }
}
