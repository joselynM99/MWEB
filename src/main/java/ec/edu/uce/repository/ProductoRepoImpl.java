package ec.edu.uce.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ec.edu.uce.controller.dto.ProductoDTO;
import ec.edu.uce.modelo.Producto;
import ec.edu.uce.modelo.Proveedor;

@Repository
@Transactional
public class ProductoRepoImpl implements IProductoRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertarProducto(Producto producto) {
		this.entityManager.persist(producto);
	}

	@Override
	public Producto buscarProducto(Integer id) {
		return this.entityManager.find(Producto.class, id);
	}

	@Override
	public void actualizarProducto(Producto producto) {
		System.out.println("-----actualizando repo");
		this.entityManager.merge(producto);
		System.out.println("-----actualizando repo fin");
	}

	@Override
	public void eliminarProducto(Integer id) {
		Producto prodAEliminar = this.buscarProducto(id);
		this.entityManager.remove(prodAEliminar);
	}

	@Override
	public List<Producto> buscarTodosProductos() {
		TypedQuery<Producto> myQuery = this.entityManager.createQuery("SELECT p FROM Producto p", Producto.class);

		return myQuery.getResultList();
	}

	@Override
	public Producto buscarProductoPorCodigoBarras(String codigoBarras) {
		TypedQuery<Producto> myQuery = this.entityManager
				.createQuery("SELECT p FROM Producto p WHERE p.codigoBarras=:codigoBarras", Producto.class);

		myQuery.setParameter("codigoBarras", codigoBarras);
		try {

			return myQuery.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Producto buscarProductoPorCodigoBarrasProv(String codigoBarras, Proveedor prov) {
		TypedQuery<Producto> myQuery = this.entityManager.createQuery(
				"SELECT p FROM Producto p WHERE p.codigoBarras=:codigoBarras AND p.proveedor=:prov", Producto.class);

		myQuery.setParameter("prov", prov);
		myQuery.setParameter("codigoBarras", codigoBarras);
		try {

			return myQuery.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Producto> buscarProductoPorNombre(String nombre) {
		TypedQuery<Producto> myQuery = this.entityManager
				.createQuery("SELECT p FROM Producto p WHERE UPPER(p.nombre) LIKE UPPER(:nombre)", Producto.class);

		myQuery.setParameter("nombre", nombre);
		try {

			return myQuery.getResultList();

		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Producto> buscarProductoPorNombreProv(String nombre, Proveedor proveedor) {
		TypedQuery<Producto> myQuery = this.entityManager.createQuery(
				"SELECT p FROM Producto p WHERE UPPER(p.nombre) LIKE UPPER(:nombre) AND p.proveedor=:proveedor",
				Producto.class);

		myQuery.setParameter("nombre", nombre);

		myQuery.setParameter("proveedor", proveedor);
		try {

			return myQuery.getResultList();

		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Producto> buscarProductoPorCategoria(String categoria) {
		TypedQuery<Producto> myQuery = this.entityManager
				.createQuery("SELECT p FROM Producto p WHERE p.categoria LIKE :categoria", Producto.class);

		myQuery.setParameter("categoria", categoria);
		try {

			return myQuery.getResultList();

		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<ProductoDTO> buscarTodosProductosDTO() {
		TypedQuery<ProductoDTO> myQuery = this.entityManager.createQuery(
				"SELECT new  ec.edu.uce.controller.dto.ProductoDTO(p.id, p.codigoBarras, p.nombre, p.descripcion, p.costoPromedio, p.precioVenta, p.stockActual) from Producto p",
				ProductoDTO.class);

		return myQuery.getResultList();
	}

}
