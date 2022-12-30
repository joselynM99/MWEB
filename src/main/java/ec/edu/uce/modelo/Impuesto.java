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
@Table(name = "impuesto")
public class Impuesto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_impuesto")
	@SequenceGenerator(name = "seq_impuesto", sequenceName = "seq_impuesto", allocationSize = 1)
	@Column(name = "impu_id")
	private Integer id;
	
	@Column(name = "impu_tipo_importe")
	private boolean tipoImporte;
	
	@Column(name = "impu_valor")
	private Double valor;
	
	@Column(name = "impu_tipo_impuesto")
	@Enumerated(value = EnumType.STRING)
	private TipoImpuesto tipoImpuesto;
	
	@OneToMany(mappedBy = "impuesto", cascade = CascadeType.ALL)
	private List<Producto> productos;
	
	@OneToMany(mappedBy = "impuesto", cascade = CascadeType.ALL)
	private List<SubProducto> subproductos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isTipoImporte() {
		return tipoImporte;
	}

	public void setTipoImporte(boolean tipoImporte) {
		this.tipoImporte = tipoImporte;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public TipoImpuesto getTipoImpuesto() {
		return tipoImpuesto;
	}

	public void setTipoImpuesto(TipoImpuesto tipoImpuesto) {
		this.tipoImpuesto = tipoImpuesto;
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
	
	

}
