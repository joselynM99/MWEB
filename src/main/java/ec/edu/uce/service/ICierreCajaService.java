package ec.edu.uce.service;

import java.math.BigDecimal;
import java.util.List;

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


}
