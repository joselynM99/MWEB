package ec.edu.uce.service;

import java.util.List;

import ec.edu.uce.modelo.Impuesto;

public interface IImpuestoService {
	void insertarImpuesto(Impuesto impuesto);

	Impuesto buscarImpuesto(Integer id);

	List<Impuesto> buscarTodosImpuesto();

	void actualizarImpuesto(Impuesto impuesto);

	void eliminarImpuesto(Integer id);
}
