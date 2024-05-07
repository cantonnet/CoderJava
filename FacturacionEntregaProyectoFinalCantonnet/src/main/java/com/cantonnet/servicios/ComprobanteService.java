package com.cantonnet.servicios;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
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
	
	private final static DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm'Z'");
    private final static String URL_API = "https://worldtimeapi.org/api/timezone/America/Argentina/Buenos_Aires";
	
	@Autowired
	private ComprobanteRepository comprobanteRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private VentaRepository ventaRepository;
	
	public List<Comprobante> listarComprobantes() {
		List<Comprobante> listaComprobantes = comprobanteRepository.findAll();
		return listaComprobantes;
	}
	
	public Comprobante mostrarComprobantePorId(int id) {
		Comprobante comprobante = comprobanteRepository.findById(id).orElse(null);
		return comprobante;
	}
	
	// Registra un comprobante de venta asociado a un cliente y una venta específica.
	public Comprobante registrarComprobante(Comprobante comprobante) {
		Cliente cliente = clienteRepository.findById(comprobante.getCliente().getId()).orElseThrow(IllegalArgumentException::new);
		comprobante.setCliente(cliente);
		Venta venta = ventaRepository.findById(comprobante.getVenta().getId()).orElseThrow(IllegalArgumentException::new);
		comprobante.setVenta(venta);
		comprobante.setTotal(venta.getTotalVenta());
		comprobante.setFechaVenta(fechaActual());
		return comprobanteRepository.save(comprobante);
	}
	
	 // Reduce la cantidad de un producto del stock, verificando si hay suficiente stock disponible.
	public void quitarProductoDeStock(int id, int cantidad) throws Exception {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        
        if (producto.getStock() >= cantidad) {
            producto.setStock(producto.getStock() - cantidad);
            productoRepository.save(producto);
        } else {
            throw new IllegalArgumentException("Stock insuficiente para realizar la operación");
        }
    }
	
	// Obtener la fecha desde un servicio REST externo y si no obtiene la fecha local
	public LocalDateTime fechaActual() {
    	LocalDateTime fecha;
    	try {
    		RestTemplate restTemplate = new RestTemplate();
    		ResponseEntity<String> sentencia = restTemplate.getForEntity(URL_API, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(sentencia.getBody());
            String fechaEnString = root.path("DateTime").asText();
            fecha = LocalDateTime.parse(fechaEnString, FORMATO);
    	} catch (Exception e) {
    		fecha = LocalDateTime.now();
    	}
    	return fecha;
    }

}
