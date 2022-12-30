package ec.edu.uce.service;

import java.util.List;

import ec.edu.uce.modelo.IngresoAdicional;

public interface IIngresoAdicionalService {
	void insertarIngresoAdicional(IngresoAdicional ingresoAdicional);

	IngresoAdicional buscarIngresoAdicional(Integer id);

	List<IngresoAdicional> buscarTodosIngresoAdicional();

	void actualizarIngresoAdicional(IngresoAdicional ingresoAdicional);

	void eliminarIngresoAdicional(Integer id);

}
