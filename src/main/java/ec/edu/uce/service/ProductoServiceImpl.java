package ec.edu.uce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.Producto;
import ec.edu.uce.repository.IProductoRepo;

@Service
public class ProductoServiceImpl implements IProductoService{
	
	@Autowired
	private IProductoRepo productoRepo;

	@Override
	public void insertarProducto(Producto producto) {
		this.productoRepo.insertarProducto(producto);
	}

	@Override
	public Producto buscarProducto(Integer id) {
		return this.productoRepo.buscarProducto(id);
	}

	@Override
	public List<Producto> buscarTodosProductos() {
		return this.productoRepo.buscarTodosProductos();
	}

	@Override
	public void actualizarProducto(Producto producto) {
		this.productoRepo.actualizarProducto(producto);
	}

	@Override
	public void eliminarProducto(Integer id) {
		this.productoRepo.eliminarProducto(id);
	}

	@Override
	public Producto buscarProductoPorCodigoBarras(String codigoBarras) {
		return this.productoRepo.buscarProductoPorCodigoBarras(codigoBarras);
	}

	@Override
	public List<Producto> buscarProductoPorNombre(String nombre) {
		return this.productoRepo.buscarProductoPorNombre("%"+nombre+"%");
	}

	@Override
	public List<Producto> buscarProductoPorCategoria(String categoria) {
		return this.productoRepo.buscarProductoPorCategoria(categoria);
	}
	
	

}
