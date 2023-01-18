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

import ec.edu.uce.modelo.Caja;
import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.Marca;
import ec.edu.uce.repository.IMarcaRepo;
import ec.edu.uce.service.ICajaService;
import ec.edu.uce.service.IClienteService;
import ec.edu.uce.service.IMarcaService;

@Controller
@RequestMapping("/negocio")
public class NegocioController {

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private ICajaService cajaService;

	@Autowired
	private IMarcaService marcaService;

	@GetMapping("/")
	public String obtenerMenuNegocio(Model model, Caja caja, Marca marca) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}

		model.addAttribute("caja", caja);
		model.addAttribute("marca", marca);
		model.addAttribute("nombreUser", userDetails.getUsername());
		return "pages/negocio";
	}

	@PostMapping("/agregarCaja")
	public String clienteNuevo(Caja caja, Marca marca, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		Caja c = this.cajaService.buscarCajaPorNombre(caja.getNombre());

		if (c == null) {
			this.cajaService.insertarCaja(caja);
			model.addAttribute("mensaje1", "CAJA REGISTRADA CON ÉXITO");

		} else {
			model.addAttribute("mensaje1", "Caja ya registrada");
		}

		model.addAttribute("caja", caja);
		model.addAttribute("marca", marca);

		return "pages/negocio";
	}

	@PostMapping("/agregarMarca")
	public String clienteNuevo(Marca marca, Caja caja, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		Marca m = this.marcaService.buscarMarcaPorNombre(marca.getNombre());
		

		if (m == null) {
			this.marcaService.insertarMarca(marca);
			model.addAttribute("mensaje2", "MARCA REGISTRADA CON ÉXITO");

		} else {
			model.addAttribute("mensaje2", "Marca ya registrada");
		}

		model.addAttribute("caja", caja);
		model.addAttribute("marca", marca);

		return "pages/negocio";
	}

}
