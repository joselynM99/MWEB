package ec.edu.uce.service;

import java.util.List;

import ec.edu.uce.modelo.Seccion;

public interface ISeccionService {
	void insertarCaja(Seccion seccion);

	Seccion buscarSeccion(Integer id);

	List<Seccion> buscarTodosSeccion();

	void actualizarSeccion(Seccion seccion);

	void eliminarSeccion(Integer id);
}
