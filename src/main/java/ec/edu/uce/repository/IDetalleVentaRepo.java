package ec.edu.uce.repository;

import java.time.LocalDateTime;
import java.util.List;

import ec.edu.uce.modelo.DetalleVenta;
import ec.edu.uce.modelo.Producto;

public interface IDetalleVentaRepo {

	void insertarDetalleVenta(DetalleVenta detalleVenta);

	DetalleVenta buscarDetalleVenta(Integer id);

	List<DetalleVenta> buscarTodosDetalleVenta();

	void actualizarVenta(DetalleVenta detalleVenta);

	void eliminarDetalleVenta(Integer id);

	List<DetalleVenta> buscarDetalleVentaProductoFecha(Producto producto, LocalDateTime fechaInicio,
			LocalDateTime fechaFin);

	List<DetalleVenta> buscarDetalleVentaFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin);

}
