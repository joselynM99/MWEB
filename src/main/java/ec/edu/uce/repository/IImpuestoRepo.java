package ec.edu.uce.repository;

import java.util.List;

import ec.edu.uce.modelo.Impuesto;

public interface IImpuestoRepo {
	
	void insertarImpuesto(Impuesto impuesto);

	Impuesto buscarImpuesto(Integer id);

	List<Impuesto> buscarTodosImpuesto();

	void actualizarImpuesto(Impuesto impuesto);

	void eliminarImpuesto(Integer id);

}
