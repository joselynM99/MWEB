package ec.edu.uce.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.controller.dto.ProdMasVendido;
import ec.edu.uce.modelo.Caja;
import ec.edu.uce.modelo.CierreCaja;
import ec.edu.uce.modelo.DetalleVenta;
import ec.edu.uce.modelo.Producto;
import ec.edu.uce.modelo.SubProducto;
import ec.edu.uce.modelo.Usuario;
import ec.edu.uce.modelo.Venta;

@Service
public class ReportesServiceImpl implements IReportesService {
	@Autowired
	private IProductoService productoService;

	@Autowired
	private IVentaService ventaService;

	@Autowired
	private IDetalleVentaService detalleVentaService;

	@Autowired
	private ICompraService compraService;

	@Autowired
	private IDetalleCompraService detalleCompraService;

	@Autowired
	private ICajaService cajaService;

	@Autowired
	private ICierreCajaService cierreCajaService;

	@Autowired
	private ISubProductoService subProductoService;

	@Override
	public List<CierreCaja> buscarCierreCajas(LocalDateTime fechaInicio, LocalDateTime fechaFin, Caja caja,
			Boolean estado, Usuario usuario) {
		return this.cierreCajaService.buscarCierreCajas(fechaInicio, fechaFin, caja, estado, usuario);
	}

	@Override
	public ProdMasVendido buscarProductosMasVendidos(LocalDateTime fechaInicio, LocalDateTime fechaFin,
			Producto producto, SubProducto subProducto) {
		List<DetalleVenta> detalles = this.detalleVentaService.buscarProductosMasVendidos(fechaInicio, fechaFin,
				producto, subProducto);
		ProdMasVendido p = new ProdMasVendido();
		p.setCantidad(0.0);
		System.out.println("buscarProductosMasVendidos");
		for (DetalleVenta d : detalles) {
			System.out.println(d.getCantidad());
			p.setCantidad(p.getCantidad() + d.getCantidad());

		}

		if (producto != null) {
			p.setCodigoBarras(producto.getCodigoBarras());
			p.setNombre(producto.getNombre());
		} else {
			p.setCodigoBarras(subProducto.getCodigoBarras());
			p.setNombre(subProducto.getNombre());
		}

		return p;
	}

	@Override

	public List<ProdMasVendido> hacerListaProdMasVendidos(LocalDateTime fechaInicio, LocalDateTime fechaFin) {

		List<ProdMasVendido> vendidos = new ArrayList<ProdMasVendido>();

		List<DetalleVenta> detalles = this.detalleVentaService.listaProdVendidosFecha(fechaInicio, fechaFin);
		System.out.println("hacerListaProdMasVendidos");
		for (DetalleVenta d : detalles) {

			vendidos.add(this.buscarProductosMasVendidos(fechaInicio, fechaFin, d.getProducto(), d.getSubProducto()));
		}

		vendidos.sort(Comparator.comparing(ProdMasVendido::getCantidad).reversed());

		return vendidos;

	}

	@Override
	public void borrarProductoVenta(DetalleVenta detalle) {
		Venta v = detalle.getVenta();
		v.setTotal(v.getTotal().subtract(detalle.getTotal()));

		Producto p = detalle.getProducto();
		SubProducto sp = detalle.getSubProducto();
		if (p != null) {
			p.setStockActual(p.getStockActual() + detalle.getCantidad());
			System.out.println(p.getId());
			this.productoService.actualizarProducto(p);
		} else if (sp != null) {

			sp.setStockActual(sp.getStockActual() + detalle.getCantidad());
			System.out.println(sp.getId());
			this.subProductoService.actualizarSubProducto(sp);
		}

		this.detalleVentaService.eliminarDetalleVenta(detalle.getId());
		this.ventaService.actualizarVenta(v);

	}

}
