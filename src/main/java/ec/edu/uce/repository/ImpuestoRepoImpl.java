package ec.edu.uce.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ec.edu.uce.modelo.Impuesto;

@Transactional
@Repository
public class ImpuestoRepoImpl implements IImpuestoRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertarImpuesto(Impuesto impuesto) {
		this.entityManager.persist(impuesto);
	}

	@Override
	public Impuesto buscarImpuesto(Integer id) {
		return this.entityManager.find(Impuesto.class, id);
	}

	@Override
	public List<Impuesto> buscarTodosImpuesto() {
		TypedQuery<Impuesto> myQuery = this.entityManager.createQuery("SELECT i FROM Impuesto i", Impuesto.class);
		return myQuery.getResultList();
	}

	@Override
	public void actualizarImpuesto(Impuesto impuesto) {
		this.entityManager.merge(impuesto);
	}

	@Override
	public void eliminarImpuesto(Integer id) {
		Impuesto i = this.buscarImpuesto(id);
		this.entityManager.remove(i);
	}

}
