package cantonnet.inventario.repositorio;

import cantonnet.inventario.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepositorio extends JpaRepository<Producto, Integer> {

}
