package ec.edu.uce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.uce.controller.dto.ProductoDTO;
import ec.edu.uce.controller.dto.SubProductoDTO;
import ec.edu.uce.modelo.Impuesto;
import ec.edu.uce.modelo.Marca;
import ec.edu.uce.modelo.Producto;
import ec.edu.uce.modelo.Proveedor;
import ec.edu.uce.modelo.Seccion;
import ec.edu.uce.modelo.SubProducto;
import ec.edu.uce.service.IGestionInventarioService;
import ec.edu.uce.service.IImpuestoService;
import ec.edu.uce.service.IMarcaService;
import ec.edu.uce.service.IProductoService;
import ec.edu.uce.service.IProveedorService;
import ec.edu.uce.service.ISeccionService;
import ec.edu.uce.service.ISubProductoService;

@Controller
@RequestMapping("/inventario")
public class InventarioController {

	@Autowired
	private IProveedorService proveedorService;

	@Autowired
	private IImpuestoService impuestoService;

	@Autowired
	private IMarcaService marcaService;

	@Autowired
	private ISeccionService seccionService;

	@Autowired
	private IGestionInventarioService gestionInventarioService;

	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private ISubProductoService subProductoService;

	@GetMapping("/menu")
	public String obtenerMenuIventario(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}

		model.addAttribute("nombreUser", userDetails.getUsername());
		return "pages/inventario";
	}

	@GetMapping("/productoNuevo")
	public String productoNuevo(ProductoDTO productoDTO, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}
		model.addAttribute("nombreUser", userDetails.getUsername());
		model.addAttribute("listImpuestos", this.impuestoService.buscarTodosImpuesto());
		model.addAttribute("listMarcas", this.marcaService.buscarTodosMarca());
		model.addAttribute("listSeccion", this.seccionService.buscarTodosSeccion());
		model.addAttribute("listProveedores", this.proveedorService.buscarTodosProveedor());
		return "pages/productoNuevo";

	}

	@PostMapping("/agregarProducto")
	public String agregarProducto(ProductoDTO productoDTO, BindingResult result, Model modelo,
			RedirectAttributes redirectAttributes) {
		this.gestionInventarioService.agregarProducto(productoDTO);
		redirectAttributes.addFlashAttribute("mensaje1", "Producto guardado");
		return "redirect:/inventario/productoNuevo";
	}

	/***/

	@GetMapping("/listaProductos")
	public String obtenerPaginaReporteProductos(Producto producto, RedirectAttributes redirectAttributes, Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}
		model.addAttribute("nombreUser", userDetails.getUsername());
		List<Producto> productos = this.productoService.buscarTodosProductos();

		model.addAttribute("productos", productos);
		model.addAttribute("producto", producto);
		return "pages/listaProductos";

	}

	@DeleteMapping("/borrarProducto/{indice}")
	public String borrarProducto(@PathVariable int indice, Producto producto, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		this.productoService.eliminarProducto(indice);

		List<Producto> productos = this.productoService.buscarTodosProductos();

		redirectAttributes.addFlashAttribute("productos", productos);

		return "redirect:/inventario/actualizarProducto";
	}

	@GetMapping("/actualizacionProducto/{indice}")
	public String obtenerProductoPorNombre(@PathVariable Integer indice, Producto producto, Model model,
			RedirectAttributes redirectAttributes) {

		Producto p = this.productoService.buscarProducto(indice);
		if (p == null || p.getId().equals(null)) {
			redirectAttributes.addFlashAttribute("error", "Producto no encontrado");
			return "redirect:/inventario/actualizarProducto";
		} else {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			UserDetails userDetails = null;
			if (principal instanceof UserDetails) {
				userDetails = (UserDetails) principal;
			} else {
				return "pages/login";
			}
			redirectAttributes.addFlashAttribute("nombreUser", userDetails.getUsername());
			redirectAttributes.addFlashAttribute("listImpuestos", this.impuestoService.buscarTodosImpuesto());
			redirectAttributes.addFlashAttribute("listMarcas", this.marcaService.buscarTodosMarca());
			redirectAttributes.addFlashAttribute("listSeccion", this.seccionService.buscarTodosSeccion());
			redirectAttributes.addFlashAttribute("listProveedores", this.proveedorService.buscarTodosProveedor());

			redirectAttributes.addFlashAttribute("producto", p);

			return "redirect:/inventario/actualizarProducto";
		}

	}

	@GetMapping("/actualizarProducto")
	public String obtenerPaginaActualizarProducto(Producto producto, Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}
		model.addAttribute("nombreUser", userDetails.getUsername());
		model.addAttribute("listImpuestos", this.impuestoService.buscarTodosImpuesto());
		model.addAttribute("listMarcas", this.marcaService.buscarTodosMarca());
		model.addAttribute("listSeccion", this.seccionService.buscarTodosSeccion());
		model.addAttribute("listProveedores", this.proveedorService.buscarTodosProveedor());

		return "pages/productoActualizar";

	}

	@GetMapping("/busquedaProducto")
	public String obtenerProductoPorNombre(Producto producto, Model model, RedirectAttributes redirectAttributes) {

		Producto p = this.productoService.buscarProductoPorCodigoBarras(producto.getCodigoBarras());
		if (p == null || p.getId().equals(null)) {
			redirectAttributes.addFlashAttribute("error", "Producto no encontrado");
			return "redirect:/inventario/actualizarProducto";
		} else {
			model.addAttribute("producto", p);

			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			UserDetails userDetails = null;
			if (principal instanceof UserDetails) {
				userDetails = (UserDetails) principal;
			} else {
				return "pages/login";
			}

			model.addAttribute("nombreUser", userDetails.getUsername());
			model.addAttribute("listImpuestos", this.impuestoService.buscarTodosImpuesto());
			model.addAttribute("listMarcas", this.marcaService.buscarTodosMarca());
			model.addAttribute("listSeccion", this.seccionService.buscarTodosSeccion());
			model.addAttribute("listProveedores", this.proveedorService.buscarTodosProveedor());

			return "pages/productoActualizar";
		}

	}

	@PutMapping("/actualizarProd")
	public String actualizarProducto(Producto producto, Model modelo, RedirectAttributes redirectAttributes) {

		Proveedor proveedor = this.proveedorService.buscarProveedor(producto.getProveedor().getId());
		System.out.println(producto.getProveedor().getId());
		Impuesto i = this.impuestoService.buscarImpuesto(producto.getImpuesto().getId());
		System.out.println(producto.getImpuesto().getId());
		Seccion s = this.seccionService.buscarSeccion(producto.getSeccion().getId());
		System.out.println(producto.getSeccion().getId());
		
		Marca m = this.marcaService.buscarMarca(producto.getMarca().getId());
		System.out.println(producto.getMarca().getId());
		
		producto.setProveedor(proveedor);
		producto.setImpuesto(i);
		producto.setMarca(m);
		producto.setSeccion(s);
		producto.setId(producto.getId());
		this.productoService.actualizarProducto(producto);
		redirectAttributes.addFlashAttribute("mensaje", "Producto actualizado");
		return "redirect:/inventario/actualizarProducto";
	}

	@DeleteMapping("/eliminarProducto/{idProducto}")
	public String eliminarProducto(@PathVariable Integer idProducto, Producto producto, Model modelo,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("mensaje", "Producto eliminado");
		this.productoService.eliminarProducto(idProducto);
		return "redirect:/inventario/actualizarProducto";
	}
	
	//subproducto
	
	@GetMapping("/subproductoNuevo")
	public String subproductoNuevo(SubProductoDTO subproductoDTO, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}
		model.addAttribute("nombreUser", userDetails.getUsername());
		model.addAttribute("listImpuestos", this.impuestoService.buscarTodosImpuesto());
		model.addAttribute("listMarcas", this.marcaService.buscarTodosMarca());
		model.addAttribute("listSeccion", this.seccionService.buscarTodosSeccion());
		model.addAttribute("listProveedores", this.proveedorService.buscarTodosProveedor());
		model.addAttribute("listProductos", this.productoService.buscarTodosProductos());
		model.addAttribute("subproductoDTO", subproductoDTO);
		return "pages/subproductoNuevo";

	}

	@PostMapping("/agregarSubProducto")
	public String agregarSubProducto(SubProductoDTO subproductoDTO, BindingResult result, Model modelo,
			RedirectAttributes redirectAttributes) {
		this.gestionInventarioService.agregarSubProducto(subproductoDTO);
		redirectAttributes.addFlashAttribute("mensaje1", "Subproducto guardado");
		return "redirect:/inventario/subproductoNuevo";
	}


	@GetMapping("/listaSubProductos")
	public String obtenerPaginaReporteSubProductos(SubProducto subproducto, RedirectAttributes redirectAttributes, Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}
		model.addAttribute("nombreUser", userDetails.getUsername());
		List<SubProducto> subproductos = this.subProductoService.buscarTodosSubProducto();

		model.addAttribute("subproductos", subproductos);
		model.addAttribute("subproducto", subproducto);
		return "pages/listaSubProductos";

	}

	@DeleteMapping("/borrarSubProducto/{indice}")
	public String borrarSubProducto(@PathVariable int indice, SubProducto subproducto, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		this.subProductoService.eliminarSubProducto(indice);

		List<SubProducto> subproductos = this.subProductoService.buscarTodosSubProducto();

		redirectAttributes.addFlashAttribute("subproductos", subproductos);

		return "redirect:/inventario/actualizarSubProducto";
	}

	@GetMapping("/actualizacionSubProducto/{indice}")
	public String obtenerSubProductoPorNombre(@PathVariable Integer indice, SubProducto subproducto, Model model,
			RedirectAttributes redirectAttributes) {

		SubProducto s = this.subProductoService.buscarSubProducto(indice);
		System.out.println(indice);
		System.out.println(s.getNombre());
		if (s == null || s.getId().equals(null)) {
			redirectAttributes.addFlashAttribute("error", "Subproducto no encontrado");
			return "redirect:/inventario/actualizarSubProducto";
		} else {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			UserDetails userDetails = null;
			if (principal instanceof UserDetails) {
				userDetails = (UserDetails) principal;
			} else {
				return "pages/login";
			}
			redirectAttributes.addFlashAttribute("nombreUser", userDetails.getUsername());
			redirectAttributes.addFlashAttribute("listImpuestos", this.impuestoService.buscarTodosImpuesto());
			redirectAttributes.addFlashAttribute("listMarcas", this.marcaService.buscarTodosMarca());
			redirectAttributes.addFlashAttribute("listSeccion", this.seccionService.buscarTodosSeccion());
			redirectAttributes.addFlashAttribute("listProveedores", this.proveedorService.buscarTodosProveedor());
			redirectAttributes.addFlashAttribute("listProductos", this.productoService.buscarTodosProductos());
			redirectAttributes.addFlashAttribute("subproducto", s);

			return "redirect:/inventario/actualizarSubProducto";
		}

	}

	@GetMapping("/actualizarSubProducto")
	public String obtenerPaginaActualizarSubProducto(@ModelAttribute SubProducto subproducto, Model model) {

		System.out.println(subproducto.getId());
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}
		model.addAttribute("nombreUser", userDetails.getUsername());
		model.addAttribute("listImpuestos", this.impuestoService.buscarTodosImpuesto());
		model.addAttribute("listMarcas", this.marcaService.buscarTodosMarca());
		model.addAttribute("listSeccion", this.seccionService.buscarTodosSeccion());
		model.addAttribute("listProveedores", this.proveedorService.buscarTodosProveedor());
		model.addAttribute("listProductos", this.productoService.buscarTodosProductos());
		model.addAttribute("subproducto",subproducto);
		

		return "pages/subproductoActualizar";

	}

	@GetMapping("/busquedaSubProducto")
	public String obtenerSubProductoPorNombre(SubProducto subproducto, Model model, RedirectAttributes redirectAttributes) {

		SubProducto p = this.subProductoService.buscarProductoPorCodigoBarras(subproducto.getCodigoBarras());
		if (p == null || p.getId().equals(null)) {
			redirectAttributes.addFlashAttribute("error", "Producto no encontrado");
			return "redirect:/inventario/actualizarProducto";
		} else {
			model.addAttribute("subproducto", p);

			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			UserDetails userDetails = null;
			if (principal instanceof UserDetails) {
				userDetails = (UserDetails) principal;
			} else {
				return "pages/login";
			}

			model.addAttribute("nombreUser", userDetails.getUsername());
			model.addAttribute("listImpuestos", this.impuestoService.buscarTodosImpuesto());
			model.addAttribute("listMarcas", this.marcaService.buscarTodosMarca());
			model.addAttribute("listSeccion", this.seccionService.buscarTodosSeccion());
			model.addAttribute("listProveedores", this.proveedorService.buscarTodosProveedor());
			model.addAttribute("listProductos", this.productoService.buscarTodosProductos());

			return "pages/subproductoActualizar";
		}

	}

	@PutMapping("/actualizarSubProd")
	public String actualizarSubProducto(SubProducto subproducto, Model modelo, RedirectAttributes redirectAttributes) {

		Proveedor proveedor = this.proveedorService.buscarProveedor(subproducto.getProveedor().getId());		
		Impuesto i = this.impuestoService.buscarImpuesto(subproducto.getImpuesto().getId());		
		Seccion s = this.seccionService.buscarSeccion(subproducto.getSeccion().getId());		
		Marca m = this.marcaService.buscarMarca(subproducto.getMarca().getId());
		Producto p = this.productoService.buscarProducto(subproducto.getProducto().getId());
		
		subproducto.setProveedor(proveedor);
		subproducto.setImpuesto(i);
		subproducto.setMarca(m);
		subproducto.setSeccion(s);
		subproducto.setProducto(p);
		subproducto.setId(subproducto.getId());
		this.subProductoService.actualizarSubProducto(subproducto);
		redirectAttributes.addFlashAttribute("mensaje", "Subproducto actualizado");
		return "redirect:/inventario/actualizarSubProducto";
	}

	@DeleteMapping("/eliminarSubProducto/{idSubProducto}")
	public String eliminarSubProducto(@PathVariable Integer idSubProducto, SubProducto subproducto, Model modelo,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("mensaje", "Producto eliminado");
		this.subProductoService.eliminarSubProducto(idSubProducto);
		return "redirect:/inventario/actualizarSubProducto";
	}

	@GetMapping("/proveedorNuevo")
	public String proveedorNuevo(Proveedor proveedor, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}
		model.addAttribute("nombreUser", userDetails.getUsername());
		
		return "pages/proveedorNuevo";
	}

	@PostMapping("/agregarProveedor")
	public String agregarProveedor(Proveedor proveedor, BindingResult result, Model modelo,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		this.gestionInventarioService.agregarProveedor(proveedor);
		redirectAttributes.addFlashAttribute("mensaje", "Proovedor guardado");
		redirectAttributes.addFlashAttribute("clase", "success");
		return "redirect:/inventario/proveedorNuevo";
	}

	@GetMapping("/actualizarProveedor")
	public String obtenerPaginaActualizarProveedor(Proveedor proveedor, Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}

		model.addAttribute("nombreUser", userDetails.getUsername());

		return "pages/proveedorActualizar";

	}

	@GetMapping("/buscarProveedor")
	public String obtenerProveedorPorNombre2(Proveedor proveedor, Model modelo, RedirectAttributes redirectAttributes) {

		Proveedor p = this.proveedorService.buscarProveedorIdentificacion(proveedor.getIdentificacion());
		if (p == null || p.getId().equals(null)) {
			redirectAttributes.addFlashAttribute("error", "Proveedor no encontrado");
			return "redirect:/inventario/actualizarProveedor";
		} else {
			modelo.addAttribute("proveedor", p);

			return "pages/proveedorActualizar";
		}

	}

	@PutMapping("/actualizarProv")
	public String actualizarProveedor(Proveedor proveedor, Model modelo, RedirectAttributes redirectAttributes) {

		proveedor.setId(proveedor.getId());

		this.proveedorService.actualizarProveedor(proveedor);
		redirectAttributes.addFlashAttribute("mensaje", "Proveedor actualizado");
		return "redirect:/inventario/actualizarProveedor";
	}

	@DeleteMapping("/eliminarProveedor/{idProveedor}")
	public String eliminarProveedor(@PathVariable Integer idProveedor, Proveedor proveedor, Model modelo,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("mensaje", "Proveedor eliminado");
		this.proveedorService.eliminarProveedor(idProveedor);
		return "redirect:/inventario/actualizarProveedor";
	}

	@GetMapping("/listaProveedores")
	public String obtenerPaginaReporteProveedores(Proveedor proveedor, RedirectAttributes redirectAttributes,
			Model model) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}
		model.addAttribute("nombreUser", userDetails.getUsername());

		List<Proveedor> proveedores = this.proveedorService.buscarTodosProveedor();

		model.addAttribute("proveedores", proveedores);

		return "pages/listaProveedores";

	}

	@DeleteMapping("/borrarProveedor/{indice}")
	public String borrarProducto(@PathVariable Integer indice, Proveedor proveedor, HttpServletRequest request,
			Model model, RedirectAttributes redirectAttributes) {
		this.proveedorService.eliminarProveedor(indice);

		List<Proveedor> proveedores = this.proveedorService.buscarTodosProveedor();

		redirectAttributes.addFlashAttribute("proveedores", proveedores);
		redirectAttributes.addFlashAttribute("proveedor", proveedor);

		return "redirect:/inventario/listaProveedores";
	}

	@GetMapping("/actualizacionProveedor/{indice}")
	public String obtenerProveedorPorNombre(@PathVariable Integer indice, Proveedor proveedor, Model modelo,
			RedirectAttributes redirectAttributes) {

		Proveedor p = this.proveedorService.buscarProveedor(indice);
		if (p == null || p.getId().equals(null)) {
			redirectAttributes.addFlashAttribute("error", "Proveedor no encontrado");
			return "redirect:/inventario/actualizarProveedor";
		} else {
			redirectAttributes.addFlashAttribute("proveedor", p);
			return "redirect:/inventario/actualizarProveedor";
		}

	}

}
