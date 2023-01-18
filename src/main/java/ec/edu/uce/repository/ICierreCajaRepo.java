package ec.edu.uce.repository;

import java.time.LocalDateTime;
import java.util.List;

import ec.edu.uce.modelo.Caja;
import ec.edu.uce.modelo.CierreCaja;
import ec.edu.uce.modelo.Usuario;
import ec.edu.uce.modelo.Venta;

public interface ICierreCajaRepo {

	void insertarCierreCaja(CierreCaja cierreCaja);

	CierreCaja buscarCierreCaja(Integer id);

	List<CierreCaja> buscarTodosCierreCaja();

	void actualizarCierreCaja(CierreCaja cierreCaja);

	void eliminarCierreCaja(Integer id);

	boolean buscarCierreCajaActivo(Usuario usuario);

	CierreCaja obtenerCierreCajaActivo(Usuario usuario);

	List<CierreCaja> buscarCierreCajas(LocalDateTime fechaInicio, LocalDateTime fechaFin, Caja caja, Boolean estado,
			Usuario usuario);

	CierreCaja buscarCierreCaja(Venta venta, Caja caja);

}
