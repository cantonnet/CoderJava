package com.cantonnet.servicios;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.cantonnet.modelo.*;
import com.cantonnet.repositorios.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
public class VentaService {
	
	private final static DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm'Z'");
    private final static String URL_API = "https://worldtimeapi.org/api/timezone/America/Argentina/Buenos_Aires";
	
	@Autowired
    private VentaRepository ventaRepository;
	
	@Autowired
    private ProductoRepository productoRepository;
	
	@Autowired
    private ComprobanteRepository comprobanteRepository;
	
	@Autowired
    private ClienteRepository clienteRepository;
	
	@Autowired
	private ComprobanteService comprobanteService;
    
    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }
    
    public Venta getVentaById(int id) {
        return ventaRepository.findById(id).orElse(null);
    }
    
    public Venta saveVenta(Venta venta) {
    	// Obtener el cliente de la venta
        Cliente cliente = clienteRepository.findById(venta.getCliente().getIdCliente()).orElse(null);
        if (cliente == null) {
            throw new RuntimeException("Cliente no encontrado");
        }

        // Establecer el cliente en la venta
        venta.setCliente(cliente);

        // Lista para almacenar los productos de la venta
        List<Producto> productosVenta = new ArrayList<>();

        // Calcular el total de la venta
        Double totalVenta = 0.0;

        // Iterar sobre los productos de la venta
        for (Producto productoVenta : venta.getProductos()) {
            // Obtener el producto desde el repositorio
            Producto producto = productoRepository.findById(productoVenta.getIdProducto()).orElse(null);
            if (producto != null) {
                // Verificar si hay suficiente stock para la venta
                if (producto.getStock() >= productoVenta.getCantidad()) {
                    // Actualizar el stock del producto
                    producto.setStock(producto.getStock() - productoVenta.getCantidad());
                    // Agregar el producto a la lista de productos de la venta
                    productosVenta.add(producto);
                    // Calcular el subtotal del producto y agregarlo al total de la venta
                    totalVenta += producto.getPrecio() * productoVenta.getCantidad();
                } else {
                    throw new RuntimeException("No hay suficiente stock para el producto: " + producto.getNombreProducto());
                }
            }
        }

        // Establecer los productos y el total de la venta en la venta
        venta.setProductos(productosVenta);
        venta.setTotalVenta(totalVenta);
        
        
        // Crear el comprobante y asociarlo a la venta
        
        return ventaRepository.save((comprobanteService.saveComprobante(venta, totalVenta, cliente, fechaActual())));
    }
    
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

    
    public void deleteVenta(int id) {
        ventaRepository.deleteById(id);
    }

}
