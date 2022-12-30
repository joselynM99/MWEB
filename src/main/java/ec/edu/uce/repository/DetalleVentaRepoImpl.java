package ec.edu.uce.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ec.edu.uce.modelo.DetalleCompra;
import ec.edu.uce.modelo.DetalleVenta;

@Transactional
@Repository
public class DetalleVentaRepoImpl implements IDetalleVentaRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertarDetalleVenta(DetalleVenta detalleVenta) {
		this.entityManager.persist(detalleVenta);
	}

	@Override
	public DetalleVenta buscarDetalleVenta(Integer id) {
		return this.entityManager.find(DetalleVenta.class, id);
	}

	@Override
	public List<DetalleVenta> buscarTodosDetalleVenta() {
		TypedQuery<DetalleVenta> myQuery = this.entityManager.createQuery("SELECT d FROM DetalleVenta d",
				DetalleVenta.class);
		return myQuery.getResultList();
	}

	@Override
	public void actualizarVenta(DetalleVenta detalleVenta) {
		this.entityManager.merge(detalleVenta);
	}

	@Override
	public void eliminarDetalleVenta(Integer id) {
		DetalleVenta detalleVenta = this.buscarDetalleVenta(id);
		this.entityManager.remove(detalleVenta);
	}

}
