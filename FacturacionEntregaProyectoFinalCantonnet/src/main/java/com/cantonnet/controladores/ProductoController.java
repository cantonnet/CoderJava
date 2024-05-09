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
    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.getAllProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }
    
	@Operation(summary = "Obtener producto por ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Se a encontrado el producto", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))}),
		@ApiResponse(responseCode = "404", description = "El producto no se encuentra o no existe", content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable("id") int id) {
        Producto producto = productoService.getProductoById(id);
        if (producto != null) {
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
	@Operation(summary = "Añadir nuevo producto")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Producto agregado!", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))}),
		@ApiResponse(responseCode = "500", description = "Error 500", content = @Content)})
    @PostMapping
    public ResponseEntity<Producto> saveProducto(@RequestBody Producto producto) {
        Producto savedProducto = productoService.saveProducto(producto);
        return new ResponseEntity<>(savedProducto, HttpStatus.CREATED);
    }
    
	@Operation(summary = "Eliminar un producto existente")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "Producto eliminado con exito", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))}),
		@ApiResponse(responseCode = "404", description = "El producto no se encuentra en la base", content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable("id") int id) {
        productoService.deleteProducto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
