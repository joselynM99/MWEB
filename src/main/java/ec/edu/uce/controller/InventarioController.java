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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.uce.controller.dto.DescuentoTO;
import ec.edu.uce.modelo.CierreCaja;
import ec.edu.uce.modelo.DetalleVenta;
import ec.edu.uce.modelo.Producto;
import ec.edu.uce.modelo.SubProducto;
import ec.edu.uce.modelo.Usuario;
import ec.edu.uce.service.ICierreCajaService;
import ec.edu.uce.service.IDetalleVentaService;
import ec.edu.uce.service.IImpuestoService;
import ec.edu.uce.service.IProductoService;
import ec.edu.uce.service.IProveedorService;
import ec.edu.uce.service.ISubProductoService;
import ec.edu.uce.service.IUsuarioService;
import ec.edu.uce.service.IVentaService;

@Controller
@RequestMapping("/inventario")
public class InventarioController {

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
	private IImpuestoService impuestoService;

	@GetMapping("/menu")
	public String obtenerMenuIventario() {
		return "pages/inventario";
	}

	@GetMapping("/productoNuevo")
	public String productoNuevo(Producto producto, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}
		model.addAttribute("listImpuestos", this.impuestoService.buscarTodosImpuesto());
		return "pages/productoNuevo";

	}

	@PostMapping("agregarProducto")
	public String agregarProducto(Producto producto, BindingResult result, Model modelo,
			RedirectAttributes redirectAttributes) {
		System.out.println(producto.getImpuesto());
		this.productoService.insertarProducto(producto);
		redirectAttributes.addFlashAttribute("mensaje", "Producto guardado");
		return "redirect:/inventario/productoNuevo";
	}
}
