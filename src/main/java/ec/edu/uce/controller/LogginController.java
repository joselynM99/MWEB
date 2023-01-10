package ec.edu.uce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@GetMapping("/gestionUsuarios/modificarUsuario")
	public String modificarUsuarioVentana(Usuario usuario, Model model) {
		return "pages/modificarUsuario";

	}

	@GetMapping("/gestionUsuarios/buscarUsuario")
	public String buscarUsuario(Usuario usuario, Model model, RedirectAttributes redirect) {

		Usuario user = this.usuarioService.buscarUsuarioPorNombreUsuario(usuario.getNombreUsuario());

		if (user == null || user.getId().equals(null)) {
			redirect.addFlashAttribute("error", "Usuario no encontrado");
			return "redirect:/gestionUsuarios/modificarUsuario";
		} else {

			model.addAttribute("usuario", user);

			return "pages/modificarUsuario";

		}
	}
	
//	@PutMapping("/gestionUsuarios/buscarUsuario")
//	public String actualizarUser(Usuario usuario, Model model, RedirectAttributes redirectAttributes) {
//		usuario.get
//		return "";
//	}
}
