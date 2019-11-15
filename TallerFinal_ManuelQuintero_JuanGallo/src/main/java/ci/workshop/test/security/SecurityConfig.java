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
	
//	@Autowired
//	private MyCustomUserDetailsService userDetailsService;
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(authenticationProvider());
//	}

//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(userDetailsService);
//		authProvider.setPasswordEncoder(encoder());
//		return authProvider;
//	}
//
//	@Bean
//	public PasswordEncoder encoder() {
//		return new BCryptPasswordEncoder(11);
//	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.authorizeRequests() 
		.antMatchers("/bus/**").access("hasRole('admin')")
		.antMatchers("/driver/**").access("hasRole('admin')")
		.antMatchers("/route/**").access("hasRole('admin')")
		.antMatchers("/services/**").access("hasRole('admin') OR hasRole('operador')")
		.antMatchers("/services/add-service/**").access("hasRole('operador')")
		.antMatchers("/services/edit/**").access("hasRole('operador')")	
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/login").permitAll().and()
		.logout().invalidateHttpSession(true).clearAuthentication(true).permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);

	}
}