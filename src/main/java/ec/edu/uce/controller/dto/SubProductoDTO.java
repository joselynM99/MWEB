package ec.edu.uce.controller.dto;

import java.math.BigDecimal;


public class SubProductoDTO {

	private Integer id;

	private String codigoBarras;

	private String nombre;

	private String descripcion;
	
	private Integer cantidadRelacionada;

	private BigDecimal costoPromedio;

	private BigDecimal precioVenta;

	private Double stockActual;

	private String proveedor;
	
	private String impuesto;

	private String seccion;

	private String marca;
	
	private String producto;

	
	
	
	/**
	 * @param id
	 * @param codigoBarras
	 * @param nombre
	 * @param descripcion
	 * @param cantidadRelacionada
	 * @param costoPromedio
	 * @param precioVenta
	 * @param stockActual
	 * @param proveedor
	 * @param impuesto
	 * @param seccion
	 * @param marca
	 * @param producto
	 */
	public SubProductoDTO(Integer id, String codigoBarras, String nombre, String descripcion,
			Integer cantidadRelacionada, BigDecimal costoPromedio, BigDecimal precioVenta, Double stockActual,
			String proveedor, String impuesto, String seccion, String marca, String producto) {
		super();
		this.id = id;
		this.codigoBarras = codigoBarras;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidadRelacionada = cantidadRelacionada;
		this.costoPromedio = costoPromedio;
		this.precioVenta = precioVenta;
		this.stockActual = stockActual;
		this.proveedor = proveedor;
		this.impuesto = impuesto;
		this.seccion = seccion;
		this.marca = marca;
		this.producto = producto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getCostoPromedio() {
		return costoPromedio;
	}

	public void setCostoPromedio(BigDecimal costoPromedio) {
		this.costoPromedio = costoPromedio;
	}

	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}

	public Double getStockActual() {
		return stockActual;
	}

	public void setStockActual(Double stockActual) {
		this.stockActual = stockActual;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public String getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(String impuesto) {
		this.impuesto = impuesto;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getCantidadRelacionada() {
		return cantidadRelacionada;
	}

	public void setCantidadRelacionada(Integer cantidadRelacionada) {
		this.cantidadRelacionada = cantidadRelacionada;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}
	 
	

}
