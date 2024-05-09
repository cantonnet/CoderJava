package com.cantonnet.servicios;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.cantonnet.modelo.*;
import com.cantonnet.repositorios.*;

@Service
public class ComprobanteService {
	
	
	@Autowired
    private ComprobanteRepository comprobanteRepository;
	
	@Autowired
	private VentaRepository ventaRepository;
    
    public List<Comprobante> getAllComprobantes() {
        return comprobanteRepository.findAll();
    }
    
    public Comprobante getComprobanteById(int id) {
        return comprobanteRepository.findById(id).orElse(null);
    }
    
    //establece los datos que vienen desde VentaService saveVenta
    public Venta saveComprobante(Venta venta, Double Total, Cliente cliente, LocalDateTime date) {
    	Comprobante comprobante = new Comprobante();
    	comprobante.setTotal(Total);
        comprobante.setFechaVenta(LocalDateTime.now());
        comprobante.setCliente(cliente);
        comprobanteRepository.save(comprobante);
        venta.setComprobante(comprobante);
        return venta;
    }
    
    public void deleteComprobante(int id) {
        comprobanteRepository.deleteById(id);
    }
    
}
