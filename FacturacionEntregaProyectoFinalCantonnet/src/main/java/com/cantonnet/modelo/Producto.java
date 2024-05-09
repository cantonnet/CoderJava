package com.cantonnet.modelo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Schema(description = "Modelo de Productos")
@SuppressWarnings("serial")
@Entity
@Table(name = "productos")
public class Producto {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Id del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private int idProducto;
	@Schema(description = "Nombre del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "Mantecol")
    @Column(name = "nombre_producto")
    private String nombreProducto;
	@Schema(description = "Precio del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "100.0")
    @Column(name = "precio")
    private Double precio;
	@Schema(description = "Stock del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "50")
    @Column(name = "stock")
    private Integer stock;
	@Schema(description = "Cantidad que lleva el cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @Column(name = "cantidad")
    private Integer cantidad;
    
    @ManyToOne
    @JoinColumn(name = "id_venta")
    @JsonIgnore
    private Venta venta;

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}
    
    // Getters and setters
    
    
}
