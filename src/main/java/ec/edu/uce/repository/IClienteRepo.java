package ec.edu.uce.repository;

import java.util.List;

import ec.edu.uce.modelo.Cliente;

public interface IClienteRepo {
	
	void insertarCliente(Cliente cliente);

	Cliente buscarCliente(Integer id);

	List<Cliente> buscarTodosCliente();

	void actualizarCliente(Cliente cliente);

	void eliminarCliente(Integer id);

}
