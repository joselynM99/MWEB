package ec.edu.uce.repository;

import java.util.List;

import ec.edu.uce.modelo.Marca;

public interface IMarcaRepo {
	
	void insertarMarca(Marca marca);

	Marca buscarMarca(Integer id);

	List<Marca> buscarTodosMarca();

	void actualizarMarca(Marca marca);

	void eliminarMarca(Integer id);

}
