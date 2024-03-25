package com.coderhouse.controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
		} catch (SQLException e) {
			System.err.println("Error al conectar la base" + e.getMessage());
		}
		return connection;
	}

	public void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
				System.out.println("Se a cerrado la coneccion");
			} catch (SQLException e) {
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

				System.out.println("Cliente " + id + " es " + nombre + " Correo: " + email + "Telefono: " + telefono);
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

	public void insertarCliente(Cliente cliente) {
		PreparedStatement Statement = null;

		String query = "INSERT INTO Cliente(nombre, email, telefono) VALUES (?, ?, ?)";
		try {
			Statement = connection.prepareStatement(query);
			Statement.setString(1, cliente.getNombre());
			Statement.setString(2, cliente.getEmail());
			Statement.setString(3, cliente.getTelefono());
			int rowsInserted = Statement.executeUpdate();

			if (rowsInserted > 0) {
				System.out.println("El cliente fue insertado exitosamente");
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (Statement != null) {
				try {
					Statement.close();
				} catch (SQLException e) {
					System.err.println("No se pudo insertar cliente " + e.getMessage());
				}
			}
		}
	}

	public void modificarCliente(String nuevoNombre, String nuevoEmail, String nuevoTelefono, int id) {
		PreparedStatement statement = null;
		try {
			String query = "UPDATE Cliente SET nombre = ?, email = ?, telefono = ? WHERE id = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, nuevoNombre);
			statement.setString(2, nuevoEmail);
			statement.setString(3, nuevoTelefono);
			statement.setInt(4, id);

			int filasAfectadas = statement.executeUpdate();
			if (filasAfectadas > 0) {
				System.out.println("Cliente " + id + " actualizado correctamente");
			} else {
				System.out.println("No se encontró el cliente con ID: " + id);
				System.out.println("No se pudo insertar a " + nuevoNombre);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				System.err.println("Error al cerrar el statement: " + e.getMessage());
			}
		}

	}
}
