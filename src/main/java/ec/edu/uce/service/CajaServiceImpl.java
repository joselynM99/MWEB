package ec.edu.uce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.Caja;
import ec.edu.uce.repository.ICajaRepo;

@Service
public class CajaServiceImpl implements ICajaService {
	
	@Autowired
	private ICajaRepo cajaRepo;

	@Override
	public void insertarCaja(Caja caja) {
		this.cajaRepo.insertarCaja(caja);
	}

	@Override
	public Caja buscarCaja(Integer id) {
		return this.cajaRepo.buscarCaja(id);
	}

	@Override
	public List<Caja> buscarTodosCaja() {
		return this.cajaRepo.buscarTodosCaja();
	}

	@Override
	public void actualizarCaja(Caja caja) {
		this.cajaRepo.actualizarCaja(caja);
	}

	@Override
	public void eliminarCaja(Integer id) {
		this.cajaRepo.eliminarCaja(id);
	}

	@Override
	public Caja buscarCajaPorNombre(String nombreCaja) {
		return this.cajaRepo.buscarCajaPorNombre(nombreCaja);
	}

}
