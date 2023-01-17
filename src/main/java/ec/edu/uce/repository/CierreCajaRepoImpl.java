package ec.edu.uce.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ec.edu.uce.modelo.Caja;
import ec.edu.uce.modelo.CierreCaja;
import ec.edu.uce.modelo.Usuario;

@Transactional
@Repository
public class CierreCajaRepoImpl implements ICierreCajaRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertarCierreCaja(CierreCaja cierreCaja) {
		this.entityManager.persist(cierreCaja);
	}

	@Override
	public CierreCaja buscarCierreCaja(Integer id) {
		return this.entityManager.find(CierreCaja.class, id);
	}

	@Override
	public List<CierreCaja> buscarTodosCierreCaja() {
		TypedQuery<CierreCaja> myQuery = this.entityManager.createQuery("SELECT c FROM CierreCaja c", CierreCaja.class);
		return myQuery.getResultList();
	}

	@Override
	public boolean buscarCierreCajaActivo(Usuario usuario) {

		TypedQuery<CierreCaja> myQuery = this.entityManager
				.createQuery("SELECT c FROM CierreCaja c WHERE c.usuario=:usuario AND c.estado=TRUE", CierreCaja.class);
		myQuery.setParameter("usuario", usuario);

		CierreCaja c = new CierreCaja();
		try {
			c = myQuery.getSingleResult();
		} catch (NoResultException e) {
			c = null;

		}

		if (c != null) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public CierreCaja obtenerCierreCajaActivo(Usuario usuario) {
		TypedQuery<CierreCaja> myQuery = this.entityManager
				.createQuery("SELECT c FROM CierreCaja c WHERE c.usuario=:usuario AND c.estado=TRUE", CierreCaja.class);
		myQuery.setParameter("usuario", usuario);
		return myQuery.getSingleResult();

	}

	@Override
	public void actualizarCierreCaja(CierreCaja cierreCaja) {
		this.entityManager.merge(cierreCaja);
	}

	@Override
	public void eliminarCierreCaja(Integer id) {
		CierreCaja c = this.buscarCierreCaja(id);
		this.entityManager.remove(c);
	}

	@Override
	public List<CierreCaja> buscarCierreCajas(LocalDateTime fechaInicio, LocalDateTime fechaFin, Caja caja,
			Boolean estado, Usuario usuario) {

		if ((usuario == null||usuario == null) && (caja == null) && (estado == null)) {

			TypedQuery<CierreCaja> myQuery = this.entityManager.createQuery(
					"SELECT c FROM CierreCaja c WHERE c.fechaApertura>=:fechaInicio AND  c.fechaApertura<=:fechaFin",
					CierreCaja.class);

			myQuery.setParameter("fechaInicio", fechaInicio);
			myQuery.setParameter("fechaFin", fechaFin);
			return myQuery.getResultList();

		} else if ((usuario == null) && (caja == null)) {
			TypedQuery<CierreCaja> myQuery = this.entityManager.createQuery(
					"SELECT c FROM CierreCaja c WHERE c.fechaApertura>=:fechaInicio AND c.fechaApertura<=:fechaFin AND c.estado=:estado",
					CierreCaja.class);
			myQuery.setParameter("fechaInicio", fechaInicio);
			myQuery.setParameter("fechaFin", fechaFin);
			myQuery.setParameter("estado", estado);

			return myQuery.getResultList();
		} else if ((caja == null) && (estado == null)) {
			TypedQuery<CierreCaja> myQuery = this.entityManager.createQuery(
					"SELECT c FROM CierreCaja c WHERE c.fechaApertura>=:fechaInicio AND c.fechaApertura<=:fechaFin AND c.usuario=:usuario",
					CierreCaja.class);

			myQuery.setParameter("fechaInicio", fechaInicio);
			myQuery.setParameter("fechaFin", fechaFin);
			myQuery.setParameter("usuario", usuario);
			return myQuery.getResultList();

		} else if ((usuario == null) && (estado == null)) {
			TypedQuery<CierreCaja> myQuery = this.entityManager.createQuery(
					"SELECT c FROM CierreCaja c WHERE c.fechaApertura>=:fechaInicio AND c.fechaApertura<=:fechaFin AND c.caja=:caja",
					CierreCaja.class);

			myQuery.setParameter("fechaInicio", fechaInicio);
			myQuery.setParameter("fechaFin", fechaFin);
			myQuery.setParameter("caja", caja);
			return myQuery.getResultList();

		} else if (usuario == null) {
			TypedQuery<CierreCaja> myQuery = this.entityManager.createQuery(
					"SELECT c FROM CierreCaja c WHERE c.fechaApertura>=:fechaInicio AND c.fechaApertura<=:fechaFin AND c.estado=:estado AND c.usuario=:usuario",
					CierreCaja.class);
			myQuery.setParameter("fechaInicio", fechaInicio);
			myQuery.setParameter("fechaFin", fechaFin);
			myQuery.setParameter("estado", estado);
			myQuery.setParameter("usuario", usuario);
			return myQuery.getResultList();

		}else if(estado == null) {
			TypedQuery<CierreCaja> myQuery = this.entityManager.createQuery(
					"SELECT c FROM CierreCaja c WHERE c.fechaApertura>=:fechaInicio AND c.fechaApertura<=:fechaFin AND c.usuario=:usuario AND c.caja=:caja",
					CierreCaja.class);
			myQuery.setParameter("fechaInicio", fechaInicio);
			myQuery.setParameter("fechaFin", fechaFin);
			myQuery.setParameter("caja", caja);
			myQuery.setParameter("usuario", usuario);
			return myQuery.getResultList();

		} else {
				TypedQuery<CierreCaja> myQuery = this.entityManager.createQuery(
						"SELECT c FROM CierreCaja c WHERE c.fechaApertura>=:fechaInicio AND c.fechaApertura<=:fechaFin AND c.usuario=:usuario AND c.estado=:estado",
						CierreCaja.class);
				myQuery.setParameter("fechaInicio", fechaInicio);
				myQuery.setParameter("fechaFin", fechaFin);
				myQuery.setParameter("estado", estado);
				myQuery.setParameter("usuario", usuario);
				return myQuery.getResultList();
		}
		

	}

}
