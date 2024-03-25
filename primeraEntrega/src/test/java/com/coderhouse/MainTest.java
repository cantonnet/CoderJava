package com.coderhouse;

import com.coderhouse.controlador.JavaDataBaseController;
import com.coderhouse.entidades.Cliente;
import com.coderhouse.entidades.Producto;
import com.coderhouse.entidades.Venta;
import java.util.Date;

public class MainTest {

	public static void main(String[] args) {
		JavaDataBaseController controller = new JavaDataBaseController();
		controller.getConnection();
		//Cliente cliente = new Cliente("obdulio", "fran@hotmail.com", "123456");
		//controller.insertarCliente("Manuel", "Manu2024@gmail.com", "53330");
		//controller.insertarCliente(cliente);
		//controller.modificarCliente("sasimo", "ger@live.com", "0987", 8);
		//controller.elimnarCliente(4);
		controller.mostrarClientes();
		//Producto producto = new Producto ("mantecol", 80.00, 30);
		//controller.insertarProducto(producto);
		Date fecha = new Date();
		Venta venta = new Venta(2, fecha);
		controller.mostrarProductos();
		controller.insertarVenta(venta);
		controller.mostrarVentas();
		controller.closeConnection();
		
	}

}
