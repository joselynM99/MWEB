package ec.edu.uce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.controller.dto.ProductoDTO;
import ec.edu.uce.modelo.Producto;

@Service
public class GestionInventarioServiceImpl implements IGestionInventarioService{

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

}
