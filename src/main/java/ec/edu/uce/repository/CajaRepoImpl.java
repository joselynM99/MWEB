package ec.edu.uce.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ec.edu.uce.modelo.Caja;
import ec.edu.uce.modelo.Compra;

@Transactional
@Repository
public class CajaRepoImpl implements ICajaRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertarCaja(Caja caja) {
		this.entityManager.persist(caja);

	}

	@Override
	public Caja buscarCaja(Integer id) {
		return this.entityManager.find(Caja.class, id);
	}

	@Override
	public List<Caja> buscarTodosCaja() {
		TypedQuery<Caja> myQuery = this.entityManager.createQuery("SELECT c FROM Caja c", Caja.class);
		return myQuery.getResultList();
	}
	
	@Override
	public Caja buscarCajaPorNombre(String nombreCaja) {
		TypedQuery<Caja> myQuery = this.entityManager.createQuery("SELECT c FROM Caja c WHERE c.nombre=:nombreCaja", Caja.class);
		
		myQuery.setParameter("nombreCaja", nombreCaja);
		return myQuery.getSingleResult();
	}

	@Override
	public void actualizarCaja(Caja caja) {
		this.entityManager.merge(caja);
	}

	@Override
	public void eliminarCaja(Integer id) {
		Caja c = this.buscarCaja(id);
		this.entityManager.remove(c);
	}

}
