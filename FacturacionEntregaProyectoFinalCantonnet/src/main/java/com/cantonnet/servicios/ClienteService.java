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
    
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }
    
    public Cliente getClienteById(int id) {
        return clienteRepository.findById(id).orElse(null);
    }
    
    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
    public void deleteCliente(int id) {
        clienteRepository.deleteById(id);
    }

}
