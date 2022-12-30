package ec.edu.uce.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "proveedor")
public class Proveedor {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_proveedor")
	@SequenceGenerator(name = "seq_proveedor", sequenceName = "seq_proveedor", allocationSize = 1)
	@Column(name = "prov_id")
	private Integer id;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "prov_tipo_id")
	private TipoIdentificacion tipoId;
	
	@Column(name = "prov_identificacion", unique = true)
	private String identificacion;

	@Column(name = "prov_razon_social")
	private String razonSocial;
	
	@Column(name = "prov_nombre_comercial")
	private String nombreComercial;

	@Column(name = "prov_telefono")
	private String telefono;

	@Column(name = "prov_correo")
	private String correo;

	@Column(name = "prov_direccion")
	private String direccion;

	@OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
	private List<Producto> productos;

	@OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
	private List<SubProducto> subproductos;

	@OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
	private List<Compra> compras;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoIdentificacion getTipoId() {
		return tipoId;
	}

	public void setTipoId(TipoIdentificacion tipoId) {
		this.tipoId = tipoId;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public List<SubProducto> getSubproductos() {
		return subproductos;
	}

	public void setSubproductos(List<SubProducto> subproductos) {
		this.subproductos = subproductos;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}
	
	

	

}
