package ec.edu.uce.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ec.edu.uce.modelo.Compra;

@Transactional 
@Repository
public class CompraRepoImpl implements ICompraRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertarCompra(Compra compra) {
		this.entityManager.persist(compra);
	}

	@Override
	public Compra buscarCompra(Integer id) {
		return this.entityManager.find(Compra.class, id);
	}

	@Override
	public List<Compra> buscarTodosCompra() {

		TypedQuery<Compra> myQuery = this.entityManager.createQuery("SELECT c FROM Compra c", Compra.class);
		return myQuery.getResultList();
	}

	@Override
	public List<Compra> buscarPorFechaTO(LocalDateTime fechaInicio, LocalDateTime fechaFinal) {

		TypedQuery<Compra> myQuery = this.entityManager.createQuery(
				"SELECT c FROM Compra c WHERE c.fecha >=: fechaInicio AND c.fecha<=:fechaFinal", Compra.class);

		myQuery.setParameter("fechaInicio", fechaInicio);
		myQuery.setParameter("fechaFinal", fechaFinal);

		return myQuery.getResultList();
	}

	@Override
	public void actualizarCompra(Compra compra) {
		this.entityManager.merge(compra);
	}

	@Override
	public void eliminarCompra(Integer id) {
		Compra compra = this.buscarCompra(id);
		this.entityManager.remove(compra);
	}

}
