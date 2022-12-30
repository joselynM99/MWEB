package ec.edu.uce.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import ec.edu.uce.modelo.Compra;
import ec.edu.uce.modelo.DetalleCompra;
import ec.edu.uce.modelo.Proveedor;

public interface ICompraService {

	void insertarCompra(Compra compra);

	Compra buscarCompra(Integer id);

	List<Compra> buscarTodosCompra();

	void actualizarCompra(Compra compra);

	void eliminarCompra(Integer id);

	void realizarCompra(List<DetalleCompra> detalles, Proveedor proveedor, LocalDateTime fecha);

	BigDecimal calcularValorAPagar(List<DetalleCompra> detalles);
	
	List<Compra> buscarPorFechaTO(LocalDateTime fechaInicio, LocalDateTime fechaFinal);


}
