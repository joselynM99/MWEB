package ec.edu.uce.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ec.edu.uce.modelo.DetalleVenta;
import ec.edu.uce.modelo.Producto;
import ec.edu.uce.modelo.SubProducto;

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

	@Override
	public List<DetalleVenta> buscarDetalleVentaProductoFecha(Producto producto, SubProducto subProducto, LocalDateTime fechaInicio,
			LocalDateTime fechaFin) {
		TypedQuery<DetalleVenta> myQuery = this.entityManager
				.createQuery(
						"SELECT d FROM DetalleVenta d JOIN FETCH d.venta v WHERE "
								+ "v.fecha>=:fechaInicio AND v.fecha<=:fechaFin AND (d.producto=:producto OR d.subProducto=:subProducto)",
						DetalleVenta.class);
		
		
		myQuery.setParameter("producto", producto);
		myQuery.setParameter("subProducto", subProducto);
		myQuery.setParameter("fechaInicio", fechaInicio);
		myQuery.setParameter("fechaFin", fechaFin);

		return myQuery.getResultList();
	}

	@Override
	public List<DetalleVenta> buscarDetalleVentaFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		TypedQuery<DetalleVenta> myQuery = this.entityManager
				.createQuery("SELECT d FROM DetalleVenta d JOIN FETCH d.venta v WHERE "
						+ "v.fecha>=:fechaInicio AND v.fecha<=:fechaFin", DetalleVenta.class);

		myQuery.setParameter("fechaInicio", fechaInicio);
		myQuery.setParameter("fechaFin", fechaFin);

		List<DetalleVenta> list = myQuery.getResultList();
		System.out.println("buscarDetalleVentaFecha");
		
		return list;
	}

}
