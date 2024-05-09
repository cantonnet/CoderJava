package com.cantonnet.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

@Schema(description = "Modelo Cliente")
@SuppressWarnings("serial")
@Entity
@Table(name = "clientes")

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idCliente")
public class Cliente implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Id del cliente", example = "1")
    private int idCliente;
    
	@Schema(description = "Nombre del cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "Pedro")
    @Column(name = "nombre")
    private String nombre;
    
	@Schema(description = "Apellido del cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "Ferraro")
    @Column(name = "apellido")
    private String apellido;
    
	@Schema(description = "DNI del cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "999685999")
    @Column(name = "dni")
    private String dni;
    
 // Relación con Venta
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonManagedReference // Indica que esta es la parte "administrada" de la relación
    private List<Venta> ventas;

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
    
    // Getters and setters
    
    
}
