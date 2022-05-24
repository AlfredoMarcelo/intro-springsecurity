package cl.aiep.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.aiep.java.model.Usuario;
import cl.aiep.java.service.UsuarioService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	@GetMapping("/instalar")
	public String instalar() {
		long cantidadUsuarios = usuarioService.cantidadUsuarios();
		if(cantidadUsuarios == 0) { 
		//solo se ejecutara en caso que la tabla de usuarios esté vacia
		Usuario usuario = new Usuario(null, "aldo@gmail.com", "1234", "ADMIN");
		usuarioService.crearUsuario(usuario);
		}
		return "redirect:/";
	}
	
	
	
	@GetMapping("/index")
	public String index() {
		return "admin/index";
	}
	
	@GetMapping("/reporte")
	public String reporte() {
		return "admin/reporte";
	}

}
