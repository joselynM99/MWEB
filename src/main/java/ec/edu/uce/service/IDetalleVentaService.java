package ec.edu.uce.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import ec.edu.uce.controller.dto.ProdMasVendido;
import ec.edu.uce.modelo.DetalleVenta;
import ec.edu.uce.modelo.Impuesto;
import ec.edu.uce.modelo.Producto;

public interface IDetalleVentaService {

	void insertarDetalleVenta(DetalleVenta detalleVenta);

	DetalleVenta buscarDetalleVenta(Integer id);

	List<DetalleVenta> buscarTodosDetalleVenta();

	void actualizarVenta(DetalleVenta detalleVenta);

	void eliminarDetalleVenta(Integer id);

	BigDecimal calcularValor(Double cantidad, BigDecimal precio);

	List<DetalleVenta> buscarProductosMasVendidos(LocalDateTime fechaInicio, LocalDateTime fechaFin, Producto producto);

	List<DetalleVenta> listaProdVendidosFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin);

}
