package ec.edu.uce.service;

import java.util.List;

import ec.edu.uce.modelo.Cliente;

public interface IClienteService {
	
	void insertarCliente(Cliente cliente);

	Cliente buscarCliente(Integer id);

	List<Cliente> buscarTodosCliente();

	void actualizarCliente(Cliente cliente);

	Cliente buscarClienteIdentificacion(String identificacion);
	
	void eliminarCliente(Integer id);

}
