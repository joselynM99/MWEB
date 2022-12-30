package ec.edu.uce.repository;

import java.util.List;

import ec.edu.uce.modelo.CierreCaja;
import ec.edu.uce.modelo.Usuario;

public interface ICierreCajaRepo {
	
	void insertarCierreCaja(CierreCaja cierreCaja);

	CierreCaja buscarCierreCaja(Integer id);

	List<CierreCaja> buscarTodosCierreCaja();

	void actualizarCierreCaja(CierreCaja cierreCaja);

	void eliminarCierreCaja(Integer id);

	boolean buscarCierreCajaActivo(Usuario usuario);

	CierreCaja obtenerCierreCajaActivo(Usuario usuario);

}
