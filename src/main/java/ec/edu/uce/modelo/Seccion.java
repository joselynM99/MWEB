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
@Table(name = "seccion")
public class Seccion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_seccion")
	@SequenceGenerator(name = "seq_seccion", sequenceName = "seq_seccion", allocationSize = 1)
	@Column(name = "secc_id")
	private Integer id;
	
	@Enumerated(value = EnumType.STRING)
	@Column(name = "secc_categoria")
	private Categoria categoria;
	
	@Column(name = "secc_descripcion")
	private String descripcion;
	
	@OneToMany(mappedBy = "seccion", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Producto> productos;
	
	@OneToMany(mappedBy = "seccion", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<SubProducto> subproductos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	
}
