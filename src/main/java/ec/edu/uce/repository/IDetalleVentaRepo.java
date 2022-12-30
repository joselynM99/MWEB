package ec.edu.uce.repository;

import java.util.List;

import ec.edu.uce.modelo.DetalleVenta;

public interface IDetalleVentaRepo {

	void insertarDetalleVenta(DetalleVenta detalleVenta);

	DetalleVenta buscarDetalleVenta(Integer id);

	List<DetalleVenta> buscarTodosDetalleVenta();

	void actualizarVenta(DetalleVenta detalleVenta);

	void eliminarDetalleVenta(Integer id);

}
