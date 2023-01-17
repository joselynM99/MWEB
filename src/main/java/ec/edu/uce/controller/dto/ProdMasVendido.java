package ec.edu.uce.controller.dto;

public class ProdMasVendido {
	
	String codigoBarras;
	String nombre;
	Double cantidad;
	
	
	/**
	 * 
	 */
	public ProdMasVendido() {
		super();
	}
	/**
	 * @param codigoBarras
	 * @param nombre
	 * @param cantidad
	 */
	public ProdMasVendido(String codigoBarras, String nombre, Double cantidad) {
		super();
		this.codigoBarras = codigoBarras;
		this.nombre = nombre;
		this.cantidad = cantidad;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getCantidad() {
		return cantidad;
	}
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	

}
