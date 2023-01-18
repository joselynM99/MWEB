package ec.edu.uce.service;

import java.util.List;

import ec.edu.uce.modelo.Marca;

public interface IMarcaService {

	void insertarMarca(Marca marca);

	Marca buscarMarca(Integer id);

	List<Marca> buscarTodosMarca();

	void actualizarMarca(Marca marca);

	void eliminarMarca(Integer id);
	
	Marca buscarMarcaPorNombre(String nombreMarca);
}
