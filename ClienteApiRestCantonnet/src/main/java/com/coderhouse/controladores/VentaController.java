package com.coderhouse.controladores;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.coderhouse.modelo.Venta;
import com.coderhouse.servicios.VentaService;

@RestController
@RequestMapping("/venta")
public class VentaController {
    
    @Autowired
    private VentaService ventaService;

    @GetMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Venta>> listarVentas() {
        try {
            List<Venta> ventas = ventaService.listarVentas();
            return new ResponseEntity<>(ventas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Venta> obtenerVenta(@PathVariable("id") Integer id) {
        Venta venta = ventaService.mostrarVentaPorId(id);
        if (venta != null) {
            return new ResponseEntity<>(venta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/agregar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Venta> agregarVenta(@RequestBody Venta venta) {
        Venta nuevaVenta = ventaService.agregarVenta(venta);
        return new ResponseEntity<>(nuevaVenta, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}/editar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Venta> editarVenta(@PathVariable("id") Integer id, @RequestBody Venta venta) {
        Venta ventaActualizada = ventaService.actualizarVenta(id, venta);
        if (ventaActualizada != null) {
            return new ResponseEntity<>(ventaActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}/eliminar")
    public ResponseEntity<Void> eliminarVenta(@PathVariable("id") Integer id) {
        boolean eliminado = ventaService.eliminarVenta(id);
        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}