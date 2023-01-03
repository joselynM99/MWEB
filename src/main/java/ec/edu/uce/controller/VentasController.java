package ec.edu.uce.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.uce.controller.dto.Adicional;
import ec.edu.uce.controller.dto.DescuentoTO;
import ec.edu.uce.modelo.Caja;
import ec.edu.uce.modelo.CierreCaja;
import ec.edu.uce.modelo.DetalleVenta;
import ec.edu.uce.modelo.GastoAdicional;
import ec.edu.uce.modelo.IngresoAdicional;
import ec.edu.uce.modelo.Producto;
import ec.edu.uce.modelo.SubProducto;
import ec.edu.uce.modelo.Usuario;
import ec.edu.uce.modelo.Venta;
import ec.edu.uce.service.ICajaService;
import ec.edu.uce.service.ICierreCajaService;
import ec.edu.uce.service.IDetalleVentaService;
import ec.edu.uce.service.IGastoAdicionalService;
import ec.edu.uce.service.IIngresoAdicionalService;
import ec.edu.uce.service.IProductoService;
import ec.edu.uce.service.IProveedorService;
import ec.edu.uce.service.ISubProductoService;
import ec.edu.uce.service.IUsuarioService;
import ec.edu.uce.service.IVentaService;

@Controller
public class VentasController {

	@Autowired
	private IProductoService productoService;

	@Autowired
	private IProveedorService proveedorService;

	@Autowired
	private IVentaService ventaService;

	@Autowired
	private IDetalleVentaService detalleVentaService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private ICierreCajaService cierreCajaService;

	@Autowired
	private ISubProductoService subProductoService;

	@Autowired
	private ICajaService cajaService;

	@Autowired
	private IIngresoAdicionalService ingresoAdicionalService;

	@Autowired
	private IGastoAdicionalService gastoAdicionalService;

