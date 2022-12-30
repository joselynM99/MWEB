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
@Table(name = "ingreso_adicional")
public class IngresoAdicional {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ingreso_adicional")
	@SequenceGenerator(name = "seq_ingreso_adicional", sequenceName = "seq_ingreso_adicional", allocationSize = 1)
	@Column(name = "inad_id")
	private Integer id;
	
	@Column(name = "inad_motivo")
	private String motivo;
	
	@Column(name = "inad_monto")
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
