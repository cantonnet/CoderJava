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

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Producto mostrarProductoPorId(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto agregarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto editarProductoPorId(Integer id, Producto producto) {
        try {
            if (productoRepository.existsById(id)) {
                producto.setId(id);
                return productoRepository.save(producto);
            }
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return null;
    }

    public boolean eliminarProductoPorId(Integer id) {
        try {
            productoRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
