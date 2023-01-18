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
import ec.edu.uce.modelo.Marca;

@Transactional
@Repository
public class MarcaRepoImpl implements IMarcaRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertarMarca(Marca marca) {
		this.entityManager.persist(marca);
	}

	@Override
	public Marca buscarMarca(Integer id) {
		return this.entityManager.find(Marca.class, id);
	}

	@Override
	public List<Marca> buscarTodosMarca() {
		TypedQuery<Marca> myQuery = this.entityManager.createQuery("SELECT m FROM Marca m", Marca.class);
		return myQuery.getResultList();
	}

	@Override
	public void actualizarMarca(Marca marca) {
		this.entityManager.merge(marca);
	}

	@Override
	public void eliminarMarca(Integer id) {
		Marca m = this.buscarMarca(id);
		this.entityManager.remove(m);
	}
	
	@Override
	public Marca buscarMarcaPorNombre(String nombreMarca) {
		TypedQuery<Marca> myQuery = this.entityManager.createQuery("SELECT c FROM Marca c WHERE c.nombre=:nombreMarca",
				Marca.class);

		myQuery.setParameter("nombreMarca", nombreMarca);

		try {

			return myQuery.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

}
