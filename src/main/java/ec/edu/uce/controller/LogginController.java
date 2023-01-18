package ec.edu.uce.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.uce.controller.dto.UsuarioRegistroDTO;
import ec.edu.uce.modelo.Usuario;
import ec.edu.uce.service.IUsuarioService;
import ec.edu.uce.service.UsuarioServicio;

@Controller
public class LogginController {

	@Autowired
	private UsuarioServicio servicio;

	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping("/login")
	public String iniciarSesion() {
		return "pages/login";
	}

	@GetMapping("/")
	public String verPaginaDeInicio(Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}

		model.addAttribute("nombreUser", userDetails.getUsername());

		return "index";
	}
	
	@GetMapping("/accesoRestringido")
	public String verPaginaDeAccesoRestringido(Model model) {



		return "pages/accesoRestringido";
	}

	@GetMapping("/gestionUsuarios")
	public String menuGestionUsuarios(Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		} else {
			return "pages/login";
		}

		model.addAttribute("nombreUser", userDetails.getUsername());

		return "pages/menuUsuarios";

	}

	@DeleteMapping("/gestionUsuarios/eliminar/{id}")
	public String eliminarUsuario(@PathVariable Integer id, Model model, Usuario usuario, RedirectAttributes redirectAttributes) {
		
		this.usuarioService.eliminarUsuario(id);
		redirectAttributes.addFlashAttribute("mensaje", "Producto eliminado");
		
		return "redirect:/gestionUsuarios";
	}

}
