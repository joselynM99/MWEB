package ec.edu.uce.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ec.edu.uce.modelo.Compra;
import ec.edu.uce.modelo.Seccion;

@Transactional
@Repository
public class SeccionRepoImpl implements ISeccionRepo{

	@PersistenceContext
	private EntityManager entityManager;

	
	@Override
	public void insertarCaja(Seccion seccion) {
		this.entityManager.persist(seccion);
	}

	@Override
	public Seccion buscarSeccion(Integer id) {
		return this.entityManager.find(Seccion.class, id);
	}

	@Override
	public List<Seccion> buscarTodosSeccion() {
		TypedQuery<Seccion> myQuery = this.entityManager.createQuery("SELECT s FROM Seccion s", Seccion.class);
		return myQuery.getResultList();
	}

	@Override
	public void actualizarSeccion(Seccion seccion) {
		this.entityManager.merge(seccion);
	}

	@Override
	public void eliminarSeccion(Integer id) {
		Seccion s = this.buscarSeccion(id);
		this.entityManager.remove(s);
	}

}
