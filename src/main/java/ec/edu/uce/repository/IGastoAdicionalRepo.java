package ec.edu.uce.repository;

import java.util.List;

import ec.edu.uce.modelo.GastoAdicional;

public interface IGastoAdicionalRepo {

	void insertarGastoAdicional(GastoAdicional gastoAdicional);

	GastoAdicional buscarGastoAdicional(Integer id);

	List<GastoAdicional> buscarTodosGastoAdicional();

	void actualizarGastoAdicional(GastoAdicional gastoAdicional);

	void eliminarGastoAdicional(Integer id);

}
