package ec.edu.uce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.IngresoAdicional;
import ec.edu.uce.repository.IIngresoAdicionalRepo;

@Service
public class IngresoAdicionalServiceImpl implements IIngresoAdicionalService {

	@Autowired
	private IIngresoAdicionalRepo ingresoAdicionalRepo;

	@Override
	public void insertarIngresoAdicional(IngresoAdicional ingresoAdicional) {
		this.ingresoAdicionalRepo.insertarIngresoAdicional(ingresoAdicional);
	}

	@Override
	public IngresoAdicional buscarIngresoAdicional(Integer id) {
		return this.ingresoAdicionalRepo.buscarIngresoAdicional(id);
	}

	@Override
	public List<IngresoAdicional> buscarTodosIngresoAdicional() {
		return this.ingresoAdicionalRepo.buscarTodosIngresoAdicional();
	}

	@Override
	public void actualizarIngresoAdicional(IngresoAdicional ingresoAdicional) {
		this.ingresoAdicionalRepo.actualizarIngresoAdicional(ingresoAdicional);
	}

	@Override
	public void eliminarIngresoAdicional(Integer id) {
		this.ingresoAdicionalRepo.eliminarIngresoAdicional(id);
	}

}
