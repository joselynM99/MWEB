package ec.edu.uce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ec.edu.uce.service.UsuarioServicio;

@Controller
public class Loggin {

	@Autowired
	private UsuarioServicio servicio;
	
	@GetMapping("/login")
	public String iniciarSesion() {
		return "pages/login";
	}
	
	@GetMapping("/")
	public String verPaginaDeInicio(Model modelo) {
		
		return "index";
	}
}
