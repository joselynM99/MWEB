package ec.edu.uce.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ec.edu.uce.modelo.Producto;
import ec.edu.uce.modelo.Proveedor;

@Repository
@Transactional
public class ProveedorRepoImpl implements IProveedorRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertarProveedor(Proveedor proveedor) {
		this.entityManager.persist(proveedor);
	}

	@Override
	public Proveedor buscarProveedor(Integer id) {
		return this.entityManager.find(Proveedor.class, id);
	}

	@Override
	public List<Proveedor> buscarTodosProveedor() {

		TypedQuery<Proveedor> myQuery = this.entityManager.createQuery("SELECT p FROM Proveedor p", Proveedor.class);
		return myQuery.getResultList();
	}

	@Override
	public Proveedor buscarProveedorIdentificacion(String identificacion) {

		TypedQuery<Proveedor> myQuery = this.entityManager
				.createQuery("SELECT p FROM Proveedor p WHERE p.identificacion=:identificacion", Proveedor.class);
		myQuery.setParameter("identificacion", identificacion);

		try {
			myQuery.setFirstResult(0);
			myQuery.setMaxResults(1);
			return myQuery.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

	}

	@Override
	public List<Proveedor> buscarProveedorPorNombre(String nombreComercial) {
		TypedQuery<Proveedor> myQuery = this.entityManager
				.createQuery("SELECT p FROM Proveedor p WHERE UPPER(p.nombreComercial) LIKE UPPER(:nombreComercial)", Proveedor.class);

		myQuery.setParameter("nombreComercial", nombreComercial);
		try {

			return myQuery.getResultList();

		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void actualizarProveedor(Proveedor proveedor) {
		this.entityManager.merge(proveedor);
	}

	@Override
	public void eliminarProveedor(Integer id) {
		Proveedor provAEliminar = this.buscarProveedor(id);
		this.entityManager.remove(provAEliminar);
	}
	

}
