package ec.edu.uce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ec.edu.uce.controller.dto.CierreCajaTO;
import ec.edu.uce.controller.dto.ProdMasVendido;
import ec.edu.uce.modelo.Caja;
import ec.edu.uce.modelo.CierreCaja;
import ec.edu.uce.modelo.DetalleVenta;
import ec.edu.uce.modelo.Usuario;
import ec.edu.uce.service.ICajaService;
import ec.edu.uce.service.ICierreCajaService;
import ec.edu.uce.service.ICompraService;
import ec.edu.uce.service.IDetalleCompraService;
import ec.edu.uce.service.IDetalleVentaService;
import ec.edu.uce.service.IProductoService;
import ec.edu.uce.service.IReportesService;
import ec.edu.uce.service.IUsuarioService;
import ec.edu.uce.service.IVentaService;

@Controller
@RequestMapping("/reportes")
public class ReportesController {

	@Autowired
	private IReportesService reportesService;

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
	private IUsuarioService usuarioService;

	@GetMapping("/menu")
	public String obtenerPagReportes(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}
		
		model.addAttribute("nombreUser", userDetails.getUsername());

		return "pages/menuReportes";
	}

	@GetMapping("/historialCierreCajas")
	public String obtenerPagReporteCierreCaja(Model model, CierreCajaTO cierreCaja) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}
		model.addAttribute("nombreUser", userDetails.getUsername());
		List<Usuario> usuarios = this.usuarioService.buscarTodosUsuario();
		List<Caja> cajas = this.cajaService.buscarTodosCaja();

		model.addAttribute("cajas", cajas);
		model.addAttribute("usuarios", usuarios);

		model.addAttribute("cierreCaja", cierreCaja);

		return "pages/reporteCierresCaja";
	}

	@GetMapping("/buscarCierres")
	public String buscarCierres(CierreCajaTO cierreCaja, Model model) {
		Caja caja = new Caja();
		Usuario usuario = new Usuario();
		if (cierreCaja.getCaja() == null || cierreCaja.getCaja() == "") {
			caja = null;
		} else {
			caja = this.cajaService.buscarCaja(Integer.parseInt(cierreCaja.getCaja()));
		}

		if (cierreCaja.getUsuario() == null || cierreCaja.getUsuario() == "") {
			usuario = null;
		} else {
			usuario = this.usuarioService.buscarUsuario(Integer.parseInt(cierreCaja.getUsuario()));
		}

		List<CierreCaja> cierres = this.reportesService.buscarCierreCajas(cierreCaja.getFechaApertura(),
				cierreCaja.getFechaCierre(), caja, cierreCaja.isEstado(), usuario);

		List<Usuario> usuarios = this.usuarioService.buscarTodosUsuario();
		List<Caja> cajas = this.cajaService.buscarTodosCaja();

		model.addAttribute("cierreCaja", cierreCaja);
		model.addAttribute("cajas", cajas);
		model.addAttribute("usuarios", usuarios);

		model.addAttribute("cierres", cierres);

		return "pages/reporteCierresCaja";

	}

	@GetMapping("/productoMasVendido")
	public String obtenerPagReporteProductosMasVendidos(Model model, CierreCajaTO cierreCaja) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}
		model.addAttribute("nombreUser", userDetails.getUsername());
		model.addAttribute("cierreCaja", cierreCaja);
		return "pages/productosMasVendidos";
	}

	@GetMapping("/buscarProductosMasVendidos")
	public String buscarReporteProductosMasVendidos(Model model, CierreCajaTO cierreCaja) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}

		List<ProdMasVendido> vendidos = this.reportesService.hacerListaProdMasVendidos(cierreCaja.getFechaApertura(),
				cierreCaja.getFechaCierre());

		model.addAttribute("vendidos", vendidos);
		model.addAttribute("cierreCaja", cierreCaja);
		return "pages/productosMasVendidos";
	}
}
