package ec.edu.uce.repository;

import java.util.List;

import ec.edu.uce.controller.dto.ProductoDTO;
import ec.edu.uce.modelo.Producto;
import ec.edu.uce.modelo.Proveedor;

public interface IProductoRepo {

	void insertarProducto(Producto producto);

	Producto buscarProducto(Integer id);

	List<Producto> buscarTodosProductos();

	//List<ProductoDTO> buscarTodosProductosDTO();

	void actualizarProducto(Producto producto);

	void eliminarProducto(Integer id);

	Producto buscarProductoPorCodigoBarras(String codigoBarras);

	List<Producto> buscarProductoPorNombre(String nombre);

	List<Producto> buscarProductoPorCategoria(String categoria);

	List<Producto> buscarProductoPorNombreProv(String nombre, Proveedor proveedor);

	Producto buscarProductoPorCodigoBarrasProv(String codigoBarras, Proveedor prov);
}
