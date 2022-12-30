package ec.edu.uce.repository;

import java.util.List;

import ec.edu.uce.modelo.Caja;

public interface ICajaRepo {
	
	void insertarCaja(Caja caja);

	Caja buscarCaja(Integer id);

	List<Caja> buscarTodosCaja();

	void actualizarCaja(Caja caja);

	void eliminarCaja(Integer id);

	Caja buscarCajaPorNombre(String nombreCaja);

}
