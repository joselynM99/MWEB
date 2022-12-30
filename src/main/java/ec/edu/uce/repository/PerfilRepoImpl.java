package ec.edu.uce.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ec.edu.uce.modelo.Compra;
import ec.edu.uce.modelo.Perfil;

@Transactional
@Repository
public class PerfilRepoImpl implements IPerfilRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertarPerfil(Perfil perfil) {
		this.entityManager.persist(perfil);
	}

	@Override
	public Perfil buscarPerfilCaja(Integer id) {
		return this.entityManager.find(Perfil.class, id);
	}

	@Override
	public List<Perfil> buscarTodosPerfil() {
		TypedQuery<Perfil> myQuery = this.entityManager.createQuery("SELECT p FROM Perfil p", Perfil.class);
		return myQuery.getResultList();
	}

	@Override
	public void actualizarPerfil(Perfil perfil) {
		this.entityManager.merge(perfil);
	}

	@Override
	public void eliminarPerfil(Integer id) {
		Perfil p = this.buscarPerfilCaja(id);
		this.entityManager.remove(p);
	}

	@Override
	public Perfil buscarPerfilNombre(String nombre) {
		TypedQuery<Perfil> myQuery = this.entityManager.createQuery("SELECT p FROM Perfil p WHERE p.nombre=:nombre", Perfil.class);
		myQuery.setParameter("nombre", nombre);
		return myQuery.getSingleResult();
	}

}
