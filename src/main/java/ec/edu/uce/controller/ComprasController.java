package ec.edu.uce.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.uce.controller.dto.DescuentoTO;
import ec.edu.uce.modelo.Compra;
import ec.edu.uce.modelo.DetalleCompra;
import ec.edu.uce.modelo.Producto;
import ec.edu.uce.modelo.Proveedor;
import ec.edu.uce.modelo.SubProducto;
import ec.edu.uce.modelo.Usuario;
import ec.edu.uce.service.ICajaService;
import ec.edu.uce.service.ICompraService;
import ec.edu.uce.service.IDetalleCompraService;
import ec.edu.uce.service.IProductoService;
import ec.edu.uce.service.IProveedorService;
import ec.edu.uce.service.ISubProductoService;
import ec.edu.uce.service.IUsuarioService;

@Controller
public class ComprasController {

	@Autowired
	private IProductoService productoService;

	@Autowired
	private IProveedorService proveedorService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private ISubProductoService subProductoService;

	@Autowired
	private ICajaService cajaService;

	@Autowired
	private ICompraService compraService;

	@Autowired
	private IDetalleCompraService detalleCompraService;

	@GetMapping("/compras")
	public String obtenerMenuCompras(Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}

		model.addAttribute("nombreUser", userDetails.getUsername());
		return "pages/compras";
	}

	@GetMapping("/compras/compraNueva")
	public String obtenerPaginaVentas(Producto producto, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}

		Usuario usuario = this.usuarioService.buscarUsuarioPorNombreUsuario(userDetails.getUsername());

		List<DetalleCompra> carrito = this.obtenerCarrito(request);
		BigDecimal total = this.compraService.calcularValorAPagar(carrito);
		model.addAttribute("total", total);
		model.addAttribute("producto", producto);

		List<Proveedor> listaProveedores = this.proveedorService.buscarTodosProveedor();

		model.addAttribute("listaProveedores", listaProveedores);

		return "pages/compraNueva";

	}

	@GetMapping("/compras/compraNuevaSinProv")
	public String obtenerPaginaVentasSinProv(Producto producto, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}

		Usuario usuario = this.usuarioService.buscarUsuarioPorNombreUsuario(userDetails.getUsername());

		List<DetalleCompra> carrito = this.obtenerCarrito(request);
		BigDecimal total = this.compraService.calcularValorAPagar(carrito);
		model.addAttribute("total", total);
		model.addAttribute("producto", producto);

		return "pages/compraNuevaSinProv";

	}

	@GetMapping("/compras/buscarProducto")
	public String buscarProducto(@ModelAttribute Producto producto, Model model, Compra compra) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}

		Proveedor p = this.proveedorService.buscarProveedor(producto.getProveedor().getId());

		List<Producto> listaProductos = this.productoService.buscarProductoPorNombreProv(producto.getNombre(), p);

		List<SubProducto> subl = this.subProductoService.buscarSubProductoPorNombreProv(producto.getNombre(), p);

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

		List<Proveedor> listaProveedores = this.proveedorService.buscarTodosProveedor();

		model.addAttribute("listaProveedores", listaProveedores);
		return "pages/compraNueva";
	}

	@GetMapping("/compras/buscarProductoG")
	public String buscarProductoG(@ModelAttribute Producto producto, Model model, Compra compra) {

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

		return "pages/compraNuevaSinProv";
	}

	@PostMapping("/compras/agregar")
	public String agregarAlCarrito(Producto producto, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		List<DetalleCompra> carrito = this.obtenerCarrito(request);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}
		List<Proveedor> listaProveedores = this.proveedorService.buscarTodosProveedor();

		redirectAttrs.addFlashAttribute("listaProveedores", listaProveedores);
		Proveedor p = this.proveedorService.buscarProveedor(producto.getProveedor().getId());

		Producto productoBuscadoPorCodigo = this.productoService
				.buscarProductoPorCodigoBarrasProv(producto.getCodigoBarras(), p);

		SubProducto i = this.subProductoService.buscarProductoPorCodigoBarrasProv(producto.getCodigoBarras(), p);
		;

		if (productoBuscadoPorCodigo == null && i == null) {
			redirectAttrs.addFlashAttribute("mensaje1", "El producto con el código " + producto.getCodigoBarras()
					+ " no existe o no pertenece al proveedor seleccionado");

		} else {
			if (i != null) {
				productoBuscadoPorCodigo = new Producto(i.getId(), i.getCodigoBarras(), i.getNombre(),
						i.getDescripcion(), i.getCostoPromedio(), i.getPrecioVenta(), i.getStockActual(),
						i.getProveedor(), i.getImpuesto(), i.getSeccion(), i.getMarca());
			}

			boolean encontrado = false;
			for (DetalleCompra det : carrito) {
				if (det.getProducto().getCodigoBarras().equals(productoBuscadoPorCodigo.getCodigoBarras())) {
					det.setCantidad(det.getCantidad() + 1);
					det.setTotal(det.getProducto().getCostoPromedio().multiply(new BigDecimal(det.getCantidad())));
					encontrado = true;
					break;
				}
			}

			if (!encontrado) {
				carrito.add(new DetalleCompra((double) 1,
						this.detalleCompraService.calcularValorCompra((double) 1, productoBuscadoPorCodigo.getCostoPromedio()),
						productoBuscadoPorCodigo));
			}

		}

		this.guardarCarrito(carrito, request);
		return "redirect:/compras/compraNueva";
	}

	@PostMapping("/compras/agregarSP")
	public String agregarAlCarritoSP(Producto producto, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		List<DetalleCompra> carrito = this.obtenerCarrito(request);
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
			redirectAttrs.addFlashAttribute("mensaje1", "El producto con el código " + producto.getCodigoBarras()
					+ " no existe o no pertenece al proveedor seleccionado");

		} else {
			if (i != null) {
				productoBuscadoPorCodigo = new Producto(i.getId(), i.getCodigoBarras(), i.getNombre(),
						i.getDescripcion(), i.getCostoPromedio(), i.getPrecioVenta(), i.getStockActual(),
						i.getProveedor(), i.getImpuesto(), i.getSeccion(), i.getMarca());
			}

			boolean encontrado = false;
			for (DetalleCompra det : carrito) {
				if (det.getProducto().getCodigoBarras().equals(productoBuscadoPorCodigo.getCodigoBarras())) {
					det.setCantidad(det.getCantidad() + 1);
					det.setTotal(det.getProducto().getCostoPromedio().multiply(new BigDecimal(det.getCantidad())));
					encontrado = true;
					break;
				}
			}

			if (!encontrado) {
				carrito.add(new DetalleCompra((double) 1,
						this.detalleCompraService.calcularValorCompra((double) 1, productoBuscadoPorCodigo.getCostoPromedio()),
						productoBuscadoPorCodigo));
			}

		}

		this.guardarCarrito(carrito, request);
		return "redirect:/compras/compraNueva";
	}

	@PostMapping("/compras/agregar2/{codBarras}")
	public String agregarAlCarrito2(@PathVariable("codBarras") String codBarras, Producto producto,
			@RequestParam(name = "cantidad") String cantidad, HttpServletRequest request,
			RedirectAttributes redirectAttrs, Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}

		List<Proveedor> listaProveedores = this.proveedorService.buscarTodosProveedor();
		System.out.println("-----metodo agregar2");

		redirectAttrs.addFlashAttribute("listaProveedores", listaProveedores);
		List<DetalleCompra> carrito = this.obtenerCarrito(request);

		Producto productoBuscadoPorCodigo = this.productoService.buscarProductoPorCodigoBarras(codBarras);

		if (productoBuscadoPorCodigo == null) {
			SubProducto i = this.subProductoService.buscarProductoPorCodigoBarras(codBarras);

			if (i != null) {
				productoBuscadoPorCodigo = new Producto(i.getId(), i.getCodigoBarras(), i.getNombre(),
						i.getDescripcion(), i.getCostoPromedio(), i.getPrecioVenta(), i.getStockActual(),
						i.getProveedor(), i.getImpuesto(), i.getSeccion(), i.getMarca());

			} else {
				redirectAttrs.addFlashAttribute("mensaje1",
						"El producto con el código " + producto.getCodigoBarras() + " no existe");
			}

		}

		boolean encontrado = false;
		for (DetalleCompra det : carrito) {
			if (det.getProducto().getCodigoBarras().equals(productoBuscadoPorCodigo.getCodigoBarras())) {
				det.setCantidad(det.getCantidad() + 1);
				det.setTotal(det.getProducto().getPrecioVenta().multiply(new BigDecimal(det.getCantidad())));
				encontrado = true;
				break;
			}
		}

		if (!encontrado) {
			carrito.add(new DetalleCompra(
					Double.parseDouble(cantidad), this.detalleCompraService
							.calcularValorCompra(Double.parseDouble(cantidad), productoBuscadoPorCodigo.getPrecioVenta()),
					productoBuscadoPorCodigo));
		}

		this.guardarCarrito(carrito, request);
		BigDecimal total = this.compraService.calcularValorAPagar(carrito);
		redirectAttrs.addFlashAttribute("total", total);
		return "redirect:/compras/compraNueva";
	}

	@PostMapping("/compras/agregar2P/{codBarras}")
	public String agregarAlCarrito2P(@PathVariable("codBarras") String codBarras,
			@RequestParam(name = "cantidad") String cantidad, Producto producto, HttpServletRequest request,
			RedirectAttributes redirectAttrs, Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}

		List<DetalleCompra> carrito = this.obtenerCarrito(request);

		Producto productoBuscadoPorCodigo = this.productoService.buscarProductoPorCodigoBarras(codBarras);

		if (productoBuscadoPorCodigo == null) {
			SubProducto i = this.subProductoService.buscarProductoPorCodigoBarras(codBarras);

			if (i != null) {
				productoBuscadoPorCodigo = new Producto(i.getId(), i.getCodigoBarras(), i.getNombre(),
						i.getDescripcion(), i.getCostoPromedio(), i.getPrecioVenta(), i.getStockActual(),
						i.getProveedor(), i.getImpuesto(), i.getSeccion(), i.getMarca());

			} else {
				redirectAttrs.addFlashAttribute("mensaje1",
						"El producto con el código " + producto.getCodigoBarras() + " no existe");
			}

		}

		boolean encontrado = false;
		for (DetalleCompra det : carrito) {
			if (det.getProducto().getCodigoBarras().equals(productoBuscadoPorCodigo.getCodigoBarras())) {
				det.setCantidad(det.getCantidad() + 1);
				det.setTotal(det.getProducto().getPrecioVenta().multiply(new BigDecimal(det.getCantidad())));
				encontrado = true;
				break;
			}
		}

		if (!encontrado) {
			carrito.add(new DetalleCompra(Double.parseDouble(cantidad), this.detalleCompraService
					.calcularValorCompra(Double.parseDouble(cantidad), productoBuscadoPorCodigo.getPrecioVenta()),
					productoBuscadoPorCodigo));
		}

		this.guardarCarrito(carrito, request);
		BigDecimal total = this.compraService.calcularValorAPagar(carrito);
		redirectAttrs.addFlashAttribute("total", total);
		return "redirect:/compras/compraNueva";
	}

	@PostMapping("/compras/realizarCompraPedido")
	public String terminarCompra(HttpServletRequest request, RedirectAttributes redirectAttrs, Producto producto,
			Compra compra) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}

		List<DetalleCompra> carrito = this.obtenerCarrito(request);
		if (carrito == null || carrito.size() <= 0) {
			return "pages/compraNueva";
		}
		Proveedor p = carrito.get(0).getProducto().getProveedor();
		this.compraService.realizarCompra(carrito, p, LocalDateTime.now());
		this.limpiarCarrito(request);

		redirectAttrs.addFlashAttribute("mensaje1", "Compra realizada correctamente");
		return "redirect:/compras/compraNueva";

	}

	@PostMapping("/compras/realizarCompraSP")
	public String terminarCompraSP(HttpServletRequest request, RedirectAttributes redirectAttrs, Producto producto,
			Compra compra) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}

		List<DetalleCompra> carrito = this.obtenerCarrito(request);
		if (carrito == null || carrito.size() <= 0) {
			return "pages/compraNueva";
		}

		this.compraService.realizarCompra(carrito, null, LocalDateTime.now());
		this.limpiarCarrito(request);

		redirectAttrs.addFlashAttribute("mensaje1", "Compra realizada correctamente");
		return "redirect:/compras/compraNuevaSinProv";

	}

	@DeleteMapping("/compras/borrar/{indice}")
	public String quitarDelCarrito(@PathVariable int indice, Producto producto, HttpServletRequest request, Model model,
			RedirectAttributes redirectAttrs) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}
		List<DetalleCompra> carrito = this.obtenerCarrito(request);
		if (carrito != null && carrito.size() > 0 && carrito.get(indice) != null) {
			carrito.remove(indice);
			this.guardarCarrito(carrito, request);
		}

		BigDecimal total = this.compraService.calcularValorAPagar(carrito);
		redirectAttrs.addFlashAttribute("total", total);
		return "redirect:/compras/compraNueva";
	}

	@DeleteMapping("/compras/borrarSP/{indice}")
	public String quitarDelCarritoSP(@PathVariable int indice, Producto producto, HttpServletRequest request,
			Model model, RedirectAttributes redirectAttrs) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}
		List<DetalleCompra> carrito = this.obtenerCarrito(request);
		if (carrito != null && carrito.size() > 0 && carrito.get(indice) != null) {
			carrito.remove(indice);
			this.guardarCarrito(carrito, request);
		}

		BigDecimal total = this.compraService.calcularValorAPagar(carrito);
		redirectAttrs.addFlashAttribute("total", total);
		return "redirect:/compras/compraNuevaSinProv";
	}

	@GetMapping("/compras/limpiar")
	public String cancelarCompra(HttpServletRequest request, RedirectAttributes redirectAttrs, Producto producto) {
		this.limpiarCarrito(request);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}
		redirectAttrs.addFlashAttribute("mensaje1", "Compra cancelada");
		return "redirect:/compras/compraNueva";
	}

	@GetMapping("/compras/limpiarSP")
	public String cancelarCompraSP(HttpServletRequest request, RedirectAttributes redirectAttrs, Producto producto) {
		this.limpiarCarrito(request);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}
		redirectAttrs.addFlashAttribute("mensaje1", "Compra cancelada");
		return "redirect:/compras/compraNuevaSinProv";
	}

//Metodos de apoyo
	private List<DetalleCompra> obtenerCarrito(HttpServletRequest request) {
		List<DetalleCompra> carrito = (List<DetalleCompra>) request.getSession().getAttribute("carrito");
		if (carrito == null) {
			carrito = new ArrayList<>();
		}
		System.out.println("-----obtener carrito");
		return carrito;
	}

	private void guardarCarrito(List<DetalleCompra> carrito, HttpServletRequest request) {
		System.out.println("-----guardar carrito");
		request.getSession().setAttribute("carrito", carrito);
	}

	private void limpiarCarrito(HttpServletRequest request) {
		System.out.println("-----carrito");

		this.guardarCarrito(new ArrayList<>(), request);
	}

}
