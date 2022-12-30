package ec.edu.uce.service;

import java.util.List;

import ec.edu.uce.modelo.Caja;

public interface ICajaService {
	
	void insertarCaja(Caja caja);

	Caja buscarCaja(Integer id);

	List<Caja> buscarTodosCaja();

	void actualizarCaja(Caja caja);

	void eliminarCaja(Integer id);
	
	Caja buscarCajaPorNombre(String nombreCaja);

}
