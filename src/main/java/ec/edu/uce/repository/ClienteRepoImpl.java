package ec.edu.uce.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.Compra;

@Transactional
@Repository
public class ClienteRepoImpl implements IClienteRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertarCliente(Cliente cliente) {
		this.entityManager.persist(cliente);
	}

	@Override
	public Cliente buscarCliente(Integer id) {
		return this.entityManager.find(Cliente.class, id);
	}

	@Override
	public List<Cliente> buscarTodosCliente() {
		TypedQuery<Cliente> myQuery = this.entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class);
		return myQuery.getResultList();
	}

	@Override
	public void actualizarCliente(Cliente cliente) {
		this.entityManager.merge(cliente);
	}

	@Override
	public void eliminarCliente(Integer id) {
		Cliente c = this.buscarCliente(id);
		this.entityManager.remove(c);
	}

}
