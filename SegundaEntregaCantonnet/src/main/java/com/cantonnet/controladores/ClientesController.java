package com.cantonnet.controladores;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/cliente")
public class ClientesController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping(value="/", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Cliente>> listarCliente() {
		try {
			List<Cliente> clientes = clienteService.listarCliente();
			return new ResponseEntity<>(clientes,HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/{dni}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ClienteDatos> mostrarClientePorDNI(@PathVariable("dni") Integer dni) {
		try {
			Cliente cliente = clienteService.mostrarClientePorDNI(dni);
	        if (cliente != null) {
	        	int anios = clienteService.calcularEdadDelCliente(cliente);
	        	ClienteDatos clienteDatos = new ClienteDatos(cliente.getNombre(), cliente.getApellido(), anios);
	        	return new ResponseEntity<>(clienteDatos, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
			
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/agregar", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Cliente> agregarCliente(@RequestBody Cliente cliente) {
		
				clienteService.agregarCliente(cliente);
	        	return new ResponseEntity<>(cliente,HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}/editar", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Cliente> editarCliente(@PathVariable("id") Integer dni, @RequestBody Cliente cliente) {
		Cliente clienteEditado = clienteService.editarClientePorDNI(dni, cliente);
		if (clienteEditado != null) {
			return new ResponseEntity<>(clienteEditado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
	}

	@DeleteMapping(value = "/{id}/eliminar")
	public ResponseEntity<Void> eliminarCliente(@PathVariable("id") Integer id) {
		boolean eliminado = clienteService.eliminarClientePorDNI(id);
		if (eliminado) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	
	

}
