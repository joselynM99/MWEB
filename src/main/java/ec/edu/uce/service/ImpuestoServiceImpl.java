package ec.edu.uce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.Impuesto;
import ec.edu.uce.repository.IImpuestoRepo;

@Service
public class ImpuestoServiceImpl implements IImpuestoService{

	@Autowired
	private IImpuestoRepo impuestoRepo;

	
	@Override
	public void insertarImpuesto(Impuesto impuesto) {
		this.impuestoRepo.insertarImpuesto(impuesto);
	}

	@Override
	public Impuesto buscarImpuesto(Integer id) {
		return this.impuestoRepo.buscarImpuesto(id);
	}

	@Override
	public List<Impuesto> buscarTodosImpuesto() {
		return this.impuestoRepo.buscarTodosImpuesto();
	}

	@Override
	public void actualizarImpuesto(Impuesto impuesto) {
		this.impuestoRepo.actualizarImpuesto(impuesto);
	}

	@Override
	public void eliminarImpuesto(Integer id) {
		this.impuestoRepo.eliminarImpuesto(id);
	}

}
