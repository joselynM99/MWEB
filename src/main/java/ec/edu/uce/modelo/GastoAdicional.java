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
@Table(name = "gasto_adicional")
public class GastoAdicional {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gasto_adicional")
	@SequenceGenerator(name = "seq_gasto_adicional", sequenceName = "seq_gasto_adicional", allocationSize = 1)
	@Column(name = "gaad_id")
	private Integer id;
	
	@Column(name = "gaad_motivo")
	private String motivo;
	
	@Column(name = "gaad_monto")
	private BigDecimal monto;
	
	@ManyToOne
	@JoinColumn(name = "cica_id")
	private CierreCaja cierreCaja;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public CierreCaja getCierreCaja() {
		return cierreCaja;
	}

	public void setCierreCaja(CierreCaja cierreCaja) {
		this.cierreCaja = cierreCaja;
	}
	
	

}
