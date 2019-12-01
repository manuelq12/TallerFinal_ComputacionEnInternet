package ci.workshop.test.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import ci.workshop.test.model.UserType;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests()
		.antMatchers("/api/**").permitAll()
		.antMatchers("/bus/**").access("hasRole('admin')")
		.antMatchers("/driver/**").access("hasRole('admin')")
		.antMatchers("/route/**").access("hasRole('admin')")
		.antMatchers("/services/**").access("hasRole('admin') OR hasRole('operador')")
		.antMatchers("/services/add-service/**").access("hasRole('operador')")
		.antMatchers("/services/edit/**").access("hasRole('operador')")	
		.and().formLogin().loginPage("/login").permitAll().and()
		.logout().invalidateHttpSession(true).clearAuthentication(true).permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);

	}
}