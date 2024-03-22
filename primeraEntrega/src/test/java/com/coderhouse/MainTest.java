package com.coderhouse;

import com.coderhouse.controlador.JavaDataBaseController;

public class MainTest {

	public static void main(String[] args) {
		JavaDataBaseController controller = new JavaDataBaseController();
		controller.getConnection();
		controller.closeConnection();
		
	}

}
