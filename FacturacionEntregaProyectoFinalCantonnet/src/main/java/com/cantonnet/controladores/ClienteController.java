package com.cantonnet.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cantonnet.modelo.Cliente;
import com.cantonnet.modelo.ClienteDatos;
import com.cantonnet.servicios.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Gestionar de Clientes", description = "Endpoints de clientes")
public class ClienteController {
	
	@Autowired
    private ClienteService clienteService;
    
	@Operation(summary = "Obtener lista de clientes")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Lista de clientes fue obtenida correctamente", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))}),
		@ApiResponse(responseCode = "500", description = "Error 500", content = @Content)})
    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        try {
        	 List<Cliente> clientes = clienteService.getAllClientes();
             return new ResponseEntity<>(clientes, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
	@Operation(summary = "Buscar cliente por id")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Cliente encontrado", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))}),
		@ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") int id) {
        Cliente cliente = clienteService.getClienteById(id);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @Operation(summary = "Agregar cliente")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Cliente agregado!", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))}),
		@ApiResponse(responseCode = "500", description = "Error 500", content = @Content)})
    @PostMapping
    public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente) {
        Cliente savedCliente = clienteService.saveCliente(cliente);
        return new ResponseEntity<>(savedCliente, HttpStatus.CREATED);
    }
    
    
    @Operation(summary = "Eliminar cliente")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "Cliente eliminado!", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))}),
		@ApiResponse(responseCode = "404", description = "Cliente no se encuentra o no existe", content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable("id") int id) {
        clienteService.deleteCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}