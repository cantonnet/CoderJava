package cantonnet.inventario.controlador;

import cantonnet.inventario.modelo.Producto;
import cantonnet.inventario.servicio.ProductoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("inventario-app")
@CrossOrigin(origins = {
        "https://scintillating-moxie-fbeda6.netlify.app",
        "http://localhost:4200",
        "https://*.netlify.app"
})
public class ProductoControlador {
    private static final Logger logger = LoggerFactory.getLogger(ProductoControlador.class);

    @Autowired
    private ProductoServicio productoServicio;

    // Status check para Render
    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> status() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Inventario API");
        response.put("timestamp", new java.util.Date());
        response.put("endpoints", Map.of(
            "GET /inventario-app/productos", "Obtener todos los productos",
            "GET /inventario-app/productos/{id}", "Obtener producto por ID",
            "POST /inventario-app/productos", "Crear nuevo producto",
            "PUT /inventario-app/productos/{id}", "Actualizar producto existente",
            "DELETE /inventario-app/productos/{id}", "Eliminar producto"
        ));
        response.put("tutorial", Map.of(
            "base_url", "https://inventario-backend-vtiq.onrender.com",
            "ejemplos", Map.of(
                "Obtener productos", "GET /inventario-app/productos",
                "Crear producto", "POST /inventario-app/productos con JSON: {\"descripcion\":\"Producto Test\",\"precio\":100.0,\"existencia\":10}",
                "Actualizar producto", "PUT /inventario-app/productos/1 con JSON actualizado",
                "Eliminar producto", "DELETE /inventario-app/productos/1"
            )
        ));
        return ResponseEntity.ok(response);
    }

    // Listar todos los productos
    @GetMapping("/productos")
    public List<Producto> obtenerProductos(){
        List<Producto> productos = this.productoServicio.listarProductos();
        logger.info("Productos obtenidos: {}", productos.size());
        return productos;
    }

    // Obtener un producto por ID
    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Integer id){
        logger.info("Buscando producto con ID: {}", id);
        Producto producto = this.productoServicio.buscarProductoPorId(id);
        if(producto != null){
            logger.info("Producto encontrado: {}", producto);
            return ResponseEntity.ok(producto);
        } else {
            logger.warn("Producto con ID {} no encontrado", id);
            return ResponseEntity.notFound().build();
        }
    }

    // Agregar un nuevo producto
    @PostMapping("/productos")
    public Producto agregarProducto(@RequestBody Producto producto){
        logger.info("Agregando nuevo producto: {}", producto);
        Producto productoGuardado = this.productoServicio.guardarProducto(producto);
        logger.info("Producto guardado exitosamente: {}", productoGuardado);
        return productoGuardado;
    }

    // Modificar un producto existente
    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Integer id,
                                                       @RequestBody Producto productoDetalles){
        logger.info("Actualizando producto con ID: {}", id);
        Producto producto = this.productoServicio.buscarProductoPorId(id);

        if(producto != null){
            // Actualizar los campos del producto
            producto.setDescripcion(productoDetalles.getDescripcion());
            producto.setPrecio(productoDetalles.getPrecio());
            producto.setExistencia(productoDetalles.getExistencia());

            Producto productoActualizado = this.productoServicio.guardarProducto(producto);
            logger.info("Producto actualizado exitosamente: {}", productoActualizado);
            return ResponseEntity.ok(productoActualizado);
        } else {
            logger.warn("Producto con ID {} no encontrado para actualizar", id);
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un producto
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarProducto(@PathVariable Integer id){
        logger.info("Eliminando producto con ID: {}", id);

        try {
            // Verificar si el producto existe antes de eliminarlo
            Producto producto = this.productoServicio.buscarProductoPorId(id);
            if(producto != null){
                this.productoServicio.eliminarProducto(id);
                logger.info("Producto con ID {} eliminado exitosamente", id);

                Map<String, Boolean> respuesta = new HashMap<>();
                respuesta.put("eliminado", Boolean.TRUE);
                return ResponseEntity.ok(respuesta);
            } else {
                logger.warn("Producto con ID {} no encontrado para eliminar", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error al eliminar producto con ID {}: {}", id, e.getMessage());
            Map<String, Boolean> respuesta = new HashMap<>();
            respuesta.put("eliminado", Boolean.FALSE);
            return ResponseEntity.badRequest().body(respuesta);
        }
    }
}
