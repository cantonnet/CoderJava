package com.cantonnet.servicios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.cantonnet.modelo.Producto;
import com.cantonnet.repositorios.ProductoRepository;

@Service
public class ProductoService {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	public List<Producto> listarProducto() {
		return productoRepository.findAll();
	}
	
	public Producto mostrarProductoPorId(int id) {
		return productoRepository.findById(id).orElse(null);
	}
	
	public Producto agregarProducto(Producto producto) {
		return productoRepository.save(producto);
	}
	
	public Producto editarProductoPorId(int id, Producto producto) {
		try {
			if(productoRepository.existsById(id) && producto.getPrecio() > 0) {
				producto.setId(id); 
				return productoRepository.save(producto);
			}
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
		return null;
	}
	
	public boolean eliminarProductoPorId(int id) {
		try {
			productoRepository.deleteById(id);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}
	
	public void restarStockProducto(int id, int cantidad) throws Exception {
		Producto producto = productoRepository.findById(id).orElse(null);
		try {
			if(producto != null && cantidad > 0) {
				if(producto.getStock() >= cantidad) {
					producto.setStock(producto.getStock() - cantidad);
					productoRepository.save(producto);
				}
			}
		} catch (EmptyResultDataAccessException e) {
			throw new Exception(e);
		}
	}

}
