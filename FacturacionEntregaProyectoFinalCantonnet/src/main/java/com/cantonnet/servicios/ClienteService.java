package com.cantonnet.servicios;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.cantonnet.modelo.Cliente;
import com.cantonnet.repositorios.ClienteRepository;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> listarClientes() {
		List<Cliente> listaClientes = clienteRepository.findAll();
		return listaClientes;
	}
	
	public Cliente mostrarClientePorId(int id) {
		Cliente cliente = clienteRepository.findById(id).orElse(null);
		return cliente;
	}
	
	public Cliente agregarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Cliente editarClientePorId(int id, Cliente cliente) {
		try {
			if(clienteRepository.existsById(id)) {
				cliente.setId(id);
				return clienteRepository.save(cliente);
			}
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
		return null;
	}
	/*
	public int calcularEdadDelCliente(Cliente cliente) {
		
	    Date fechaNacimiento = cliente.getFecha_Nacimiento();
	    Calendar fechaActual = Calendar.getInstance();
	    Calendar fechaNac = Calendar.getInstance();
	    fechaNac.setTime(fechaNacimiento);

	    int edad = fechaActual.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
	    if (fechaNac.get(Calendar.DAY_OF_YEAR) > fechaActual.get(Calendar.DAY_OF_YEAR)) {
	        edad--;
	    }
	    if (cliente.getFechaDeNacimiento() == null) {
	        return 0;
	    }
	    
	    return edad;
	}
	
	public Cliente editarClientePorDNI(Integer dni, Cliente cliente) {
		try {
			if (clienteRepository.existsById(dni)) {
				cliente.setdni(dni);
				return clienteRepository.save(cliente);
			}
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return null;
	}
	*/
	
	public boolean eliminarClientePorId(int id) {
		try {
			clienteRepository.deleteById(id);
			return true;
		} catch(EmptyResultDataAccessException e) {
			return false;
		}
	}

}
