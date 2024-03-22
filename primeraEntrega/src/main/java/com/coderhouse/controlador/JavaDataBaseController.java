package com.coderhouse.controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JavaDataBaseController {
	
	private static final String DATA_BASE = "coderhouse";
	private static final String URL = "jdbc:mysql://localhost:3306/" + DATA_BASE;
	private static final String USER = "root";
	private static final String PASSWORD = "";

	private Connection connection;
	
	public Connection getConnection() {
		try {
			System.out.println("intentando conectar a " + DATA_BASE);
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Conectado a " + URL);
			}
		catch(SQLException e) {
			System.err.println("Error al conectar la base" + e.getMessage());
		}
		return connection;
	}
	
	public void closeConnection()
	{
		if (connection != null)
		{
			try {connection.close(); System.out.println("Se a cerrado la coneccion");}
			catch(SQLException e) {
				System.err.println("Error al cerrar" + e.getMessage());
			}
			
		}
	}
}
