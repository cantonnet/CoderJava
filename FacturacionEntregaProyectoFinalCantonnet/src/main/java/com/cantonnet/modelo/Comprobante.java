package com.cantonnet.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Schema(description = "Modelo de Comprobante")
@SuppressWarnings("serial")
@Entity
@Table(name = "comprobantes")
public class Comprobante implements Serializable{

	@Schema(description = "Id del comprobante", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@Id
	@Column(name = "id_comprobante")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Schema(description = "Total monto de la venta", requiredMode = Schema.RequiredMode.REQUIRED, example = "30.0")
	@Column(name = "total")
	private double total;
	@Schema(description = "Fecha actual de la venta", requiredMode = Schema.RequiredMode.REQUIRED, example = "2024-3-5T10:45:33")
	@Column(name = "fecha_venta")
	private LocalDateTime fechaVenta;

	@Schema(description = "Comprobante del cliente")
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@Schema(description = "Comprobante de la venta")
	@OneToOne(mappedBy = "comprobante")
	private Venta venta;
	
	public Comprobante() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	public LocalDateTime getFechaVenta() {
		return fechaVenta;
	}
	
	public void setFechaVenta(LocalDateTime fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

}

