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
import ec.edu.uce.modelo.Usuario;

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

	@Override
	public List<CierreCaja> buscarCierreCajas(LocalDateTime fechaInicio, LocalDateTime fechaFin, Caja caja,
			Boolean estado, Usuario usuario) {
		return this.cierreCajaService.buscarCierreCajas(fechaInicio, fechaFin, caja, estado, usuario);
	}

	@Override
	public ProdMasVendido buscarProductosMasVendidos(LocalDateTime fechaInicio, LocalDateTime fechaFin,
			Producto producto) {
		List<DetalleVenta> detalles = this.detalleVentaService.buscarProductosMasVendidos(fechaInicio, fechaFin,
				producto);
		ProdMasVendido p = new ProdMasVendido();
		for (DetalleVenta d : detalles) {
			p.setCantidad(p.getCantidad() + d.getCantidad());
			
		}
		
		p.setCodigoBarras(producto.getCodigoBarras());
		p.setNombre(producto.getNombre());

		return p;
	}
	
	@Override
	
	public List<ProdMasVendido> hacerListaProdMasVendidos(LocalDateTime fechaInicio, LocalDateTime fechaFin){
		
		List<ProdMasVendido> vendidos = new ArrayList<ProdMasVendido>();
		
		List<DetalleVenta> detalles = this.detalleVentaService.listaProdVendidosFecha(fechaInicio, fechaFin);
		
		for(DetalleVenta d: detalles) {
			vendidos.add(this.buscarProductosMasVendidos(fechaInicio, fechaFin, d.getProducto()));
		}
		
		vendidos.sort(Comparator.comparing(ProdMasVendido::getCantidad));
		return vendidos;
		
	}

}
