package com.cantonnet.controladores;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cantonnet.modelo.Producto;
import com.cantonnet.servicios.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/productos")
@Tag(name = "Gestionar productos", description = "Endpoints de productos")
public class ProductoController {
	
	@Autowired
	private ProductoService productoService;

	@Operation(summary = "Obtener listado de productos")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Se a desplegado la lista de productos", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))}),
		@ApiResponse(responseCode = "500", description = "Error 500", content = @Content)})
	@GetMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Producto>> listarProducto() {
		try {
			List<Producto> producto = productoService.listarProducto();
			return new ResponseEntity<>(producto, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Obtener producto por ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Se a encontrado el producto", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))}),
		@ApiResponse(responseCode = "404", description = "El producto no se encuentra o no existe", content = @Content)})
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Producto> mostrarProductoPorId(@PathVariable("id") int id) {
		try {
			Producto producto = productoService.mostrarProductoPorId(id);
			if(producto != null) {
				return new ResponseEntity<>(producto, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Añadir nuevo producto")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Producto agregado!", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))}),
		@ApiResponse(responseCode = "500", description = "Error 500", content = @Content)})
	@PostMapping(value = "/agregar", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) {
		productoService.agregarProducto(producto);
		return new ResponseEntity<>(producto, HttpStatus.CREATED);
	}
	
	@Operation(summary = "Editar un producto existente")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Producto editado correctamente", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))}),
		@ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content)})
	@PutMapping(value = "/{id}/editar", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Producto> editarProductoPorId(@PathVariable("id") int id, @RequestBody Producto producto) {
		try {
			Producto productoEditado = productoService.editarProductoPorId(id, producto);
			return new ResponseEntity<>(productoEditado, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Eliminar un producto existente")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "Producto eliminado con exito", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))}),
		@ApiResponse(responseCode = "404", description = "El producto no se encuentra en la base", content = @Content)})
	@DeleteMapping(value = "/{id}/eliminar", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> eliminarProductoPorId(@PathVariable("id") int id) {
		try {
			productoService.eliminarProductoPorId(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch(EmptyResultDataAccessException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
