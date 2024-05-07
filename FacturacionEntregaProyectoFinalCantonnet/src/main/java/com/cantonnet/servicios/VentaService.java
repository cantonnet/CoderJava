package com.cantonnet.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.cantonnet.modelo.*;
import com.cantonnet.repositorios.*;

import jakarta.transaction.Transactional;

@Service
public class VentaService {
	
	@Autowired
	private VentaRepository ventaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private ComprobanteService comprobanteService;
	
	@Autowired
	private ProductoService productoService;
	
	public List<Venta> listarVentas() {
		return ventaRepository.findAll();
	}
	
	public Venta mostrarVentaPorId(int id) {
		return ventaRepository.findById(id).orElse(null);
	}
	
	public Venta agregarVenta(Venta venta) throws Exception {
	    // Obtener el cliente de la venta
	    Cliente cliente = clienteRepository.findById(venta.getCliente().getId())
	                                       .orElseThrow(IllegalArgumentException::new);
	    
	    // Establecer el cliente en la venta
	    venta.setCliente(cliente);
	    
	    // Lista para almacenar los productos de la venta
	    List<Producto> productosVenta = new ArrayList<>();
	    
	    // Calcular el total de la venta
	    double totalVenta = 0;
	    
	    // Iterar sobre los productos del cliente
	    for (Producto producto : venta.getCliente().getProductos()) {
	        // Obtener el producto desde el repositorio
	        Producto productoActualizado = productoRepository.findById(producto.getId())
	                                                        .orElseThrow(IllegalArgumentException::new);
	        
	        // Verificar si hay suficiente stock para la venta
	        if (productoActualizado.getCantidad() < producto.getCantidad()) {
	            throw new Exception("No hay suficiente stock para el producto: " + producto.getNombreProducto());
	        }
	        
	        // Actualizar el stock del producto
	        productoService.restarStockProducto(productoActualizado.getId(), producto.getCantidad());
	        
	        // Agregar el producto a la lista de productos de la venta
	        productosVenta.add(productoActualizado);
	        
	        // Calcular el subtotal del producto y agregarlo al total de la venta
	        double subTotal = productoActualizado.getPrecio() * producto.getCantidad();
	        totalVenta += subTotal;
	    }
	    
	    // Establecer los productos y el total de la venta en la venta
	    venta.setProductos(productosVenta);
	    venta.setTotalVenta(totalVenta);
	    
	    // Crear el comprobante y asociarlo a la venta
	    Comprobante comprobante = new Comprobante();
	    comprobante.setTotal(totalVenta);
	    venta.setComprobante(comprobante);
	    crearComprobante(cliente, venta, totalVenta);
	    
	    // Guardar la venta en el repositorio y devolverla
	    return ventaRepository.save(venta);
	}
	
	public Venta crearVenta(int clienteId, int productoId, int cantidad) throws Exception {
	    // Obtener el cliente y el producto desde el repositorio
	    Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(IllegalArgumentException::new);
	    Producto producto = productoRepository.findById(productoId).orElseThrow(IllegalArgumentException::new);
	    
	    // Crear una lista de productos con el producto de la venta
	    List<Producto> productosLista = new ArrayList<>();
	    productosLista.add(producto);
	    
	    // Crear un comprobante y calcular el total de la venta
	    Comprobante comprobante = new Comprobante();
	    double precio = producto.getPrecio();
	    double totalVenta = precio * cantidad;
	    
	    // Actualizar el stock del producto y guardarlo en el repositorio
	    productoService.restarStockProducto(productoId, cantidad);
	    productoRepository.save(producto);
	    
	    // Generar un ID aleatorio para la venta
	    int id = generateRandomId();
	    
	    // Crear la venta con los datos proporcionados
	    Venta venta = new Venta(id, cantidad, totalVenta, cliente, productosLista, comprobante);
	    
	    // Crear un comprobante para la venta
	    crearComprobante(cliente, venta, totalVenta);
	    
	    // Guardar la venta en el repositorio y devolverla
	    return ventaRepository.save(venta);
	}
	
	/**
	 * Genera un ID aleatorio para las ventas.
	 * 
	 * retorna Un ID aleatorio.
	 */
	public int generateRandomId() {
	    Random random = new Random();
	    return random.nextInt(1000000);
	}
	
	/**
	 * Elimina una venta del sistema dado su ID.
	 * 
	 * parametro id es El ID de la venta a eliminar.
	 * retorna verdadero si la venta fue eliminada con éxito, false si no se encontró la venta.
	 */
	public boolean eliminarVentaPorId(int id) {
		try {
			ventaRepository.deleteById(id);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}
	
	/**
	 * Crea un comprobante para una venta.
	 * 
	 * "Cliente" cliente El cliente asociado a la venta.
	 * "Venta" venta La venta para la cual se crea el comprobante.
	 * "total" total El total de la venta.
	 *  retorna El comprobante creado.
	 */
	public Comprobante crearComprobante(Cliente cliente, Venta venta,
			double total) {
		Comprobante comprobante = new Comprobante();

		comprobante.setCliente(cliente);
		comprobante.setVenta(venta);
		comprobante.setTotal(total);
		return comprobante;
	}

}
