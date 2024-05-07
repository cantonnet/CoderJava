USE coderhouse;

-- Creación de la tabla Clientes
CREATE TABLE clientes (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    dni VARCHAR(255) NOT NULL
);

-- Creación de la tabla Productos
CREATE TABLE productos (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre_producto VARCHAR(255) NOT NULL,
    precio DOUBLE NOT NULL,
    stock INT NOT NULL,
    cantidad INT NOT NULL,
    id_cliente INT,
    id_venta INT,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente),
    FOREIGN KEY (id_venta) REFERENCES ventas(id_venta)
);

-- Creación de la tabla Ventas
CREATE TABLE ventas (
    id_venta INT AUTO_INCREMENT PRIMARY KEY,
    total_productos INT NOT NULL,
    total_venta DOUBLE NOT NULL,
    id_cliente INT,
    id_comprobante INT,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente),
    FOREIGN KEY (id_comprobante) REFERENCES comprobantes(id_comprobante)
);

-- Creación de la tabla Comprobantes
CREATE TABLE comprobantes (
    id_comprobante INT AUTO_INCREMENT PRIMARY KEY,
    total DOUBLE NOT NULL,
    fecha_venta DATETIME NOT NULL,
    id_cliente INT,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
);


-- Insertar datos en la tabla Clientes
INSERT INTO clientes (nombre, apellido, dni) VALUES
('Hernan', 'Bartolome', '123456987'),
('Juan', 'Stiggetti', '321654987');

-- Insertar datos en la tabla Comprobantes
INSERT INTO comprobantes (total, fecha_venta, id_cliente) VALUES
(60.0, '2024-03-05 10:45:33', 1),
(45.5, '2024-03-06 12:30:20', 2);

-- Insertar datos en la tabla Ventas
INSERT INTO ventas (total_productos, total_venta, id_cliente, id_comprobante) VALUES
(3, 60.0, 1, 1),
(2, 45.5, 2, 2);

-- Insertar datos en la tabla Productos
INSERT INTO productos (nombre_producto, precio, stock, cantidad, id_cliente, id_venta) VALUES
('Mantecol', 100.0, 10, 2, 1, 1),
('QUeso', 140.0, 15, 1, 1, 1),
('Cepillo de dientes', 115.5, 20, 1, 2, 2),
('Pasta dental', 130.0, 25, 1, 2, 2);
