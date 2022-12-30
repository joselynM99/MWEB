package ec.edu.uce.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.Compra;
import ec.edu.uce.modelo.DetalleCompra;
import ec.edu.uce.modelo.Producto;
import ec.edu.uce.modelo.Proveedor;
import ec.edu.uce.repository.ICompraRepo;

@Service
public class CompraServiceImpl implements ICompraService {

	@Autowired
	private ICompraRepo compraRepo;

	@Autowired
	private IDetalleCompraService detalleCompraService;

	@Autowired
	private IProductoService productoService;

	@Autowired
	private IProveedorService proveedorService;

	@Override
	public void insertarCompra(Compra compra) {
		this.compraRepo.insertarCompra(compra);
	}

	@Override
	public Compra buscarCompra(Integer id) {
		return this.compraRepo.buscarCompra(id);
	}

	@Override
	public List<Compra> buscarTodosCompra() {
		return this.compraRepo.buscarTodosCompra();
	}

	@Override
	public void actualizarCompra(Compra compra) {
		this.compraRepo.actualizarCompra(compra);
	}

	@Override
	public void eliminarCompra(Integer id) {
		Compra compra = this.compraRepo.buscarCompra(id);
		List<DetalleCompra> compras = compra.getDetalles();

		for (DetalleCompra d : compras) {
			Producto p = d.getProducto();
//			p.setCantidad(p.getCantidad() - d.getCantidad());
			this.productoService.actualizarProducto(p);
		}

		this.compraRepo.eliminarCompra(id);
	}

	@Override
	public BigDecimal calcularValorAPagar(List<DetalleCompra> detalles) {

		BigDecimal total = new BigDecimal(0);
		for (DetalleCompra d : detalles) {
			total = d.getTotal().add(total);
		}

		return total;
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void realizarCompra(List<DetalleCompra> detalles, Proveedor proveedor, LocalDateTime fecha) {

		for (DetalleCompra d : detalles) {
			Producto p = d.getProducto();

//			p.setCantidad(p.getCantidad() + d.getCantidad());

			p.setId(p.getId());

			this.productoService.actualizarProducto(p);

//			d.setTotal(this.detalleCompraService.calcularValorCompra(d.getCantidad(), p.getCostoBruto()));
			this.detalleCompraService.insertarDetalleCompra(d);

		}
		Compra compra = new Compra();
		compra.setDetalles(detalles);
//		compra.setValorCompra(this.calcularValorAPagar(detalles));
		compra.setFecha(fecha);
		compra.setProveedor(proveedor);

		this.insertarCompra(compra);

		for (DetalleCompra d : detalles) {
			d.setCompra(compra);
			this.detalleCompraService.actualizarDetalleCompra(d);
		}

	}

	@Override
	public List<Compra> buscarPorFechaTO(LocalDateTime fechaInicio, LocalDateTime fechaFinal) {
		return this.compraRepo.buscarPorFechaTO(fechaInicio, fechaFinal);
	}

}
