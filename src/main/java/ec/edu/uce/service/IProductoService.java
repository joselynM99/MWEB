package ec.edu.uce.service;

import java.util.List;

import ec.edu.uce.modelo.Producto;

public interface IProductoService {

	void insertarProducto(Producto producto);

	Producto buscarProducto(Integer id);

	List<Producto> buscarTodosProductos();

	void actualizarProducto(Producto producto);

	void eliminarProducto(Integer id);

	Producto buscarProductoPorCodigoBarras(String codigoBarras);

	List<Producto> buscarProductoPorNombre(String nombre);

	List<Producto> buscarProductoPorCategoria(String categoria);

}
