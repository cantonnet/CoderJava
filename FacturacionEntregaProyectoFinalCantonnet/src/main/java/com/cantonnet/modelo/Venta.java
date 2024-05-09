package com.cantonnet.modelo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idVenta")
public class Venta {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVenta;
    
    @Column(name = "total_productos")
    private Integer totalProductos;
    
    @Column(name = "total_venta")
    private Double totalVenta;
    
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    @JsonBackReference
    @Schema(description = "Cliente que tiene la venta", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "id_comprobante")
    private Comprobante comprobante;
    
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<Producto> productos;
    
    public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public Integer getTotalProductos() {
		return totalProductos;
	}

	public void setTotalProductos(Integer totalProductos) {
		this.totalProductos = totalProductos;
	}

	public Double getTotalVenta() {
		return totalVenta;
	}

	public void setTotalVenta(Double totalVenta) {
		this.totalVenta = totalVenta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Comprobante getComprobante() {
		return comprobante;
	}

	public void setComprobante(Comprobante comprobante) {
		this.comprobante = comprobante;
	}
    
    // Getters and setters
    
    

}
