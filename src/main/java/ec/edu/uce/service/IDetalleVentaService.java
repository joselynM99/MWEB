package ec.edu.uce.service;

import java.math.BigDecimal;
import java.util.List;

import ec.edu.uce.modelo.DetalleVenta;
import ec.edu.uce.modelo.Impuesto;

public interface IDetalleVentaService {

	void insertarDetalleVenta(DetalleVenta detalleVenta);

	DetalleVenta buscarDetalleVenta(Integer id);

	List<DetalleVenta> buscarTodosDetalleVenta();

	void actualizarVenta(DetalleVenta detalleVenta);

	void eliminarDetalleVenta(Integer id);



	BigDecimal calcularValor(Integer cantidad, BigDecimal precio);

}
