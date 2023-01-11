package ec.edu.uce.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import ec.edu.uce.controller.dto.UsuarioRegistroDTO;
import ec.edu.uce.modelo.Usuario;


public interface UsuarioServicio extends UserDetailsService{

	public Usuario guardar(UsuarioRegistroDTO registroDTO);
	
	public List<Usuario> listarUsuarios();

	

	void eliminar(UsuarioRegistroDTO registroDTO);
	
}
