package ec.edu.uce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.Marca;
import ec.edu.uce.repository.IMarcaRepo;

@Service
public class MarcaServiceImpl implements IMarcaService {

	@Autowired
	private IMarcaRepo marcaRepo;

	@Override
	public void insertarMarca(Marca marca) {
		this.marcaRepo.insertarMarca(marca);
	}

	@Override
	public Marca buscarMarca(Integer id) {
		return this.marcaRepo.buscarMarca(id);
	}

	@Override
	public List<Marca> buscarTodosMarca() {
		return this.marcaRepo.buscarTodosMarca();
	}

	@Override
	public void actualizarMarca(Marca marca) {
		this.marcaRepo.actualizarMarca(marca);
	}

	@Override
	public void eliminarMarca(Integer id) {
		this.marcaRepo.eliminarMarca(id);
	}

}
