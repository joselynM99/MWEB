package ec.edu.uce.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ec.edu.uce.controller.dto.UsuarioRegistroDTO;
import ec.edu.uce.modelo.Caja;
import ec.edu.uce.modelo.Perfil;
import ec.edu.uce.modelo.Usuario;
import ec.edu.uce.repository.UsuarioRepositorio;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

	@Autowired
	private ICajaService cajaService;

	private UsuarioRepositorio usuarioRepositorio;


	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	private IPerfilService iPerfilService;

	public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio) {
		super();
		this.usuarioRepositorio = usuarioRepositorio;
	}

	@Override
	public Usuario guardar(UsuarioRegistroDTO registroDTO) {
		Caja caja = this.cajaService.buscarCajaPorNombre(registroDTO.getCaja());
			
		Perfil perfil = this.iPerfilService.buscarPerfilNombre(registroDTO.getPerfil());
		System.out.println(registroDTO.getPassword());
		
		Usuario usuario = new Usuario(registroDTO.getTipoId(), registroDTO.getIdentificacion(), 
				registroDTO.getApellidos(), registroDTO.getNombres(), 
				registroDTO.getEmail(), registroDTO.isEstado(),
				passwordEncoder.encode(registroDTO.getPassword()), Arrays.asList(perfil));
		usuario.setCaja(caja);
		return usuarioRepositorio.save(usuario);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepositorio.findByEmail(username);
		if (usuario == null) {
			System.out.println(username);
			throw new UsernameNotFoundException("Usuario o password inválidos");
			
		}
		return new User(usuario.getNombreUsuario(), usuario.getContraseña(), mapearAutoridadesRoles(usuario.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Perfil> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}

	@Override
	public List<Usuario> listarUsuarios() {
		return usuarioRepositorio.findAll();
	}
}
