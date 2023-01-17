package ec.edu.uce.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import ec.edu.uce.modelo.Caja;
import ec.edu.uce.modelo.CierreCaja;
import ec.edu.uce.modelo.Usuario;

public interface ICierreCajaService {
	void insertarCierreCaja(CierreCaja cierreCaja);

	CierreCaja buscarCierreCaja(Integer id);

	List<CierreCaja> buscarTodosCierreCaja();

	void actualizarCierreCaja(CierreCaja cierreCaja);

	void eliminarCierreCaja(Integer id);
	
	boolean buscarCierreCajaActivo(Usuario usuario);

	void abrirCaja(Usuario usuario, BigDecimal valorInicial);

	CierreCaja obtenerCierreCajaActivo(Usuario usuario);


	void cerrarCaja(Usuario usuario, BigDecimal diferencia, BigDecimal valorContable, BigDecimal valorCierre);

	List<CierreCaja> buscarCierreCajas(LocalDateTime fechaInicio, LocalDateTime fechaFin, Caja caja, Boolean estado,
			Usuario usuario);


}
