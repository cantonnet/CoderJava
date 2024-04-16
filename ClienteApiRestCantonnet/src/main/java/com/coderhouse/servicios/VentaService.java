package com.coderhouse.servicios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.modelo.Venta;
import com.coderhouse.modelo.Producto;
import com.coderhouse.repositorios.VentaRepository;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    public Venta mostrarVentaPorId(Integer id) {
        return ventaRepository.findById(id).orElse(null);
    }

    public Venta agregarVenta(Venta venta) {
        calcularTotalVenta(venta);
        return ventaRepository.save(venta);
    }

    private void calcularTotalVenta(Venta venta) {
        double total = 0.0;
        if (venta.getProductos() != null) {
            for (Producto producto : venta.getProductos()) {
                total += producto.getPrecio();
            }
        }
        venta.setTotal(total);
    }

    public Venta actualizarVenta(Integer id, Venta venta) {
        if (ventaRepository.existsById(id)) {
            venta.setId(id);
            calcularTotalVenta(venta);
            return ventaRepository.save(venta);
        }
        return null;
    }

    public boolean eliminarVenta(Integer id) {
        try {
            ventaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}