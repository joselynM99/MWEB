package ec.edu.uce.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.CierreCaja;
import ec.edu.uce.modelo.Usuario;
import ec.edu.uce.repository.ICierreCajaRepo;

@Service
public class CierreCajaServiceImpl implements ICierreCajaService {

	@Autowired
	private ICierreCajaRepo cierreCajaRepo;

	@Override
	public void insertarCierreCaja(CierreCaja cierreCaja) {
		this.cierreCajaRepo.insertarCierreCaja(cierreCaja);
	}

	@Override
	public CierreCaja buscarCierreCaja(Integer id) {
		return this.cierreCajaRepo.buscarCierreCaja(id);
	}

	@Override
	public List<CierreCaja> buscarTodosCierreCaja() {
		return this.cierreCajaRepo.buscarTodosCierreCaja();
	}

	@Override
	public void actualizarCierreCaja(CierreCaja cierreCaja) {
		this.cierreCajaRepo.actualizarCierreCaja(cierreCaja);
	}

	@Override
	public void eliminarCierreCaja(Integer id) {
		this.cierreCajaRepo.eliminarCierreCaja(id);
	}

	@Override
	public boolean buscarCierreCajaActivo(Usuario usuario) {

		return this.cierreCajaRepo.buscarCierreCajaActivo(usuario);
	}

	@Override
	public CierreCaja obtenerCierreCajaActivo(Usuario usuario) {
		return this.cierreCajaRepo.obtenerCierreCajaActivo(usuario);
	}

	@Override
	public void abrirCaja(Usuario usuario, BigDecimal valorInicial) {

		CierreCaja cierreNuevo = new CierreCaja();
		cierreNuevo.setCaja(usuario.getCaja());
		cierreNuevo.setValorApertura(valorInicial);
		cierreNuevo.setFechaApertura(LocalDateTime.now());
		cierreNuevo.setUsuario(usuario);
		cierreNuevo.setEstado(true);

		this.insertarCierreCaja(cierreNuevo);

	}
	
	@Override
	public void cerrarCaja(Usuario usuario, BigDecimal diferencia, BigDecimal valorContable, BigDecimal valorCierre) {

		CierreCaja cierreNuevo = this.obtenerCierreCajaActivo(usuario);
		cierreNuevo.setId(cierreNuevo.getId());

		cierreNuevo.setEstado(false);
		cierreNuevo.setDiferencia(diferencia);
		cierreNuevo.setFechaCierre(LocalDateTime.now());
		cierreNuevo.setValorContable(valorContable);
		cierreNuevo.setValorCierre(valorCierre);
		

		this.actualizarCierreCaja(cierreNuevo);

	}

}
