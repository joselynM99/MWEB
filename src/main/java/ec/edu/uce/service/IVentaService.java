package ec.edu.uce.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import ec.edu.uce.controller.dto.DescuentoTO;
import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.Compra;
import ec.edu.uce.modelo.DetalleVenta;
import ec.edu.uce.modelo.Venta;

public interface IVentaService {

	void insertarVenta(Venta venta);

	Venta buscarVenta(Integer id);

	List<Venta> buscarTodosVenta();

	void actualizarVenta(Venta venta);

	void eliminarVenta(Integer id);

//	void realizarVenta(List<DetalleVenta> detalles);

	BigDecimal calcularValorAPagar(List<DetalleVenta> detalles);

	List<Venta> buscarPorFechaTO(LocalDateTime fechaInicio, LocalDateTime fechaFinal);
	
	List<Venta> buscarPorVentasCaja(Integer caja, LocalDateTime fechaCajaAbierta);

//	void realizarVenta(List<DetalleVenta> detalles, DescuentoTO descuento);

	void realizarVenta(List<DetalleVenta> detalles, DescuentoTO descuento, Cliente cliente);

}
