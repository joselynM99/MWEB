package ec.edu.uce.controller.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Adicional {
	
	String nombre;
	BigDecimal monto;
	String motivo;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	
	

}
