package com.coderhouse.controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

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

	// CRUD CLIENTE
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
	
	public void elimnarCliente(Integer id)
	{
		PreparedStatement statement = null;
		try {
			String query = "DELETE FROM Cliente WHERE id = ?";
					statement = connection.prepareStatement(query);
					statement.setInt(1, id);
					
					int filasAfectadas = statement.executeUpdate();
					if (filasAfectadas > 0) {
						System.out.println("Cliente " + id + " eliminado correctamente");
					} else {
						System.out.println("No se encontró el cliente con ID: " + id);
						System.out.println("No se pudo eliminar ");
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
	
	// CRUD PRODUCTO
	
	public void insertarProducto(Producto producto) {
	    PreparedStatement statement = null;
	    String query = "INSERT INTO Producto(nombre, precio, stock) VALUES (?, ?, ?)";
	    try {
	        statement = connection.prepareStatement(query);
	        statement.setString(1, producto.getNombre());
	        statement.setDouble(2, producto.getPrecio());
	        statement.setInt(3, producto.getStock());
	        
	        int rowsInserted = statement.executeUpdate();
	        if (rowsInserted > 0) {
	            System.out.println("El producto fue insertado exitosamente");
	        }
	    } catch (SQLException e) {
	        System.err.println("No se pudo insertar producto: " + e.getMessage());
	    } finally {
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException e) {
	                System.err.println("Error al cerrar el statement: " + e.getMessage());
	            }
	        }
	    }
	}
	
	public void modificarProducto(int id, String nuevoNombre, double nuevoPrecio, int nuevoStock) {
	    PreparedStatement statement = null;
	    try {
	        String query = "UPDATE Producto SET nombre = ?, precio = ?, stock = ? WHERE id = ?";
	        statement = connection.prepareStatement(query);
	        statement.setString(1, nuevoNombre);
	        statement.setDouble(2, nuevoPrecio);
	        statement.setInt(3, nuevoStock);
	        statement.setInt(4, id);
	        
	        int filasAfectadas = statement.executeUpdate();
	        if (filasAfectadas > 0) {
	            System.out.println("Producto actualizado correctamente");
	        } else {
	            System.out.println("No se encontró el producto con ID: " + id);
	        }
	    } catch (SQLException e) {
	        System.err.println("No se pudo actualizar producto: " + e.getMessage());
	    } finally {
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException e) {
	                System.err.println("Error al cerrar el statement: " + e.getMessage());
	            }
	        }
	    }
	}
	
	public void eliminarProducto(int id) {
	    PreparedStatement statement = null;
	    try {
	        String query = "DELETE FROM Producto WHERE id = ?";
	        statement = connection.prepareStatement(query);
	        statement.setInt(1, id);

	        int filasAfectadas = statement.executeUpdate();
	        if (filasAfectadas > 0) {
	            System.out.println("Producto eliminado correctamente.");
	        } else {
	            System.out.println("No se encontró el producto con ID: " + id);
	        }
	    } catch (SQLException e) {
	        System.err.println("No se pudo eliminar el producto: " + e.getMessage());
	    } finally {
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException e) {
	                System.err.println("Error al cerrar el statement: " + e.getMessage());
	            }
	        }
	    }
	}
	
	public void mostrarProductos() {
	    Statement statement = null;
	    ResultSet resultSet = null;

	    String query = "SELECT id, nombre, precio, stock FROM Producto";
	    try {
	        statement = connection.createStatement();
	        resultSet = statement.executeQuery(query);

	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String nombre = resultSet.getString("nombre");
	            double precio = resultSet.getDouble("precio");
	            int stock = resultSet.getInt("stock");

	            System.out.println("Producto ID: " + id + ", Nombre: " + nombre + ", Precio: $" + precio + ", Stock: " + stock);
	        }
	    } catch (SQLException e) {
	        System.err.println(e.getMessage());
	    } finally {
	        if (resultSet != null) {
	            try {
	                resultSet.close();
	            } catch (SQLException e) {
	                System.err.println("Error al cerrar el resultSet: " + e.getMessage());
	            }
	        }
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException e) {
	                System.err.println("Error al cerrar el statement: " + e.getMessage());
	            }
	        }
	    }
	}
	
	// CRUD VENTA
	
	public void insertarVenta(Venta venta) {
	    PreparedStatement statement = null;
	    String query = "INSERT INTO Venta(clienteId, fecha, total) VALUES (?, ?, ?)";
	    try {
	        statement = connection.prepareStatement(query);
	        statement.setInt(1, venta.getClienteId());
	        statement.setDate(2, new java.sql.Date(venta.getFecha().getTime()));
	        statement.setDouble(3, venta.getTotal()); // Asumiendo que calculas el total antes de llamar a este método
	        
	        int rowsInserted = statement.executeUpdate();
	        if (rowsInserted > 0) {
	            System.out.println("La venta fue insertada exitosamente");
	        }
	    } catch (SQLException e) {
	        System.err.println("No se pudo insertar venta: " + e.getMessage());
	    } finally {
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException e) {
	                System.err.println("Error al cerrar el statement: " + e.getMessage());
	            }
	        }
	    }
	}
	
	public void modificarVenta(int id, int nuevoClienteId, Date nuevaFecha, double nuevoTotal) {
	    PreparedStatement statement = null;
	    try {
	        String query = "UPDATE Venta SET clienteId = ?, fecha = ?, total = ? WHERE id = ?";
	        statement = connection.prepareStatement(query);
	        statement.setInt(1, nuevoClienteId);
	        statement.setDate(2, new java.sql.Date(nuevaFecha.getTime()));
	        statement.setDouble(3, nuevoTotal);
	        statement.setInt(4, id);
	        
	        int filasAfectadas = statement.executeUpdate();
	        if (filasAfectadas > 0) {
	            System.out.println("Venta actualizada correctamente");
	        } else {
	            System.out.println("No se encontró la venta con ID: " + id);
	        }
	    } catch (SQLException e) {
	        System.err.println("No se pudo actualizar la venta: " + e.getMessage());
	    } finally {
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException e) {
	                System.err.println("Error al cerrar el statement: " + e.getMessage());
	            }
	        }
	    }
	}
	
	public void eliminarVenta(int id) {
	    PreparedStatement statement = null;
	    try {
	        String query = "DELETE FROM Venta WHERE id = ?";
	        statement = connection.prepareStatement(query);
	        statement.setInt(1, id);

	        int filasAfectadas = statement.executeUpdate();
	        if (filasAfectadas > 0) {
	            System.out.println("Venta eliminada correctamente.");
	        } else {
	            System.out.println("No se encontró la venta con ID: " + id);
	        }
	    } catch (SQLException e) {
	        System.err.println("No se pudo eliminar la venta: " + e.getMessage());
	    } finally {
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException e) {
	                System.err.println("Error al cerrar el statement: " + e.getMessage());
	            }
	        }
	    }
	}
	
	public void mostrarVentas() {
	    Statement statement = null;
	    ResultSet resultSet = null;

	    String query = "SELECT id, clienteId, fecha, total FROM Venta";
	    try {
	        statement = connection.createStatement();
	        resultSet = statement.executeQuery(query);

	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            int clienteId = resultSet.getInt("clienteId");
	            Date fecha = resultSet.getDate("fecha");
	            double total = resultSet.getDouble("total");

	            System.out.println("Venta ID: " + id + ", Cliente ID: " + clienteId + ", Fecha: " + fecha + ", Total: $" + total);
	        }
	    } catch (SQLException e) {
	        System.err.println(e.getMessage());
	    } finally {
	        if (resultSet != null) {
	            try {
	                resultSet.close();
	            } catch (SQLException e) {
	                System.err.println("Error al cerrar el resultSet: " + e.getMessage());
	            }
	        }
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException e) {
	                System.err.println("Error al cerrar el statement: " + e.getMessage());
	            }
	        }
	    }
	}
}
