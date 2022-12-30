package ec.edu.uce.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ec.edu.uce.modelo.DetalleCompra;

@Repository
@Transactional
public class DetalleCompraRepoImpl implements IDetalleCompraRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertarDetalleCompra(DetalleCompra detalleCompra) {
		this.entityManager.persist(detalleCompra);
	}

	@Override
	public DetalleCompra buscarDetalleCompra(Integer id) {
		return this.entityManager.find(DetalleCompra.class, id);
	}

	@Override
	public List<DetalleCompra> buscarTodosDetalleCompra() {
		TypedQuery<DetalleCompra> myQuery = this.entityManager.createQuery("SELECT d FROM DetalleCompra d",
				DetalleCompra.class);
		return myQuery.getResultList();
	}

	@Override
	public void actualizarDetalleCompra(DetalleCompra detalleCompra) {
		this.entityManager.merge(detalleCompra);
	}

	@Override
	public void eliminarDetalleCompra(Integer id) {
		DetalleCompra detalleCompra = this.buscarDetalleCompra(id);
		this.entityManager.refresh(detalleCompra);
	}

}
