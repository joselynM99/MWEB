package ec.edu.uce.service;

import java.util.List;

import ec.edu.uce.modelo.Usuario;

public interface IUsuarioService {
	void insertarUsuario(Usuario usuario);

	Usuario buscarUsuario(Integer id);

	List<Usuario> buscarTodosUsuario();

	void actualizarUsuario(Usuario usuario);

	void eliminarUsuario(Integer id);
	
	Usuario buscarUsuarioPorNombreUsuario(String nombreUsuario);

}
