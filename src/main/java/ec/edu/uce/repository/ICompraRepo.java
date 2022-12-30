package ec.edu.uce.repository;

import java.time.LocalDateTime;
import java.util.List;

import ec.edu.uce.modelo.Compra;

public interface ICompraRepo {

	void insertarCompra(Compra compra);

	Compra buscarCompra(Integer id);

	List<Compra> buscarTodosCompra();

	void actualizarCompra(Compra compra);

	void eliminarCompra(Integer id);

	List<Compra> buscarPorFechaTO(LocalDateTime fechaInicio, LocalDateTime fechaFinal);

}
