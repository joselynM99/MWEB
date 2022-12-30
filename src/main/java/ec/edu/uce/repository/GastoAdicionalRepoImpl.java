package ec.edu.uce.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ec.edu.uce.modelo.Compra;
import ec.edu.uce.modelo.GastoAdicional;

@Transactional
@Repository
public class GastoAdicionalRepoImpl implements IGastoAdicionalRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertarGastoAdicional(GastoAdicional gastoAdicional) {
		this.entityManager.persist(gastoAdicional);
	}

	@Override
	public GastoAdicional buscarGastoAdicional(Integer id) {
		return this.entityManager.find(GastoAdicional.class, id);
	}

	@Override
	public List<GastoAdicional> buscarTodosGastoAdicional() {
		TypedQuery<GastoAdicional> myQuery = this.entityManager.createQuery("SELECT g FROM GastoAdicional g",
				GastoAdicional.class);
		return myQuery.getResultList();
	}

	@Override
	public void actualizarGastoAdicional(GastoAdicional gastoAdicional) {
		this.entityManager.merge(gastoAdicional);
	}

	@Override
	public void eliminarGastoAdicional(Integer id) {
		GastoAdicional g = this.buscarGastoAdicional(id);
		this.entityManager.remove(g);
	}

}
