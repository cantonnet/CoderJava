CREATE DATABASE IF NOT EXISTS coderhouse;
USE coderhouse;

CREATE TABLE IF NOT EXISTS Cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefono VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    precio DOUBLE NOT NULL,
    stock INT NOT NULL
);

CREATE TABLE IF NOT EXISTS Venta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    clienteId INT,
    fecha DATE NOT NULL,
    total DOUBLE NOT NULL,
    FOREIGN KEY (clienteId) REFERENCES Cliente(id)
);

INSERT INTO Cliente (nombre, email, telefono) VALUES ('Juan Perez', 'juan.perez@example.com', '1234567890');