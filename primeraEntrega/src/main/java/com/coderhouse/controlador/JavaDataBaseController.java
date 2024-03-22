package com.coderhouse.controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.coderhouse.entidades.Cliente;
import com.coderhouse.entidades.Producto;
import com.coderhouse.entidades.Venta;

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
	
	// CRUD
		public void mostrarClientes() {
			Statement statement = null;
			ResultSet resultSet = null;

			String query = "SELECT id, nombre, email, telefono FROM Cliente";
			try {

				statement = connection.createStatement();
				resultSet = statement.executeQuery(query);

				while (resultSet.next()) {
					Integer id = resultSet.getInt("id");
					String nombre = resultSet.getString("nombre");
					String email = resultSet.getString("email");
					Integer telefono = resultSet.getInt("telefono");

					System.out.println("Cliente " + id + " es " + nombre + " Correo: " + email
							+ "Telefono: " + telefono);
				}

			} catch (SQLException e) {
				System.err.println(e.getMessage());
			} finally {
				try {
					if (resultSet != null) {
						resultSet.close();
					}
					if (statement != null) {
						statement.close();
					}
				} catch (SQLException e) {
					System.err.println("Error al cerrar el statement o el resultSet: " + e.getMessage());
				}
			}

		}
}