	@GetMapping("/ventas")
	public String obtenerMenuVentas(Model model) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}
		
		model.addAttribute("nombreUser", userDetails.getUsername());
		return "pages/ventas";
	}

	@GetMapping("ventas/adicionales")
	public String obtenerVentanaAdicionales(Model model, Adicional adicional) {

		return "pages/adicionales";

	}

	@PostMapping("ventas/registarAdicional")
	public String registrarAdicional(Model model, @ModelAttribute Adicional adicional) {
	
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}

		Usuario usuario = this.usuarioService.buscarUsuarioPorNombreUsuario(userDetails.getUsername());

		Caja caja = usuario.getCaja();
		CierreCaja cierre = this.cierreCajaService.obtenerCierreCajaActivo(usuario);

		if (adicional.getNombre().contains("Ingreso")) {
			IngresoAdicional ingreso = new IngresoAdicional();
			ingreso.setMotivo(adicional.getMotivo());
			ingreso.setMonto(adicional.getMonto());
			ingreso.setCierreCaja(cierre);
			this.ingresoAdicionalService.insertarIngresoAdicional(ingreso);
		} else {
			GastoAdicional gasto = new GastoAdicional();
			gasto.setMotivo(adicional.getMotivo());
			gasto.setMonto(adicional.getMonto());
			gasto.setCierreCaja(cierre);
			this.gastoAdicionalService.insertarGastoAdicional(gasto);
		}
		return "redirect:/ventas/ventaNueva";
	}

	@GetMapping("/ventas/cierreCaja")
	public String obtenerVantanaCierreCaja(Model model, @ModelAttribute CierreCaja cierre) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}

		Usuario usuario = this.usuarioService.buscarUsuarioPorNombreUsuario(userDetails.getUsername());

		Caja caja = usuario.getCaja();
		cierre = this.cierreCajaService.obtenerCierreCajaActivo(usuario);

		List<Venta> ventasCaja = this.ventaService.buscarPorVentasCaja(caja.getId(), cierre.getFechaApertura());
		List<IngresoAdicional> listIngresos = cierre.getIngresos();

		List<GastoAdicional> listGastos = cierre.getGastos();

		BigDecimal totalVentas = new BigDecimal(0);
		BigDecimal ingresos = new BigDecimal(0);
		BigDecimal gastos = new BigDecimal(0);
		BigDecimal saldoFinal = new BigDecimal(0);

		for (Venta v : ventasCaja) {
			totalVentas = totalVentas.add(v.getTotal());
		}

		for (IngresoAdicional i : listIngresos) {
			ingresos = ingresos.add(i.getMonto());
			System.out.println(i.getMotivo());
		}

		for (GastoAdicional g : listGastos) {
			gastos = gastos.add(g.getMonto());
			System.out.println(g.getMotivo());
		}

		ingresos = ingresos.add(totalVentas).add(cierre.getValorApertura());
		saldoFinal = ingresos.subtract(gastos);

		System.out.print(totalVentas);
		System.out.print(ingresos);
		System.out.print(gastos);

		model.addAttribute("cierre", cierre);
		model.addAttribute("saldoFinal", saldoFinal);
		model.addAttribute("gastos", gastos);
		model.addAttribute("ingresos", ingresos);
		model.addAttribute("totalVentas", totalVentas);
		return "pages/cerrarCaja";
	}

	@PutMapping("/ventas/cerrarCaja")
	public String cerrarCaja(@ModelAttribute CierreCaja cierre, Producto producto, Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}

		Usuario usuario = this.usuarioService.buscarUsuarioPorNombreUsuario(userDetails.getUsername());
		this.cierreCajaService.cerrarCaja(usuario, cierre.getDiferencia(), cierre.getValorContable(),
				cierre.getValorCierre());

		return "redirect:/ventas/ventaNueva";
	}

	@GetMapping("/ventas/ventaNueva")
	public String obtenerPaginaVentas(Producto producto, CierreCaja cierre, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}

		Usuario usuario = this.usuarioService.buscarUsuarioPorNombreUsuario(userDetails.getUsername());

		if (this.cierreCajaService.buscarCierreCajaActivo(usuario)) {
			List<DetalleVenta> carrito = this.obtenerCarrito(request);
			BigDecimal total = this.ventaService.calcularValorAPagar(carrito);
			model.addAttribute("total", total);
			model.addAttribute("producto", producto);

			List<Producto> listaProductos = this.productoService.buscarTodosProductos();

			List<SubProducto> spl = this.subProductoService.buscarTodosSubProducto();

			for (SubProducto i : spl) {
				listaProductos.add(new Producto(i.getId(), i.getCodigoBarras(), i.getNombre(), i.getDescripcion(),
						i.getCostoPromedio(), i.getPrecioVenta(), i.getStockActual(), i.getProveedor(), i.getImpuesto(),
						i.getSeccion(), i.getMarca()));

			}
			model.addAttribute("listaProductos", listaProductos);

			return "pages/ventaNueva";
		} else {
			model.addAttribute("cierre", cierre);
			return "pages/abrirCaja";
		}

	}

	@GetMapping("/ventas/abrirCaja")
	public String abrirCaja(@ModelAttribute CierreCaja cierre, Producto producto, Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}

		Usuario usuario = this.usuarioService.buscarUsuarioPorNombreUsuario(userDetails.getUsername());
		this.cierreCajaService.abrirCaja(usuario, cierre.getValorApertura());

		return "redirect:/ventas/ventaNueva";
	}

	@GetMapping("/ventas/buscarProducto")
	public String buscarProducto(@ModelAttribute Producto producto, Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}

		List<Producto> listaProductos = this.productoService.buscarProductoPorNombre(producto.getNombre());

		List<SubProducto> subl = this.subProductoService.buscarSubProductoPorNombre(producto.getNombre());

		if ((listaProductos.isEmpty() || listaProductos == null) && (subl.isEmpty() || subl == null)) {
			model.addAttribute("mensaje1", "No se pudo encontrar un producto que coincida");
		} else {
			if (subl.isEmpty() || subl == null) {
				model.addAttribute("listaProductos", listaProductos);
			} else {
				for (SubProducto i : subl) {
					listaProductos.add(new Producto(i.getId(), i.getCodigoBarras(), i.getNombre(), i.getDescripcion(),
							i.getCostoPromedio(), i.getPrecioVenta(), i.getStockActual(), i.getProveedor(),
							i.getImpuesto(), i.getSeccion(), i.getMarca()));

				}
				model.addAttribute("listaProductos", listaProductos);
			}
		}
		return "pages/ventaNueva";
	}

	@PostMapping("/ventas/agregar")
	public String agregarAlCarrito(Producto producto, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		List<DetalleVenta> carrito = this.obtenerCarrito(request);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}

		Producto productoBuscadoPorCodigo = this.productoService
				.buscarProductoPorCodigoBarras(producto.getCodigoBarras());

		SubProducto i = this.subProductoService.buscarProductoPorCodigoBarras(producto.getCodigoBarras());

		if (productoBuscadoPorCodigo == null && i == null) {
			redirectAttrs.addFlashAttribute("mensaje1",
					"El producto con el código " + producto.getCodigoBarras() + " no existe");

		} else {
			if (i != null) {
				productoBuscadoPorCodigo = new Producto(i.getId(), i.getCodigoBarras(), i.getNombre(),
						i.getDescripcion(), i.getCostoPromedio(), i.getPrecioVenta(), i.getStockActual(),
						i.getProveedor(), i.getImpuesto(), i.getSeccion(), i.getMarca());
			}

			boolean encontrado = false;
			for (DetalleVenta det : carrito) {
				if (det.getProducto().getCodigoBarras().equals(productoBuscadoPorCodigo.getCodigoBarras())) {
					det.setCantidad(det.getCantidad() + 1);
					det.setTotal(det.getProducto().getPrecioVenta().multiply(new BigDecimal(det.getCantidad())));
					encontrado = true;
					break;
				}
			}

			if (!encontrado) {
				carrito.add(new DetalleVenta(1,
						this.detalleVentaService.calcularValor(1, productoBuscadoPorCodigo.getPrecioVenta()),
						productoBuscadoPorCodigo));
			}

			if (productoBuscadoPorCodigo.getStockActual() <= 0) {
				redirectAttrs.addFlashAttribute("mensaje1", "El producto está agotado");
			}
		}

		this.guardarCarrito(carrito, request);
		return "redirect:/ventas/ventaNueva";
	}

	@PostMapping("/ventas/agregar2/{codBarras}")
	public String agregarAlCarrito2(@PathVariable("codBarras") String codBarras, Producto producto,
			HttpServletRequest request, RedirectAttributes redirectAttrs, Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}
		List<DetalleVenta> carrito = this.obtenerCarrito(request);

		Producto productoBuscadoPorCodigo = this.productoService.buscarProductoPorCodigoBarras(codBarras);

		if (productoBuscadoPorCodigo == null) {
			SubProducto i = this.subProductoService.buscarProductoPorCodigoBarras(codBarras);

			if (i != null) {
				productoBuscadoPorCodigo = new Producto(i.getId(), i.getCodigoBarras(), i.getNombre(),
						i.getDescripcion(), i.getCostoPromedio(), i.getPrecioVenta(), i.getStockActual(),
						i.getProveedor(), i.getImpuesto(), i.getSeccion(), i.getMarca());

				if (productoBuscadoPorCodigo.getStockActual() <= 0) {
					redirectAttrs.addFlashAttribute("mensaje1", "El producto está agotado");

				}
			} else {
				redirectAttrs.addFlashAttribute("mensaje1",
						"El producto con el código " + producto.getCodigoBarras() + " no existe");
			}

		}

		boolean encontrado = false;
		for (DetalleVenta det : carrito) {
			if (det.getProducto().getCodigoBarras().equals(productoBuscadoPorCodigo.getCodigoBarras())) {
				det.setCantidad(det.getCantidad() + 1);
				det.setTotal(det.getProducto().getPrecioVenta().multiply(new BigDecimal(det.getCantidad())));
				encontrado = true;
				break;
			}
		}

		if (!encontrado) {
			carrito.add(new DetalleVenta(1,
					this.detalleVentaService.calcularValor(1, productoBuscadoPorCodigo.getPrecioVenta()),
					productoBuscadoPorCodigo));
		}

		this.guardarCarrito(carrito, request);
		BigDecimal total = this.ventaService.calcularValorAPagar(carrito);
		redirectAttrs.addFlashAttribute("total", total);
		return "redirect:/ventas/ventaNueva";
	}

	@PutMapping("ventas/cantidad")
	public String establecerCantidad(HttpServletRequest request, RedirectAttributes redirectAttrs,
			@ModelAttribute Producto producto, Model model) {
		List<DetalleVenta> carrito = this.obtenerCarrito(request);

		System.out.println("p" + producto.getCodigoBarras());
		for (DetalleVenta det : carrito) {
			if (det.getProducto().equals(producto)) {
				det.setTotal(det.getProducto().getPrecioVenta().multiply(new BigDecimal(det.getCantidad())));
				break;
			}
		}

		BigDecimal total = this.ventaService.calcularValorAPagar(carrito);
		model.addAttribute("total", total);
		this.guardarCarrito(carrito, request);

		return "pages/ventaNueva";
	}

	@GetMapping("/ventas/cobrar")
	public String pantallaVenta(HttpServletRequest request, RedirectAttributes redirectAttrs, Producto producto,
			DescuentoTO descuento, Model model) {
		List<DetalleVenta> carrito = this.obtenerCarrito(request);

		BigDecimal total = this.ventaService.calcularValorAPagar(carrito);

		model.addAttribute("total", total);
		model.addAttribute("descuento", descuento);
		return "pages/cobrar";

	}

	@PostMapping("/ventas/realizarVenta")
	public String terminarVenta(HttpServletRequest request, RedirectAttributes redirectAttrs, Producto producto,
			DescuentoTO descuento) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}

		List<DetalleVenta> carrito = this.obtenerCarrito(request);
		if (carrito == null || carrito.size() <= 0) {
			return "pages/ventaNueva";
		}
		
		System.out.println(descuento.getTipoDesceunto());
		System.out.println(descuento.getValorDesceunto());

		this.ventaService.realizarVenta(carrito, descuento);
		this.limpiarCarrito(request);

		redirectAttrs.addFlashAttribute("mensaje1", "Venta realizada correctamente");
		return "redirect:/ventas/ventaNueva";

	}

	@DeleteMapping("/ventas/borrar/{indice}")
	public String quitarDelCarrito(@PathVariable int indice, Producto producto, HttpServletRequest request, Model model,
			RedirectAttributes redirectAttrs) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}
		List<DetalleVenta> carrito = this.obtenerCarrito(request);
		if (carrito != null && carrito.size() > 0 && carrito.get(indice) != null) {
			carrito.remove(indice);
			this.guardarCarrito(carrito, request);
		}

		BigDecimal total = this.ventaService.calcularValorAPagar(carrito);
		redirectAttrs.addFlashAttribute("total", total);
		return "redirect:/ventas/ventaNueva";
	}

	@GetMapping("/ventas/limpiar")
	public String cancelarVenta(HttpServletRequest request, RedirectAttributes redirectAttrs, Producto producto) {
		this.limpiarCarrito(request);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}
		redirectAttrs.addFlashAttribute("mensaje1", "Venta cancelada");
		return "redirect:/ventas/ventaNueva";
	}

//Metodos de apoyo
	private List<DetalleVenta> obtenerCarrito(HttpServletRequest request) {
		List<DetalleVenta> carrito = (List<DetalleVenta>) request.getSession().getAttribute("carrito");
		if (carrito == null) {
			carrito = new ArrayList<>();
		}
		return carrito;
	}

	private DescuentoTO obtenerDescuento(HttpServletRequest request) {
		DescuentoTO descuento = (DescuentoTO) request.getSession().getAttribute("descuento");
		return descuento;
	}

	private void guardarCarrito(List<DetalleVenta> carrito, HttpServletRequest request) {
		request.getSession().setAttribute("carrito", carrito);
	}

	private void limpiarCarrito(HttpServletRequest request) {
		this.guardarCarrito(new ArrayList<>(), request);
	}

}
