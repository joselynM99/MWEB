package ec.edu.uce.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "perfil")
public class Perfil {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_perfil")
	@SequenceGenerator(name = "seq_perfil", sequenceName = "seq_perfil", allocationSize = 1)
	@Column(name = "perf_id")
	private Integer id;
	
	@Column(name = "perf_nombre")
	private String nombre;
	
	@Column(name = "perf_descripcion")
	private String descripcion;
	
	@Column(name = "perf_estado")
	private boolean estado;
	
	@ManyToMany(mappedBy = "roles")
	private List<Usuario> usuarios;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Perfil [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", estado=" + estado
				+ ", usuarios=" + usuarios + "]";
	}

	
	
	

}
