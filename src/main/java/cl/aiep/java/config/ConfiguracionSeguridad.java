package cl.aiep.java.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests(authorize -> authorize
					.mvcMatchers("/", "/nosotros", "/contacto").permitAll()//permite ver a todos esas rutas
					.mvcMatchers("/admin/reporte", "/admin/index").hasRole("ADMIN")//permite ver solo al ADMIN
					.anyRequest().authenticated()
					)
					.logout(logout -> logout
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")
					)	
					)
			
			
			;
	}
	
	
}
