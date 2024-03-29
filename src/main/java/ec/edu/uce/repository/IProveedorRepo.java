package ec.edu.uce.repository;

import java.util.List;

import ec.edu.uce.modelo.Proveedor;

public interface IProveedorRepo {

	void insertarProveedor(Proveedor proveedor);

	Proveedor buscarProveedor(Integer id);

	List<Proveedor> buscarTodosProveedor();

	void eliminarProveedor(Integer id);

	void actualizarProveedor(Proveedor proveedor);

	List<Proveedor> buscarProveedorPorNombre(String nombreEmpresa);

	Proveedor buscarProveedorIdentificacion(String identificacion);

}
