package ec.edu.uce.service;

import java.util.List;

import ec.edu.uce.modelo.Perfil;

public interface IPerfilService {
	void insertarPerfil(Perfil perfil);

	Perfil buscarPerfilCaja(Integer id);

	List<Perfil> buscarTodosPerfil();

	void actualizarPerfil(Perfil perfil);

	void eliminarPerfil(Integer id);
	
	Perfil buscarPerfilNombre(String nombre);

}
