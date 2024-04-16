package com.cantonnet.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "Producto")
public class Producto {
	
	@Id
	@Column(name="id")
    private int id;
	@Column(name="nombre")
    private String nombre;
	@Column(name="precio")
    private double precio;
	@Column(name="stock")
    private int stock;
	
	@ManyToOne
    @JoinColumn(name="venta_id") // clave foránea en la tabla de base
    private Venta venta;

    // Constructor
	public Producto() {}
	
    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    
}
