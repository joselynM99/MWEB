package ec.edu.uce.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "cierre_caja")
public class CierreCaja {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cica")
	@SequenceGenerator(name = "seq_cica", sequenceName = "seq_cica", allocationSize = 1)
	@Column(name="cica_id")
	private Integer id;

	@Column(name="cica_estado")
	private boolean estado;

	@Column(name="cica_fecha_apertura")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime fechaApertura;

	@Column(name="cica_fecha_cierre")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime fechaCierre;

	@Column(name="cica_valor_apertura")
	private BigDecimal valorApertura;

	@Column(name="cica_valor_contable")
	private BigDecimal valorContable;

	@Column(name="cica_diferencia")
	private BigDecimal diferencia;
	
	
	@OneToMany(mappedBy = "cierreCaja", cascade = CascadeType.ALL)
	private List<IngresoAdicional> ingresos;
	
	@OneToMany(mappedBy = "cierreCaja", cascade = CascadeType.ALL)
	private List<GastoAdicional> gastos;
	
	@ManyToOne
	@JoinColumn(name = "caja_id")
	private Caja caja;
	
	@ManyToOne
	@JoinColumn(name = "usua_id")
	private Usuario usuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public LocalDateTime getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(LocalDateTime fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public LocalDateTime getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(LocalDateTime fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public BigDecimal getValorApertura() {
		return valorApertura;
	}

	public void setValorApertura(BigDecimal valorApertura) {
		this.valorApertura = valorApertura;
	}

	public BigDecimal getValorContable() {
		return valorContable;
	}

	public void setValorContable(BigDecimal valorContable) {
		this.valorContable = valorContable;
	}

	public BigDecimal getDiferencia() {
		return diferencia;
	}

	public void setDiferencia(BigDecimal diferencia) {
		this.diferencia = diferencia;
	}

	public List<IngresoAdicional> getIngresos() {
		return ingresos;
	}

	public void setIngresos(List<IngresoAdicional> ingresos) {
		this.ingresos = ingresos;
	}

	public List<GastoAdicional> getGastos() {
		return gastos;
	}

	public void setGastos(List<GastoAdicional> gastos) {
		this.gastos = gastos;
	}

	public Caja getCaja() {
		return caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	
	

}
