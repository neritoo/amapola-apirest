package com.gavilan.amapolaapirest.Productos.controladores;

import com.gavilan.amapolaapirest.Excepciones.ProductoException;
import com.gavilan.amapolaapirest.Productos.dominio.Subcategoria;
import com.gavilan.amapolaapirest.Productos.servicios.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ControladorObtenerSubcategorias {

    private final ProductoService productoService;

    @GetMapping("/productos/subcategorias")
    public ResponseEntity<?> obtenerSubcategorias() {

        Map<String, Object> response = new HashMap<>();
        List<Subcategoria> subcategorias;

        try {
            subcategorias = this.productoService.obtenerSubcategorias();
        } catch (ProductoException e) {
            response.put("mensaje", "Error al obtener las subcategorias");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (subcategorias.size() <= 0) {
            response.put("mensaje", "No existen subcategorias en la base de datos");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(subcategorias, HttpStatus.OK);
    }
}
