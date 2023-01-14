package ec.edu.uce.controller.dto;

import java.math.BigDecimal;

import ec.edu.uce.modelo.Impuesto;

public class ProductoDTO {

	private Integer id;

	private String codigoBarras;

	private String nombre;

	private String descripcion;

	private BigDecimal costoPromedio;

	private BigDecimal precioVenta;

	private Integer stockActual;

	private String proveedor;

	private String impuesto;

	private String seccion;

	private String marca;

	private Integer cantidad;

	public ProductoDTO(Integer id, String codigoBarras, String nombre, String descripcion, BigDecimal costoPromedio,
			BigDecimal precioVenta, Integer stockActual) {
		this.id = id;
		this.codigoBarras = codigoBarras;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.costoPromedio = costoPromedio;
		this.precioVenta = precioVenta;
		this.stockActual = stockActual;
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

	public Integer getStockActual() {
		return stockActual;
	}

	public void setStockActual(Integer stockActual) {
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

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}
