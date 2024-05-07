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
	public ResponseEntity<List<Venta>> listarVentas() {
		try {
			List<Venta> ventas = ventaService.listarVentas();
			return new ResponseEntity<>(ventas, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Retorna una venta específica según su ID.
	@Operation(summary = "Obtener ventas por su ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Venta encontrada!", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class))}),
		@ApiResponse(responseCode = "404", description = "Venta no se encuentra o no existe", content = @Content)})
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> mostrarVentaPorId(@PathVariable int id) {
		try {
			Venta venta = ventaService.mostrarVentaPorId(id);
			if(venta != null) {
				return new ResponseEntity<>(venta, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Crea una nueva venta con los datos proporcionados.
	@Operation(summary = "Crear una nueva venta")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Venta creada correctamente", content = {
	            @Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class))}),
	    @ApiResponse(responseCode = "500", description = "Error 500", content = @Content)})
	@PostMapping(value = "/crear", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Venta> agregarVenta(
			@RequestParam("clienteId") String clienteIdStr,
	        @RequestParam("productoId") String productoIdStr,
	        @RequestParam("cantidad") String cantidadStr) {
	    try {
	    	int clienteId = Integer.parseInt(clienteIdStr);
	        int productoId = Integer.parseInt(productoIdStr);
	        int cantidad = Integer.parseInt(cantidadStr);
	        Venta hacerVenta = ventaService.crearVenta(clienteId, productoId, cantidad);
	        return new ResponseEntity<>(hacerVenta, HttpStatus.CREATED);
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	// Agrega una nueva venta con los datos proporcionados en el cuerpo de body.
	@Operation(summary = "Agregar nueva venta")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "venta agregada!", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class))}),
		@ApiResponse(responseCode = "500", description = "Error 500", content = @Content)})
	@PostMapping(value = "/agregar", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Venta> agregarVenta(@RequestBody Venta venta) {
		try {
			Venta hacerVenta = ventaService.agregarVenta(venta);
			return new ResponseEntity<>(hacerVenta, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Elimina una venta existente según su ID.
	@Operation(summary = "Eliminar venta existente")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "Venta eliminada con exito", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class))}),
		@ApiResponse(responseCode = "404", description = "Venta no se encuentra o no existe", content = @Content)})
	@DeleteMapping(value = "/{id}/eliminar", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> eliminarVentaPorId(@PathVariable("id") int id) {
		try {
			ventaService.eliminarVentaPorId(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch(EmptyResultDataAccessException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
