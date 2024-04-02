package com.coderhouse.controladores;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.modelo.Cliente;
import com.coderhouse.repositorios.ClienteRepository;

@RestController
@RequestMapping("/cliente")
public class ClientesController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping(value="/", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Cliente>> listarCliente() {
		try {
			List<Cliente> clientes = clienteRepository.findAll();
			return new ResponseEntity<>(clientes,HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/{id}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Cliente> mostrarClientePorDNI(@PathVariable("id") Integer dni) {
		try {
			Cliente cliente = clienteRepository.findById(dni).orElse(null);
	        if (cliente != null) {
	            return new ResponseEntity<>(cliente, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
			
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/agregar", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Cliente> gregarCliente(@RequestBody Cliente cliente) {
		clienteRepository.save(cliente);
		return new ResponseEntity<>(cliente,HttpStatus.CREATED);
	}
	

}
