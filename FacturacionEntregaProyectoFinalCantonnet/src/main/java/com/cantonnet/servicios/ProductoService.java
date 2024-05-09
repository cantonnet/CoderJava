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
    
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }
    
    public Producto getProductoById(int id) {
        return productoRepository.findById(id).orElse(null);
    }
    
    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }
    
    public void deleteProducto(int id) {
        productoRepository.deleteById(id);
    }

}
