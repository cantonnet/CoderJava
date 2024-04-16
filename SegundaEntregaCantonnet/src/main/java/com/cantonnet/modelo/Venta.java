package com.cantonnet.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "Venta")
public class Venta {
	
	@Id
	@Column(name="id")
    private int id;
	@Column(name="clienteID")
    private int clienteId;
	@Column(name="fecha")
    private Date fecha;
	@Column(name="total")
    private double total;
	@OneToMany(mappedBy = "venta")
    private List<Producto> productos;

	public Venta() {}
    // Constructor
    public Venta(int clienteId, Date fecha) {
        this.clienteId = clienteId;
        this.fecha = fecha;
        this.productos = new ArrayList<>();
        this.total = 0.0;
    }
    public void agregarProducto(Producto producto) {
        productos.add(producto);
        calcularTotal();
    }

    private void calcularTotal() {
        total = 0.0;
        for (Producto producto : productos) {
            total += producto.getPrecio();
        }
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }
    
    public void setTotal(double total) {
        this.total = total;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
        calcularTotal();
    }
}
