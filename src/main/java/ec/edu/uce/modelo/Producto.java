package ec.edu.uce.modelo;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_producto")
	@SequenceGenerator(name = "seq_producto", sequenceName = "seq_producto", allocationSize = 1)
	@Column(name = "prod_id")
	private Integer id;

	@Column(name = "prod_codigo_barras", unique = true)
	private String codigoBarras;

	@Column(name = "prod_nombre")
	private String nombre;

	@Column(name = "prod_descripcion")
	private String descripcion;

	@Column(name = "prod_costo_promedio")
	private BigDecimal costoPromedio;

	@Column(name = "prod_precio_venta")
	private BigDecimal precioVenta;

	@Column(name = "prod_stock_actual")
	private Integer stockActual;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prov_id")
	private Proveedor proveedor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "impu_id")
	private Impuesto impuesto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "secc_id")
	private Seccion seccion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn( name = "marc_id")
	private Marca marca;

	@OneToMany(mappedBy = "producto", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<DetalleVenta> ventas;

	@OneToMany(mappedBy = "producto", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<DetalleCompra> compras;

	@OneToMany(mappedBy = "producto", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<SubProducto> subproductos;
	
	

	/**
	 * 
	 */
	public Producto() {
		super();
	}

	/**
	 * @param id
	 * @param codigoBarras
	 * @param nombre
	 * @param descripcion
	 * @param costoPromedio
	 * @param precioVenta
	 * @param stockActual
	 * @param proveedor
	 * @param impuesto
	 * @param seccion
	 * @param marca
	 */
	public Producto(Integer id, String codigoBarras, String nombre, String descripcion, BigDecimal costoPromedio,
			BigDecimal precioVenta, Integer stockActual, Proveedor proveedor, Impuesto impuesto, Seccion seccion,
			Marca marca) {
		super();
		this.id = id;
		this.codigoBarras = codigoBarras;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.costoPromedio = costoPromedio;
		this.precioVenta = precioVenta;
		this.stockActual = stockActual;
		this.proveedor = proveedor;
		this.impuesto = impuesto;
		this.seccion = seccion;
		this.marca = marca;
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

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Impuesto getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(Impuesto impuesto) {
		this.impuesto = impuesto;
	}

	public Seccion getSeccion() {
		return seccion;
	}

	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public List<DetalleVenta> getVentas() {
		return ventas;
	}

	public void setVentas(List<DetalleVenta> ventas) {
		this.ventas = ventas;
	}

	public List<DetalleCompra> getCompras() {
		return compras;
	}

	public void setCompras(List<DetalleCompra> compras) {
		this.compras = compras;
	}

	public List<SubProducto> getSubproductos() {
		return subproductos;
	}

	public void setSubproductos(List<SubProducto> subproductos) {
		this.subproductos = subproductos;
	}
	
	

}
