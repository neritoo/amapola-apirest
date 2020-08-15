package com.gavilan.amapolaapirest.Productos.controladores;

import com.gavilan.amapolaapirest.Excepciones.ProductoException;
import com.gavilan.amapolaapirest.Productos.dominio.Producto;
import com.gavilan.amapolaapirest.Productos.servicios.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/productos/por-categoria/{categoriaId}")
    public ResponseEntity<?> obtenerProductosPorCategoria(@PathVariable Long categoriaId) {

        Map<String, Object> response = new HashMap<>();
        List<Producto> productos;

        try {
            productos = this.productoService.obtenerProductosPorCategoria(categoriaId);
        } catch (ProductoException e) {
            response.put("mensaje", "Error al obtener los productos");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (productos.size() <= 0) {
            response.put("mensaje", "No existen productos con esa categoría");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @GetMapping("/productos/por-subcategoria/{subcategoriaId}")
    public ResponseEntity<?> obtenerProductosPorSubcategoria(@PathVariable Long subcategoriaId) {

        Map<String, Object> response = new HashMap<>();
        List<Producto> productos;

        try {
            productos = this.productoService.obtenerProductosPorSubcategoria(subcategoriaId);
        } catch (ProductoException e) {
            response.put("mensaje", "Error al obtener los productos");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (productos.size() <= 0) {
            response.put("mensaje", "No existen productos con esa subcategoria");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(productos, HttpStatus.OK);
    }
}
