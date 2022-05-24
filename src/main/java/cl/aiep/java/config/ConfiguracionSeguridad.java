package cl.aiep.java.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import cl.aiep.java.service.MiServicioUsuario;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter {


	
	
	@Autowired
	MiServicioUsuario miServicioUsuario;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(miServicioUsuario)
			.passwordEncoder(passwordEncoder)
			;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests(authorize -> authorize
					.mvcMatchers("/", "/nosotros", "/contacto", "/admin/instalar").permitAll()//permite ver a todos esas rutas
					.mvcMatchers("/admin/reporte", "/admin/index").hasAuthority("ADMIN")//permite ver solo al ADMIN
					.anyRequest().authenticated()
					)
					.formLogin(form -> form
							.loginPage("/ingreso")
							.defaultSuccessUrl("/",true)// nos sirve para forzar la redireccion y no estemos entrando dos veces
							.permitAll()
					)
					.logout(logout -> logout
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")
					)	
					)
			;
	}
	
	
	
	
	
}
