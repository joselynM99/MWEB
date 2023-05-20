package ec.edu.uce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.controller.dto.ProductoDTO;
import ec.edu.uce.modelo.Producto;
import ec.edu.uce.modelo.Proveedor;
import ec.edu.uce.repository.IProductoRepo;

@Service
public class ProductoServiceImpl implements IProductoService {

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
		System.out.println("-----buscar productos");
		return this.productoRepo.buscarTodosProductos();
	}

	@Override
	public void actualizarProducto(Producto producto) {
		System.out.println("-----actualizando service");

		this.productoRepo.actualizarProducto(producto);
	}

	@Override
	public void eliminarProducto(Integer id) {
		this.productoRepo.eliminarProducto(id);
	}

	@Override
	public Producto buscarProductoPorCodigoBarras(String codigoBarras) {
		System.out.println("-----buscar productos");
		return this.productoRepo.buscarProductoPorCodigoBarras(codigoBarras);
	}

	@Override
	public List<Producto> buscarProductoPorNombre(String nombre) {
		System.out.println("-----buscar productos");
		return this.productoRepo.buscarProductoPorNombre("%" + nombre + "%");
	}

	@Override
	public List<Producto> buscarProductoPorCategoria(String categoria) {
		System.out.println("-----buscar productos");
		return this.productoRepo.buscarProductoPorCategoria(categoria);
	}

	/*@Override
	public List<ProductoDTO> buscarTodosProductosDTO() {
		
		return this.productoRepo.buscarTodosProductosDTO();
	}*/
	public List<Producto> buscarProductoPorNombreProv(String nombre, Proveedor proveedor) {
		System.out.println("-----buscar productos");
		return this.productoRepo.buscarProductoPorNombreProv("%"+nombre+"%", proveedor);
	}

	@Override
	public Producto buscarProductoPorCodigoBarrasProv(String codigoBarras, Proveedor prov) {
		System.out.println("-----buscar productos");
		return this.productoRepo.buscarProductoPorCodigoBarrasProv(codigoBarras, prov);
	}


}
