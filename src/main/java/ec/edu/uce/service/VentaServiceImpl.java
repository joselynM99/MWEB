package ec.edu.uce.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import ec.edu.uce.controller.dto.DescuentoTO;
import ec.edu.uce.modelo.Caja;
import ec.edu.uce.modelo.CierreCaja;
import ec.edu.uce.modelo.DetalleVenta;
import ec.edu.uce.modelo.Producto;
import ec.edu.uce.modelo.SubProducto;
import ec.edu.uce.modelo.Usuario;
import ec.edu.uce.modelo.Venta;
import ec.edu.uce.repository.IVentaRepo;

@Service
public class VentaServiceImpl implements IVentaService {

	@Autowired
	private IVentaRepo ventaRepo;

	@Autowired
	private IProductoService productoService;

	@Autowired
	private IDetalleVentaService detalleVentaService;

	@Autowired
	private ISubProductoService subProductoService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private ICierreCajaService cierreCajaService;
	
	@Autowired
	private ICajaService cajaService;

	@Override
	public void insertarVenta(Venta venta) {
		this.ventaRepo.insertarVenta(venta);
	}

	@Override
	public Venta buscarVenta(Integer id) {
		return this.ventaRepo.buscarVenta(id);
	}

	@Override
	public List<Venta> buscarTodosVenta() {
		return this.ventaRepo.buscarTodosVenta();
	}

	@Override
	public void actualizarVenta(Venta venta) {
		this.ventaRepo.actualizarVenta(venta);
	}

	@Override
	public void eliminarVenta(Integer id) {
		Venta venta = this.ventaRepo.buscarVenta(id);
		List<DetalleVenta> ventas = venta.getDetalles();

		for (DetalleVenta d : ventas) {
			Producto p = d.getProducto();
//			p.setCantidad(p.getCantidad() + d.getCantidad());
			this.productoService.actualizarProducto(p);
		}
		this.ventaRepo.eliminarVenta(id);
	}

	@Override
	public BigDecimal calcularValorAPagar(List<DetalleVenta> detalles) {

		BigDecimal total = new BigDecimal(0);
		for (DetalleVenta d : detalles) {
			total = d.getTotal().add(total);
		}

		return total;
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void realizarVenta(List<DetalleVenta> detalles, DescuentoTO descuento) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		}

		Usuario usuario = this.usuarioService.buscarUsuarioPorNombreUsuario(userDetails.getUsername());
		CierreCaja cierreCajaAbierto = this.cierreCajaService.obtenerCierreCajaActivo(usuario);
		
		
		for (DetalleVenta d : detalles) {
			Producto p = d.getProducto();
			SubProducto s = this.subProductoService.buscarProductoPorCodigoBarras(p.getCodigoBarras());
			if (s != null) {
				s.setStockActual(s.getStockActual() - d.getCantidad());
				s.setId(s.getId());
				this.subProductoService.actualizarSubProducto(s);
				s = this.subProductoService.buscarProductoPorCodigoBarras(p.getCodigoBarras());
				if (s.getStockActual() == 0) {
					Producto pe = s.getProducto();
					pe.setStockActual(pe.getStockActual() - 1);
					this.productoService.actualizarProducto(pe);
				}
				d.setSubProducto(s);
				d.setProducto(null);

			} else {
				p.setStockActual(p.getStockActual() - d.getCantidad());
				p.setId(p.getId());
				this.productoService.actualizarProducto(p);
				d.setProducto(p);
				d.setSubProducto(null);

			}

			this.detalleVentaService.insertarDetalleVenta(d);

		}

		Venta venta = new Venta();
		venta.setDetalles(detalles);
		venta.setFecha(LocalDateTime.now());
		
		
		if(descuento == null || descuento.getValorDesceunto()==null || descuento.getValorDesceunto()==0) {
			venta.setTotal(calcularValorAPagar(detalles));
		}else {
			BigDecimal total = this.calcularValorAPagar(detalles);
			
			if (descuento.getTipoDesceunto()) {
				total = total.subtract(
						total.multiply(new BigDecimal(descuento.getValorDesceunto())).divide(new BigDecimal(100)));			
				
			} else {
				total = total.subtract(new BigDecimal(descuento.getValorDesceunto()));				
			
			}
			
			venta.setTotal(total);
		}
		
		Caja caja = cierreCajaAbierto.getCaja();
		
		List<Venta> listaV = caja.getVentas();
		listaV.add(venta);
		
		caja.setVentas(listaV);
		caja.setId(caja.getId());
		
		venta.setCaja(caja);

		this.insertarVenta(venta);
		
		this.cajaService.actualizarCaja(caja);
		
		for (DetalleVenta d : detalles) {
			d.setVenta(venta);
			this.detalleVentaService.actualizarVenta(d);
		}

	}

	@Override
	public List<Venta> buscarPorFechaTO(LocalDateTime fechaInicio, LocalDateTime fechaFinal) {
		return this.ventaRepo.buscarPorFechaTO(fechaInicio, fechaFinal);
	}

	@Override
	public List<Venta> buscarPorVentasCaja(Integer caja, LocalDateTime fechaCajaAbierta) {
		return this.ventaRepo.buscarPorVentasCaja(caja, fechaCajaAbierta);
	}

}
