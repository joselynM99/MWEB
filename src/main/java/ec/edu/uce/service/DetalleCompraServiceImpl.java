package ec.edu.uce.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.DetalleCompra;
import ec.edu.uce.repository.IDetalleCompraRepo;

@Service
public class DetalleCompraServiceImpl implements IDetalleCompraService {

	@Autowired
	private IDetalleCompraRepo detalleCompraRepo;

	@Override
	public void insertarDetalleCompra(DetalleCompra detalleCompra) {
		this.detalleCompraRepo.insertarDetalleCompra(detalleCompra);
	}

	@Override
	public DetalleCompra buscarDetalleCompra(Integer id) {
		return this.detalleCompraRepo.buscarDetalleCompra(id);
	}

	@Override
	public List<DetalleCompra> buscarTodosDetalleCompra() {
		return this.detalleCompraRepo.buscarTodosDetalleCompra();
	}

	@Override
	public void actualizarDetalleCompra(DetalleCompra detalleCompra) {
		this.detalleCompraRepo.actualizarDetalleCompra(detalleCompra);
	}

	@Override
	public void eliminarDetalleCompra(Integer id) {
		this.detalleCompraRepo.eliminarDetalleCompra(id);
	}
	
	@Override
	public BigDecimal calcularValorCompra(Integer cantidad, BigDecimal precio) {
		return precio.multiply(new BigDecimal(cantidad));
	}

}
