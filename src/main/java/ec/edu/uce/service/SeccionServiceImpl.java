package ec.edu.uce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.Seccion;
import ec.edu.uce.repository.ISeccionRepo;

@Service
public class SeccionServiceImpl implements ISeccionService {

	@Autowired
	private ISeccionRepo seccionRepo;

	
	@Override
	public void insertarCaja(Seccion seccion) {
		this.seccionRepo.insertarCaja(seccion);
	}

	@Override
	public Seccion buscarSeccion(Integer id) {
		return this.seccionRepo.buscarSeccion(id);
	}

	@Override
	public List<Seccion> buscarTodosSeccion() {
		return this.seccionRepo.buscarTodosSeccion();
	}

	@Override
	public void actualizarSeccion(Seccion seccion) {
		this.seccionRepo.actualizarSeccion(seccion);
	}

	@Override
	public void eliminarSeccion(Integer id) {
		this.seccionRepo.eliminarSeccion(id);
	}

}
