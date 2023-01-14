package ec.edu.uce.repository;

import java.util.List;

import ec.edu.uce.modelo.Proveedor;
import ec.edu.uce.modelo.SubProducto;

public interface ISubProductoRepo {
	
	void insertarSubProducto(SubProducto subProducto);

	SubProducto buscarSubProducto(Integer id);

	List<SubProducto> buscarTodosSubProducto();

	void actualizarSubProducto(SubProducto subProducto);

	void eliminarSubProducto(Integer id);

	SubProducto buscarProductoPorCodigoBarras(String codigoBarras);

	List<SubProducto> buscarSubProductoPorNombre(String nombre);

	List<SubProducto> buscarSubProductoPorCategoria(String categoria);

	List<SubProducto> buscarSubProductoPorNombreProv(String nombre, Proveedor proveedor);

}
