package ec.edu.uce.service;

import ec.edu.uce.controller.dto.ProductoDTO;
import ec.edu.uce.controller.dto.SubProductoDTO;
import ec.edu.uce.modelo.Proveedor;

public interface IGestionInventarioService {
	public void agregarProducto(ProductoDTO productoDTO);

	public void agregarProveedor(Proveedor proveedor);



	void actualizarProductoDTO(ProductoDTO productoDTO);

	void agregarSubProducto(SubProductoDTO subproductoDTO);

}
