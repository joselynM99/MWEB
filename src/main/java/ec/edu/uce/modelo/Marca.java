package ec.edu.uce.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "marca")
public class Marca {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_marca")
	@SequenceGenerator(name = "seq_marca", sequenceName = "seq_marca", allocationSize = 1)
	@Column(name = "marc_id")
	private Integer id;
	
	@Column(name = "marc_nombre", unique = true)
	private String nombre;
	
	@OneToMany(mappedBy = "marca", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Producto> productos;
	
	@OneToMany(mappedBy = "marca", cascade ={CascadeType.PERSIST, CascadeType.REMOVE})
	private List<SubProducto> subproductos;

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

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	

}
