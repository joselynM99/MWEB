package ec.edu.uce.repository;

import java.util.List;

import ec.edu.uce.modelo.Seccion;

public interface ISeccionRepo {
	
	void insertarCaja(Seccion seccion);

	Seccion buscarSeccion(Integer id);

	List<Seccion> buscarTodosSeccion();

	void actualizarSeccion(Seccion seccion);

	void eliminarSeccion(Integer id);

}
