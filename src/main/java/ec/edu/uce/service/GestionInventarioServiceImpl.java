package ec.edu.uce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.controller.dto.ProductoDTO;
import ec.edu.uce.controller.dto.SubProductoDTO;
import ec.edu.uce.modelo.Producto;
import ec.edu.uce.modelo.Proveedor;
import ec.edu.uce.modelo.SubProducto;

@Service
public class GestionInventarioServiceImpl implements IGestionInventarioService {

	@Autowired
	private IProductoService productoService;

	@Autowired
	private IProveedorService proveedorService;

	@Autowired
	private IImpuestoService impuestoService;

	@Autowired
	private IMarcaService marcaService;

	@Autowired
	private ISeccionService seccionService;
	
	@Autowired
	private ISubProductoService subProductoService;

	@Override
	public void agregarProducto(ProductoDTO productoDTO) {

		Producto producto = new Producto(null, productoDTO.getCodigoBarras(), productoDTO.getNombre(),
				productoDTO.getDescripcion(), productoDTO.getCostoPromedio(), productoDTO.getPrecioVenta(),
				productoDTO.getStockActual(),
				this.proveedorService.buscarProveedor(Integer.valueOf(productoDTO.getProveedor())),
				this.impuestoService.buscarImpuesto(Integer.valueOf(productoDTO.getImpuesto())),
				this.seccionService.buscarSeccion(Integer.valueOf(productoDTO.getSeccion())),
				this.marcaService.buscarMarca(Integer.valueOf(productoDTO.getMarca())));
		this.productoService.insertarProducto(producto);
	}

	@Override
	public void agregarSubProducto(SubProductoDTO subproductoDTO) {

		SubProducto subproducto = new SubProducto(null, subproductoDTO.getCodigoBarras(), subproductoDTO.getNombre(),
				subproductoDTO.getDescripcion(), subproductoDTO.getCantidadRelacionada(),
				subproductoDTO.getCostoPromedio(), subproductoDTO.getPrecioVenta(), subproductoDTO.getStockActual(),
				this.impuestoService.buscarImpuesto(Integer.valueOf(subproductoDTO.getImpuesto())),
				this.productoService.buscarProducto(Integer.valueOf(subproductoDTO.getProducto())),				
				this.proveedorService.buscarProveedor(Integer.valueOf(subproductoDTO.getProveedor())),				
				this.marcaService.buscarMarca(Integer.valueOf(subproductoDTO.getMarca())),
				this.seccionService.buscarSeccion(Integer.valueOf(subproductoDTO.getSeccion())));

		this.subProductoService.insertarSubProducto(subproducto);
	}

	@Override
	public void agregarProveedor(Proveedor proveedor) {
		this.proveedorService.insertarProveedor(proveedor);

	}

	@Override
	public void actualizarProductoDTO(ProductoDTO productoDTO) {

		Producto producto = new Producto(productoDTO.getId(), productoDTO.getCodigoBarras(), productoDTO.getNombre(),
				productoDTO.getDescripcion(), productoDTO.getCostoPromedio(), productoDTO.getPrecioVenta(),
				productoDTO.getStockActual(),
				this.proveedorService.buscarProveedor(Integer.valueOf(productoDTO.getProveedor())),
				this.impuestoService.buscarImpuesto(Integer.valueOf(productoDTO.getImpuesto())),
				this.seccionService.buscarSeccion(Integer.valueOf(productoDTO.getSeccion())),
				this.marcaService.buscarMarca(Integer.valueOf(productoDTO.getMarca())));
		producto.setId(productoDTO.getId());

		this.productoService.actualizarProducto(producto);

	}
	
	
	

}
