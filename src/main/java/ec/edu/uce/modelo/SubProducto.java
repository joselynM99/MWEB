package ec.edu.uce.modelo;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "sub_producto")
public class SubProducto {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sub_producto")
	@SequenceGenerator(name = "seq_sub_producto", sequenceName = "seq_sub_producto", allocationSize = 1)
	@Column(name = "supr_id")
	private Integer id;

	@Column(name = "supr_codigo_barras", unique = true)
	private String codigoBarras;

	@Column(name = "supr_nombre")
	private String nombre;

	@Column(name = "supr_descripcion")
	private String descripcion;

	@Column(name = "supr_cantidad_relacionada")
	private Integer cantidadRelacionada;

	@Column(name = "supr_costo_promedio")
	private BigDecimal costoPromedio;

	@Column(name = "supr_precio_venta")
	private BigDecimal precioVenta;

	@Column(name = "supr_stock_actual")
	private Integer stockActual;

	@ManyToOne
	@JoinColumn(name = "impu_id")
	private Impuesto impuesto;

	@ManyToOne
	@JoinColumn(name = "prod_id")
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "prov_id")
	private Proveedor proveedor;

	@ManyToOne
	@JoinColumn(name = "marc_id")
	private Marca marca;
	
	@ManyToOne
	@JoinColumn(name = "secc_id")
	private Seccion seccion;
	
	@OneToMany(mappedBy = "subProducto", cascade = CascadeType.ALL)
	private List<DetalleVenta> ventas;

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

	public Seccion getSeccion() {
		
		return seccion;
	}

	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}

	public Integer getCantidadRelacionada() {
		return cantidadRelacionada;
	}

	public void setCantidadRelacionada(Integer cantidadRelacionada) {
		this.cantidadRelacionada = cantidadRelacionada;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
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

	public Impuesto getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(Impuesto impuesto) {
		this.impuesto = impuesto;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public List<DetalleVenta> getVentas() {
		return ventas;
	}

	public void setVentas(List<DetalleVenta> ventas) {
		this.ventas = ventas;
	}
	
	

}
