package ec.edu.uce.repository;

import java.util.List;

import ec.edu.uce.modelo.Perfil;

public interface IPerfilRepo {
	
	void insertarPerfil(Perfil perfil);

	Perfil buscarPerfilCaja(Integer id);

	List<Perfil> buscarTodosPerfil();

	void actualizarPerfil(Perfil perfil);

	void eliminarPerfil(Integer id);
	
	Perfil buscarPerfilNombre(String nombre);

}
