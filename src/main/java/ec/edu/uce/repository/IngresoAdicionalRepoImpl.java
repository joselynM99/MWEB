package ec.edu.uce.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ec.edu.uce.modelo.Compra;
import ec.edu.uce.modelo.IngresoAdicional;

@Transactional
@Repository
public class IngresoAdicionalRepoImpl implements IIngresoAdicionalRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertarIngresoAdicional(IngresoAdicional ingresoAdicional) {
		this.entityManager.persist(ingresoAdicional);
	}

	@Override
	public IngresoAdicional buscarIngresoAdicional(Integer id) {
		return this.entityManager.find(IngresoAdicional.class, id);
	}

	@Override
	public List<IngresoAdicional> buscarTodosIngresoAdicional() {
		TypedQuery<IngresoAdicional> myQuery = this.entityManager.createQuery("SELECT i FROM IngresoAdicional i",
				IngresoAdicional.class);
		return myQuery.getResultList();
	}

	@Override
	public void actualizarIngresoAdicional(IngresoAdicional ingresoAdicional) {
		this.entityManager.merge(ingresoAdicional);
	}

	@Override
	public void eliminarIngresoAdicional(Integer id) {
		IngresoAdicional ia = this.buscarIngresoAdicional(id);
		this.entityManager.remove(ia);
	}

}
