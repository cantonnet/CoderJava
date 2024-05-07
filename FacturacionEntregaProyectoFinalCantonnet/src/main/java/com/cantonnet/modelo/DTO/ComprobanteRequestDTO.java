package com.cantonnet.modelo.DTO;

import java.util.List;

public class ComprobanteRequestDTO {
    private ClienteDTO cliente;
    private List<ProductoVentaDTO> productos;
    
	public ClienteDTO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
	public List<ProductoVentaDTO> getProductos() {
		return productos;
	}
	public void setProductos(List<ProductoVentaDTO> productos) {
		this.productos = productos;
	}

    // Getters and setters
}
