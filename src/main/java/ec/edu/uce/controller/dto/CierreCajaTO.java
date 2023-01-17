package ec.edu.uce.controller.dto;

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

/**
 * @author jkmoncayo
 *
 */
public class CierreCajaTO {

	private Integer id;

	private boolean estado;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime fechaApertura;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime fechaCierre;

	private BigDecimal valorApertura;

	private BigDecimal valorContable;

	private BigDecimal valorCierre;

	private BigDecimal diferencia;

	private String caja;

	private String usuario;

	/**
	 * 
	 */
	public CierreCajaTO() {
		super();
	}

	/**
	 * @param id
	 * @param estado
	 * @param fechaApertura
	 * @param fechaCierre
	 * @param valorApertura
	 * @param valorContable
	 * @param valorCierre
	 * @param diferencia
	 * @param caja
	 * @param usuario
	 */
	public CierreCajaTO(Integer id, boolean estado, LocalDateTime fechaApertura, LocalDateTime fechaCierre,
			BigDecimal valorApertura, BigDecimal valorContable, BigDecimal valorCierre, BigDecimal diferencia,
			String caja, String usuario) {
		super();
		this.id = id;
		this.estado = estado;
		this.fechaApertura = fechaApertura;
		this.fechaCierre = fechaCierre;
		this.valorApertura = valorApertura;
		this.valorContable = valorContable;
		this.valorCierre = valorCierre;
		this.diferencia = diferencia;
		this.caja = caja;
		this.usuario = usuario;
	}

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

	public BigDecimal getValorCierre() {
		return valorCierre;
	}

	public void setValorCierre(BigDecimal valorCierre) {
		this.valorCierre = valorCierre;
	}

	public BigDecimal getDiferencia() {
		return diferencia;
	}

	public void setDiferencia(BigDecimal diferencia) {
		this.diferencia = diferencia;
	}

	public String getCaja() {
		return caja;
	}

	public void setCaja(String caja) {
		this.caja = caja;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
