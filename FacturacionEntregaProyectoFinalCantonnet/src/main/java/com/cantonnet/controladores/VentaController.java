package com.cantonnet.controladores;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cantonnet.modelo.*;
import com.cantonnet.servicios.*;
import com.cantonnet.repositorios.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/ventas")
@Tag(name = "Controlar las ventas", description = "Endpoints de ventas")
public class VentaController {
	
	@Autowired
    private VentaService ventaService;
    
	
	// Retorna un listado de todas las ventas.
		@Operation(summary = "Desplegar listado de ventas")
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Listado de ventas desplegado con exito", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class))}),
			@ApiResponse(responseCode = "500", description = "Error 500", content = @Content)})
		@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Venta>> getAllVentas() {
        List<Venta> ventas = ventaService.getAllVentas();
        return new ResponseEntity<>(ventas, HttpStatus.OK);
    }
    
		// Retorna una venta específica según su ID.
		@Operation(summary = "Obtener ventas por su ID")
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Venta encontrada!", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class))}),
			@ApiResponse(responseCode = "404", description = "Venta no se encuentra o no existe", content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable("id") int id) {
        Venta venta = ventaService.getVentaById(id);
        if (venta != null) {
            return new ResponseEntity<>(venta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
		// Crea una nueva venta con los datos proporcionados.
		@Operation(summary = "Crear una nueva venta")
		@ApiResponses(value = {
			    @ApiResponse(responseCode = "201", description = "Venta creada correctamente", content = {
			            @Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class))}),
			    @ApiResponse(responseCode = "500", description = "Error 500", content = @Content)})
    @PostMapping("/save")
    public ResponseEntity<Venta> saveVenta(@RequestBody Venta venta) {
        Venta savedVenta = ventaService.saveVenta(venta);
        return new ResponseEntity<>(savedVenta, HttpStatus.CREATED);
    }
    
		// Elimina una venta existente según su ID.
		@Operation(summary = "Eliminar venta existente")
		@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Venta eliminada con exito", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class))}),
			@ApiResponse(responseCode = "404", description = "Venta no se encuentra o no existe", content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable("id") int id) {
        ventaService.deleteVenta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
}
