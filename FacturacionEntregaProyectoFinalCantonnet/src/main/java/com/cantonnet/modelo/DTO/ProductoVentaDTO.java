package com.cantonnet.modelo.DTO;

public class ProductoVentaDTO {
	
		private int cantidad;
	    private ProductoDTO producto;
	    
		public int getCantidad() {
			return cantidad;
		}
		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}
		public ProductoDTO getProducto() {
			return producto;
		}
		public void setProducto(ProductoDTO producto) {
			this.producto = producto;
		}


}
