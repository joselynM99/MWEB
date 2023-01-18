package ec.edu.uce.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.controller.dto.ProdMasVendido;
import ec.edu.uce.modelo.Caja;
import ec.edu.uce.modelo.CierreCaja;
import ec.edu.uce.modelo.DetalleVenta;
import ec.edu.uce.modelo.Producto;
import ec.edu.uce.modelo.Usuario;


public interface IReportesService {

	List<CierreCaja> buscarCierreCajas(LocalDateTime fechaInicio, LocalDateTime fechaFin, Caja caja, Boolean estado,
			Usuario usuario);


	ProdMasVendido buscarProductosMasVendidos(LocalDateTime fechaInicio, LocalDateTime fechaFin,
			Producto producto);


	List<ProdMasVendido> hacerListaProdMasVendidos(LocalDateTime fechaInicio, LocalDateTime fechaFin);


	void borrarProductoVenta(DetalleVenta detalle);
	
	

}
