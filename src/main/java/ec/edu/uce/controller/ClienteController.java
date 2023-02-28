package ec.edu.uce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.Proveedor;
import ec.edu.uce.service.IClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

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
		return "pages/clientes/menuClientes";
	}

	@GetMapping("/registroCliente")
	public String obtenerRegistroClientes(Model model, Cliente cliente) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}
		model.addAttribute("nombreUser", userDetails.getUsername());

		return "pages/clientes/clienteNuevo";
	}

	@PostMapping("/agregarCliente")
	public String clienteNuevo(Cliente cliente, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		this.clienteService.insertarCliente(cliente);

		return "redirect:/clientes/registroCliente";
	}
	
	@GetMapping("/actualizarCliente")
	public String obtenerPaginaActualizarProveedor(Cliente cliente, Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}

		model.addAttribute("nombreUser", userDetails.getUsername());

		return "pages/clientes/clienteActualizar";

	}

	@GetMapping("/buscarCliente")
	public String obtenerProveedorPorNombre2(Cliente cliente, Model modelo, RedirectAttributes redirectAttributes) {

		Cliente p = this.clienteService.buscarClienteIdentificacion(cliente.getIdentificacion());
		if (p == null || p.getId().equals(null)) {
			redirectAttributes.addFlashAttribute("error", "Cliente no encontrado");
			return "redirect:/clientes/actualizarCliente";
		} else {
			modelo.addAttribute("cliente", p);

			return "pages/clientes/clienteActualizar";
		}

	}

	@PutMapping("/actualizarClie")
	public String actualizarCliente(Cliente cliente, Model modelo, RedirectAttributes redirectAttributes) {

		cliente.setId(cliente.getId());

		this.clienteService.actualizarCliente(cliente);
		redirectAttributes.addFlashAttribute("mensaje", "Cliente actualizado");
		return "redirect:/clientes/actualizarCliente";
	}

	@DeleteMapping("/eliminarCliente/{idCliente}")
	public String eliminarCliente(@PathVariable Integer idCliente, Cliente cliente, Model modelo,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("mensaje", "Cliente eliminado");
		this.clienteService.eliminarCliente(idCliente);
		return "redirect:/clientes/actualizarCliente";
	}

	@GetMapping("/listaClientes")
	public String obtenerPaginaReporteClientes(Cliente cliente, RedirectAttributes redirectAttributes,
			Model model) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}
		model.addAttribute("nombreUser", userDetails.getUsername());

		List<Cliente> clientes = this.clienteService.buscarTodosCliente();

		model.addAttribute("clientes", clientes);

		return "pages/clientes/listaClientes";

	}

	@DeleteMapping("/borrarCliente/{indice}")
	public String borrarCliente(@PathVariable Integer indice, Cliente cliente, HttpServletRequest request,
			Model model, RedirectAttributes redirectAttributes) {
		this.clienteService.eliminarCliente(indice);

		List<Cliente> clientes = this.clienteService.buscarTodosCliente();

		redirectAttributes.addFlashAttribute("clientes", clientes);
		redirectAttributes.addFlashAttribute("cliente", cliente);

		return "redirect:/clientes/listaClientes";
	}

	@GetMapping("/actualizacionCliente/{indice}")
	public String obtenerProveedorPorNombre(@PathVariable Integer indice, Cliente cliente, Model modelo,
			RedirectAttributes redirectAttributes) {

		Cliente p = this.clienteService.buscarCliente(indice);
		if (p == null || p.getId().equals(null)) {
			redirectAttributes.addFlashAttribute("error", "Cliente no encontrado");
			return "redirect:/clientes/actualizarCliente";
		} else {
			redirectAttributes.addFlashAttribute("cliente", p);
			return "redirect:/clientes/actualizarCliente";
		}

	}

}
