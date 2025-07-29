package cantonnet.inventario.servicio;

import cantonnet.inventario.modelo.Producto;
import cantonnet.inventario.repositorio.IProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicio implements IProductoServicio {

    @Autowired
    private IProductoRepositorio productoRepositorio;

    @Override
    public List<Producto> listarProductos() {
        return this.productoRepositorio.findAll();
    }

    @Override
    public Producto buscarProductoPorId(Integer idProducto) {
        Producto producto = this.productoRepositorio.findById(idProducto).orElse(null);
        return producto;
    }

    @Override
    public Producto guardarProducto(Producto producto) {
    this.productoRepositorio.save(producto);
        return producto;
    }

    @Override
    public void eliminarProducto(Integer idProducto) {
    this.productoRepositorio.deleteById(idProducto);
    }
}
