package ec.edu.uce.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.DetalleVenta;
import ec.edu.uce.modelo.Impuesto;
import ec.edu.uce.modelo.Producto;
import ec.edu.uce.repository.IDetalleVentaRepo;

@Service
public class DetalleVentaServiceImpl implements IDetalleVentaService {

	@Autowired
	private IDetalleVentaRepo detalleVentaRepo;

	@Override
	public void insertarDetalleVenta(DetalleVenta detalleVenta) {
		this.detalleVentaRepo.insertarDetalleVenta(detalleVenta);
	}

	@Override
	public DetalleVenta buscarDetalleVenta(Integer id) {
		return this.detalleVentaRepo.buscarDetalleVenta(id);
	}

	@Override
	public List<DetalleVenta> buscarTodosDetalleVenta() {
		return this.detalleVentaRepo.buscarTodosDetalleVenta();
	}

	@Override
	public void actualizarVenta(DetalleVenta detalleVenta) {
		this.detalleVentaRepo.actualizarVenta(detalleVenta);
	}

	@Override
	public void eliminarDetalleVenta(Integer id) {
		this.detalleVentaRepo.eliminarDetalleVenta(id);
	}

	@Override
	public BigDecimal calcularValor(Double cantidad, BigDecimal precio) {

		return precio.multiply(new BigDecimal(cantidad));
	}

	@Override
	public List<DetalleVenta> buscarProductosMasVendidos(LocalDateTime fechaInicio, LocalDateTime fechaFin,
			Producto producto) {
		return this.detalleVentaRepo.buscarDetalleVentaProductoFecha(producto, fechaInicio, fechaFin);
	}
	
	@Override
	public List<DetalleVenta> listaProdVendidosFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		return this.detalleVentaRepo.buscarDetalleVentaFecha(fechaInicio, fechaFin);
	}

}
