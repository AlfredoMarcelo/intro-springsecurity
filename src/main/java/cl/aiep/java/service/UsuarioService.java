package cl.aiep.java.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.aiep.java.model.Usuario;
import cl.aiep.java.repository.UsuarioRepository;

@Service
public class UsuarioService {
	//se encarga de guardar un usuario, pero antes se encarga de encriptar la contraseña
	
	
	
	@Autowired
	private PasswordEncoder codificadorContrasena;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> buscarPorEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
	
	
	public long cantidadUsuarios() {
		return usuarioRepository.count();
	}
	
	public Usuario crearUsuario(Usuario usuario) {
		String contrasenaCodificada = codificadorContrasena.encode(usuario.getPassword());
		usuario.setPassword(contrasenaCodificada);
		return usuarioRepository.saveAndFlush(usuario);
	}
}
