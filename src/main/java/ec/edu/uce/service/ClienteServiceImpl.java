package ec.edu.uce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.repository.IClienteRepo;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteRepo clienteRepo;

	@Override
	public void insertarCliente(Cliente cliente) {
		this.clienteRepo.insertarCliente(cliente);
	}

	@Override
	public Cliente buscarCliente(Integer id) {
		return this.clienteRepo.buscarCliente(id);
	}

	@Override
	public List<Cliente> buscarTodosCliente() {
		return this.clienteRepo.buscarTodosCliente();
	}

	@Override
	public void actualizarCliente(Cliente cliente) {
		this.clienteRepo.actualizarCliente(cliente);
	}

	@Override
	public void eliminarCliente(Integer id) {
		this.clienteRepo.eliminarCliente(id);
	}

}
