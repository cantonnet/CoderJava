package com.coderhouse;

import com.coderhouse.controlador.JavaDataBaseController;
import com.coderhouse.entidades.Cliente;

public class MainTest {

	public static void main(String[] args) {
		JavaDataBaseController controller = new JavaDataBaseController();
		controller.getConnection();
		//Cliente cliente = new Cliente("obdulio", "fran@hotmail.com", "123456");
		//controller.insertarCliente("Manuel", "Manu2024@gmail.com", "53330");
		//controller.insertarCliente(cliente);
		controller.modificarCliente("sasimo", "ger@live.com", "0987", 8);
		controller.mostrarClientes();
		controller.closeConnection();
		
	}

}
