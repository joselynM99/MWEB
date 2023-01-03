package ec.edu.uce.service;

import java.util.List;

import ec.edu.uce.modelo.Proveedor;

public interface IProveedorService {

	void insertarProveedor(Proveedor proveedor);

	Proveedor buscarProveedor(Integer id);

	List<Proveedor> buscarTodosProveedor();

	
	void eliminarProveedor(Integer id);

	void actualizarProveedor(Proveedor proveedor);
	
	List<Proveedor> buscarProveedorPorNombre(String nombreEmpresa);

	Proveedor buscarProveedorIdentificacion(String identificacion);
}
