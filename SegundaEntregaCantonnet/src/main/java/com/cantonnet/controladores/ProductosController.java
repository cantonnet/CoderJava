package com.cantonnet.controladores;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cantonnet.modelo.Producto;
import com.cantonnet.servicios.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductosController {
    
    @Autowired
    private ProductoService productoService;
    
    @GetMapping(value="/", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<Producto>> listarProductos() {
        try {
            List<Producto> productos = productoService.listarProductos();
            return new ResponseEntity<>(productos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping(value="/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Producto> mostrarProductoPorId(@PathVariable("id") Integer id) {
        try {
            Producto producto = productoService.mostrarProductoPorId(id);
            if (producto != null) {
                return new ResponseEntity<>(producto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping(value = "/agregar", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) {
        productoService.agregarProducto(producto);
        return new ResponseEntity<>(producto, HttpStatus.CREATED);
    }
    
    @PutMapping(value = "/{id}/editar", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Producto> editarProducto(@PathVariable("id") Integer id, @RequestBody Producto producto) {
        Producto productoEditado = productoService.editarProductoPorId(id, producto);
        if (productoEditado != null) {
            return new ResponseEntity<>(productoEditado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}/eliminar")
    public ResponseEntity<Void> eliminarProducto(@PathVariable("id") Integer id) {
        boolean eliminado = productoService.eliminarProductoPorId(id);
        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
