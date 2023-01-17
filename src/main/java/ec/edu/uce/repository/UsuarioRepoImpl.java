package ec.edu.uce.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ec.edu.uce.modelo.Caja;
import ec.edu.uce.modelo.Compra;
import ec.edu.uce.modelo.Usuario;

@Transactional
@Repository
public class UsuarioRepoImpl implements IUsuarioRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertarUsuario(Usuario usuario) {
		this.entityManager.persist(usuario);
	}

	@Override
	public Usuario buscarUsuario(Integer id) {
		try {

			return this.entityManager.find(Usuario.class, id);

		} catch (NoResultException e) {
			return null;
		}

	}

	@Override
	public List<Usuario> buscarTodosUsuario() {
		TypedQuery<Usuario> myQuery = this.entityManager.createQuery("SELECT u FROM Usuario u", Usuario.class);
		return myQuery.getResultList();
	}

	@Override
	public Usuario buscarUsuarioPorNombreUsuario(String nombreUsuario) {
		TypedQuery<Usuario> myQuery = this.entityManager
				.createQuery("SELECT u FROM Usuario u WHERE u.email=:nombreUsuario", Usuario.class);
		myQuery.setParameter("nombreUsuario", nombreUsuario);
		return myQuery.getSingleResult();
	}

	@Override
	public void actualizarUsuario(Usuario usuario) {
		this.entityManager.merge(usuario);
	}

	@Override
	public void eliminarUsuario(Integer id) {
		Usuario u = this.buscarUsuario(id);
		this.entityManager.remove(u);
	}

}
