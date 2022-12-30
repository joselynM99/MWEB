package ec.edu.uce.repository;

import java.util.List;

import ec.edu.uce.modelo.IngresoAdicional;

public interface IIngresoAdicionalRepo {
	
	void insertarIngresoAdicional(IngresoAdicional ingresoAdicional);

	IngresoAdicional buscarIngresoAdicional(Integer id);

	List<IngresoAdicional> buscarTodosIngresoAdicional();

	void actualizarIngresoAdicional(IngresoAdicional ingresoAdicional);

	void eliminarIngresoAdicional(Integer id);

}
