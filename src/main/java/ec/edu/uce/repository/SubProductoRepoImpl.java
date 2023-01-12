package ec.edu.uce.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ec.edu.uce.modelo.Proveedor;
import ec.edu.uce.modelo.SubProducto;

@Transactional
@Repository
public class SubProductoRepoImpl implements ISubProductoRepo{

	@PersistenceContext
	private EntityManager entityManager;

	
	@Override
	public void insertarSubProducto(SubProducto subProducto) {
		this.entityManager.persist(subProducto);
	}

	@Override
	public SubProducto buscarSubProducto(Integer id) {
		return this.entityManager.find(SubProducto.class, id);
	}

	@Override
	public List<SubProducto> buscarTodosSubProducto() {
		TypedQuery<SubProducto> myQuery = this.entityManager.createQuery("SELECT s FROM SubProducto s", SubProducto.class);
		return myQuery.getResultList();
	}

	@Override
	public void actualizarSubProducto(SubProducto subProducto) {
		this.entityManager.merge(subProducto);
	}

	@Override
	public void eliminarSubProducto(Integer id) {
		SubProducto sp = this.buscarSubProducto(id);
		this.entityManager.remove(sp);
	}
	
	
	@Override
	public SubProducto buscarProductoPorCodigoBarras(String codigoBarras) {
		TypedQuery<SubProducto> myQuery = this.entityManager
				.createQuery("SELECT p FROM SubProducto p WHERE p.codigoBarras=:codigoBarras", SubProducto.class);

		myQuery.setParameter("codigoBarras", codigoBarras);
		try {

			return myQuery.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<SubProducto> buscarSubProductoPorNombre(String nombre) {
		TypedQuery<SubProducto> myQuery = this.entityManager
				.createQuery("SELECT s FROM SubProducto s WHERE UPPER(s.nombre) LIKE UPPER(:nombre)", SubProducto.class);

		myQuery.setParameter("nombre", nombre);
		try {

			return myQuery.getResultList();

		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public List<SubProducto> buscarSubProductoPorNombreProv(String nombre,  Proveedor proveedor) {
		TypedQuery<SubProducto> myQuery = this.entityManager
				.createQuery("SELECT p FROM SubProducto p WHERE UPPER(p.nombre) LIKE UPPER(:nombre) AND p.proveedor=:proveedor", SubProducto.class);

		myQuery.setParameter("nombre", nombre);
		
		myQuery.setParameter("proveedor", proveedor);
		try {

			return myQuery.getResultList();

		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<SubProducto> buscarSubProductoPorCategoria(String categoria) {
		TypedQuery<SubProducto> myQuery = this.entityManager
				.createQuery("SELECT p FROM SubProducto p WHERE p.categoria LIKE :categoria", SubProducto.class);

		myQuery.setParameter("categoria", categoria);
		try {

			return myQuery.getResultList();

		} catch (NoResultException e) {
			return null;
		}
	}

}
