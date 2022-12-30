package ec.edu.uce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.Perfil;
import ec.edu.uce.repository.IPerfilRepo;

@Service
public class PerfilServiceImpl implements IPerfilService {

	@Autowired
	private IPerfilRepo perfilRepo;

	@Override
	public void insertarPerfil(Perfil perfil) {
		this.perfilRepo.insertarPerfil(perfil);
	}

	@Override
	public Perfil buscarPerfilCaja(Integer id) {
		return this.perfilRepo.buscarPerfilCaja(id);
	}

	@Override
	public List<Perfil> buscarTodosPerfil() {
		return this.perfilRepo.buscarTodosPerfil();
	}

	@Override
	public void actualizarPerfil(Perfil perfil) {
		this.perfilRepo.actualizarPerfil(perfil);
	}

	@Override
	public void eliminarPerfil(Integer id) {
		this.perfilRepo.eliminarPerfil(id);
	}
	
	@Override
	public Perfil buscarPerfilNombre(String nombre) {
		return this.perfilRepo.buscarPerfilNombre(nombre);
	}

}
