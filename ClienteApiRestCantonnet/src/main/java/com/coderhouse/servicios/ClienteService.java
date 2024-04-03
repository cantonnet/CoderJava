package com.coderhouse.servicios;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.coderhouse.modelo.Cliente;
import com.coderhouse.repositorios.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> listarCliente() {
		return clienteRepository.findAll();
	}

	public Cliente mostrarClientePorDNI(Integer dni) {
		return clienteRepository.findByDni(dni).orElse(null);
	}

	public Cliente agregarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	
	public int calcularEdadDelCliente(Cliente cliente) {
		
	    java.util.Date fechaNacimiento = cliente.getFechaDeNacimiento();
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

	public boolean eliminarClientePorDNI(Integer dni) {
		try {
			clienteRepository.deleteById(dni);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}
	
}
