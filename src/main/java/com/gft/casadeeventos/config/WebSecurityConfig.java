package com.gft.casadeeventos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			
				.antMatchers("/cadastro").permitAll()
				.antMatchers("/historico").hasAnyAuthority("USER","ADMIN")
				.antMatchers("/casadeshow").hasAuthority("ADMIN")
				.antMatchers("/evento").hasAuthority("ADMIN")
				.antMatchers("/").hasAnyAuthority("USER","ADMIN")
				.antMatchers("/home").hasAnyAuthority("USER","ADMIN")
				.antMatchers("/api/**").permitAll()
				.antMatchers("/swagger-ui.html").hasAnyAuthority("USER","ADMIN")
				.anyRequest()
				.authenticated()
			
			.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
				.defaultSuccessUrl("/",true)
			.and()
				.logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true);
		
			
		}
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		return provider;
	}
	
	@Override
	public void configure(WebSecurity config) throws Exception{
		config.ignoring()
		.antMatchers("/css/**")
		.antMatchers("/js/**")
		.antMatchers("/imagens/**")
		.antMatchers("/META-INF/resources/webjars/**")
		.antMatchers("/csrf/**");
	}
	
}
	
	
