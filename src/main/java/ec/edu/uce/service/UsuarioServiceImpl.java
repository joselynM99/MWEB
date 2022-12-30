package ec.edu.uce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.Usuario;
import ec.edu.uce.repository.IUsuarioRepo;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioRepo usuarioRepo;

	@Override
	public void insertarUsuario(Usuario usuario) {
		this.usuarioRepo.insertarUsuario(usuario);
	}

	@Override
	public Usuario buscarUsuario(Integer id) {
		return this.usuarioRepo.buscarUsuario(id);
	}

	@Override
	public List<Usuario> buscarTodosUsuario() {
		return this.usuarioRepo.buscarTodosUsuario();
	}

	@Override
	public void actualizarUsuario(Usuario usuario) {
		this.usuarioRepo.actualizarUsuario(usuario);
	}

	@Override
	public void eliminarUsuario(Integer id) {
		this.usuarioRepo.eliminarUsuario(id);
	}

	@Override
	public Usuario buscarUsuarioPorNombreUsuario(String nombreUsuario) {
		return this.usuarioRepo.buscarUsuarioPorNombreUsuario(nombreUsuario);
	}

}
