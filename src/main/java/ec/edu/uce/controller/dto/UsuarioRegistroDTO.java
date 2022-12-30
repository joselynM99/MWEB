package ec.edu.uce.controller.dto;

import ec.edu.uce.modelo.Caja;
import ec.edu.uce.modelo.TipoIdentificacion;

public class UsuarioRegistroDTO {

	private Long id;
	private String nombres;
	private String apellidos;
	private String email;
	private String password;
	private String perfil;
	private TipoIdentificacion tipoId;
	private String identificacion;
	private boolean estado;
	private String caja;
	
	
	/**
	 * 
	 */
	public UsuarioRegistroDTO() {
		super();
	}
	
	/**
	 * @param id
	 * @param nombres
	 * @param apellidos
	 * @param email
	 * @param password
	 * @param perfil
	 * @param tipoId
	 * @param identificacion
	 * @param estado
	 * @param caja
	 */
	public UsuarioRegistroDTO(Long id, String nombres, String apellidos, String email, String password, String perfil,
			TipoIdentificacion tipoId, String identificacion, boolean estado, String caja) {
		super();
		this.id = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.email = email;
		this.password = password;
		this.perfil = perfil;
		this.tipoId = tipoId;
		this.identificacion = identificacion;
		this.estado = estado;
		this.caja = caja;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
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
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getCaja() {
		return caja;
	}

	public void setCaja(String caja) {
		this.caja = caja;
	}
	
	
	
	
	

}
