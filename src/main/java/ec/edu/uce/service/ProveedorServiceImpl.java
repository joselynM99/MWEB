package ec.edu.uce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.Proveedor;
import ec.edu.uce.repository.IProveedorRepo;

@Service
public class ProveedorServiceImpl implements IProveedorService {

	@Autowired
	private IProveedorRepo proveedorRepo;

	@Override
	public void insertarProveedor(Proveedor proveedor) {
		this.proveedorRepo.insertarProveedor(proveedor);
	}

	@Override
	public Proveedor buscarProveedor(Integer id) {
		return this.proveedorRepo.buscarProveedor(id);
	}

	@Override
	public List<Proveedor> buscarTodosProveedor() {
		return this.proveedorRepo.buscarTodosProveedor();
	}

	@Override
	public void actualizarProveedor(Proveedor proveedor) {
		this.proveedorRepo.actualizarProveedor(proveedor);
	}

	@Override
	public void eliminarProveedor(Integer id) {
		this.proveedorRepo.eliminarProveedor(id);
	}

	@Override
	public Proveedor buscarProveedorNombre(String nombreEmpresa) {
		return this.proveedorRepo.buscarProveedorNombre(nombreEmpresa);
	}

	@Override
	public List<Proveedor> buscarProveedorPorNombre(String nombreEmpresa) {
		return this.proveedorRepo.buscarProveedorPorNombre(nombreEmpresa);
	}

}
