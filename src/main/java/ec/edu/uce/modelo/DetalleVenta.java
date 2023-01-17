package ec.edu.uce.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_detalle_venta")
	@SequenceGenerator(name = "seq_detalle_venta", sequenceName = "seq_detalle_venta", allocationSize = 1)
	@Column(name = "deve_id")
	private Integer id;

	@Column(name = "deve_cantidad")
	private Double cantidad;

	@Column(name = "deve_total")
	private BigDecimal total;

	@ManyToOne
	@JoinColumn(name = "vent_id")
	private Venta venta;

	@ManyToOne
	@JoinColumn(name = "prod_id")
	private Producto producto;
	
	@ManyToOne
	@JoinColumn(name = "supr_id")
	private SubProducto subProducto;
	
	

	/**
	 * 
	 */
	public DetalleVenta() {
		super();
	}

	/**
	 * @param cantidad
	 * @param total
	 * @param producto
	 */
	public DetalleVenta(Double cantidad, BigDecimal total, Producto producto) {
		super();
		this.cantidad = cantidad;
		this.total = total;
		this.producto = producto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public SubProducto getSubProducto() {
		return subProducto;
	}

	public void setSubProducto(SubProducto subProducto) {
		this.subProducto = subProducto;
	}
	
	

	

}
