package com.cantonnet.modelo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Schema(description = "Modelo de Venta")
@SuppressWarnings("serial")
@Entity
@Table(name = "ventas")
public class Venta implements Serializable{

	@Schema(description = "Id de al venta", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@Id
	@Column(name = "id_venta")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "total_productos")
	private int totalProductos;
	@Column(name = "total_venta")
	private double totalVenta;
	

	@Schema(description = "Cliente que tiene la venta", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@JoinColumn(name = "id_cliente")
	@ManyToOne
	private Cliente cliente;

	@JsonIgnore
	@OneToMany(mappedBy = "venta")
	@Column(name = "producto")
	private List<Producto> productos;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "id_comprobante")
	private Comprobante comprobante;
	
	public Venta(int id, int totalProductos, double totalVenta, Cliente cliente, List<Producto> productos,
			Comprobante comprobante) {
		super();
		this.id = id;
		this.totalProductos = totalProductos;
		this.totalVenta = totalVenta;
		this.cliente = cliente;
		this.productos = productos;
		this.comprobante = comprobante;
	}

	public Venta() {
		super();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getTotalProductos() {
		return totalProductos;
	}
	
	public void setTotalProductos(int totalProductos) {
		this.totalProductos = totalProductos;
	}
	
	public double getTotalVenta() {
		return totalVenta;
	}
	
	public void setTotalVenta(double totalVenta) {
		this.totalVenta = totalVenta;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Comprobante getComprobante() {
		return comprobante;
	}

	public void setComprobante(Comprobante comprobante) {
		this.comprobante = comprobante;
	}
	
}
