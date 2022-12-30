package ec.edu.uce.modelo;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
	@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1)
	@Column(name = "usua_id")
	private Integer id;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "usua_tipo_id")
	private TipoIdentificacion tipoId;

	@Column(name = "usua_identificacion", unique = true)
	private String identificacion;

	@Column(name = "usua_apellidos")
	private String apellidos;

	@Column(name = "usua_nombres")
	private String nombres;

	private String email;

	private String contraseña;

	@Column(name = "usua_estado")
	private boolean estado;


	@ManyToOne
	@JoinColumn(name = "caja_id")
	private Caja caja;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<CierreCaja> cierres;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<Perfil> roles;
	
	

	/**
	 * 
	 */
	public Usuario() {
		super();
	}

	/**
	 * @param tipoId
	 * @param identificacion
	 * @param apellidos
	 * @param nombres
	 * @param nombreUsuario
	 * @param estado
	 */
	public Usuario(TipoIdentificacion tipoId, String identificacion, String apellidos, String nombres,
			String email, boolean estado, String contraseña, Collection<Perfil> roles) {
		super();
		this.tipoId = tipoId;
		this.identificacion = identificacion;
		this.apellidos = apellidos;
		this.nombres = nombres;
		this.email = email;
		this.estado = estado;
		this.contraseña = contraseña;
		this.roles = roles;
	}

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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getNombreUsuario() {
		return email;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.email = nombreUsuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	
	public List<CierreCaja> getCierres() {
		return cierres;
	}

	public void setCierres(List<CierreCaja> cierres) {
		this.cierres = cierres;
	}

	public Collection<Perfil> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Perfil> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Caja getCaja() {
		return caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
	}
	
	

}
