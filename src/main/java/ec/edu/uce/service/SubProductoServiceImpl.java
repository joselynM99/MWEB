package ec.edu.uce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.Proveedor;
import ec.edu.uce.modelo.SubProducto;
import ec.edu.uce.repository.ISubProductoRepo;

@Service
public class SubProductoServiceImpl implements ISubProductoService {

	@Autowired
	private ISubProductoRepo subProductoRepo;

	@Override
	public void insertarSubProducto(SubProducto subProducto) {
		this.subProductoRepo.insertarSubProducto(subProducto);
	}

	@Override
	public SubProducto buscarSubProducto(Integer id) {
		return this.subProductoRepo.buscarSubProducto(id);
	}

	@Override
	public List<SubProducto> buscarTodosSubProducto() {
		return this.subProductoRepo.buscarTodosSubProducto();
	}

	@Override
	public void actualizarSubProducto(SubProducto subProducto) {
		this.subProductoRepo.actualizarSubProducto(subProducto);
	}

	@Override
	public void eliminarSubProducto(Integer id) {
		this.subProductoRepo.eliminarSubProducto(id);
	}

	@Override
	public SubProducto buscarProductoPorCodigoBarras(String codigoBarras) {
		
		return this.subProductoRepo.buscarProductoPorCodigoBarras(codigoBarras);
	}

	@Override
	public List<SubProducto> buscarSubProductoPorNombre(String nombre) {
		return this.subProductoRepo.buscarSubProductoPorNombre("%"+nombre+"%");
	}

	@Override
	public List<SubProducto> buscarSubProductoPorCategoria(String categoria) {
		return this.subProductoRepo.buscarSubProductoPorCategoria(categoria);
	}

	@Override
	public List<SubProducto> buscarSubProductoPorNombreProv(String nombre, Proveedor proveedor) {
		
		return this.subProductoRepo.buscarSubProductoPorNombreProv("%"+nombre+"%", proveedor);
	}

}
