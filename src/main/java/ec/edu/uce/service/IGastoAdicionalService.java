package ec.edu.uce.service;

import java.util.List;

import ec.edu.uce.modelo.GastoAdicional;

public interface IGastoAdicionalService {
	void insertarGastoAdicional(GastoAdicional gastoAdicional);

	GastoAdicional buscarGastoAdicional(Integer id);

	List<GastoAdicional> buscarTodosGastoAdicional();

	void actualizarGastoAdicional(GastoAdicional gastoAdicional);

	void eliminarGastoAdicional(Integer id);

}
