package ec.edu.uce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.GastoAdicional;
import ec.edu.uce.repository.IGastoAdicionalRepo;

@Service
public class GastoAdicionalServiceImpl implements IGastoAdicionalService {

	@Autowired
	private IGastoAdicionalRepo gastoAdicionalRepo;

	@Override
	public void insertarGastoAdicional(GastoAdicional gastoAdicional) {
		this.gastoAdicionalRepo.insertarGastoAdicional(gastoAdicional);
	}

	@Override
	public GastoAdicional buscarGastoAdicional(Integer id) {
		return this.gastoAdicionalRepo.buscarGastoAdicional(id);
	}

	@Override
	public List<GastoAdicional> buscarTodosGastoAdicional() {
		return this.gastoAdicionalRepo.buscarTodosGastoAdicional();
	}

	@Override
	public void actualizarGastoAdicional(GastoAdicional gastoAdicional) {
		this.gastoAdicionalRepo.actualizarGastoAdicional(gastoAdicional);
	}

	@Override
	public void eliminarGastoAdicional(Integer id) {
		this.gastoAdicionalRepo.eliminarGastoAdicional(id);
	}

}
