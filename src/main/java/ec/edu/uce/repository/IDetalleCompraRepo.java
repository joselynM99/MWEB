package ec.edu.uce.repository;

import java.util.List;

import ec.edu.uce.modelo.DetalleCompra;

public interface IDetalleCompraRepo {

	void insertarDetalleCompra(DetalleCompra detalleCompra);

	DetalleCompra buscarDetalleCompra(Integer id);

	List<DetalleCompra> buscarTodosDetalleCompra();

	void actualizarDetalleCompra(DetalleCompra detalleCompra);

	void eliminarDetalleCompra(Integer id);

}
