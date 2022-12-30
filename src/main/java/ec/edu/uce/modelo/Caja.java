package ec.edu.uce.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "caja")
public class Caja {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_caja")
	@SequenceGenerator(name = "seq_caja", sequenceName = "seq_caja", allocationSize = 1)
	@Column(name = "caja_id")
	private Integer id;

	@Column(name = "caja_codigo", unique = true)
	private Integer codigo;

	@Column(name = "caja_nombre", unique = true)
	private String nombre;

	@OneToMany(mappedBy = "caja", cascade = CascadeType.ALL)
	private List<CierreCaja> cierresCaja;

	@OneToMany(mappedBy = "caja", cascade = CascadeType.ALL)
	private List<Usuario> usuarios;
	
	@OneToMany(mappedBy = "caja", cascade = CascadeType.ALL)
	private List<Venta> ventas;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<CierreCaja> getCierresCaja() {
		return cierresCaja;
	}

	public void setCierresCaja(List<CierreCaja> cierresCaja) {
		this.cierresCaja = cierresCaja;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}
	
	

}
