package com.jrp.pma.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// jdbc authentication:
		auth.jdbcAuthentication()
		.usersByUsernameQuery("select username, password, enabled from user_accounts "
				+ "where username=?")
		.authoritiesByUsernameQuery("select username, role "
				+ "from user_accounts where username=?")
		.dataSource(dataSource)
		.passwordEncoder(bCryptEncoder);
		
		// in-memory authentication: 
		/*
		auth.inMemoryAuthentication()
		.withUser("user")
			.password("123")
			.roles("USER")
		.and()
		.withUser("user2")
			.password("123")
			.roles("USER")
		.and()
		.withUser("manager")
			.password("123")
			.roles("ADMIN");
		*/
	}
	/*
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
//		.antMatchers("/projects/new").hasAuthority("ADMIN")
//		.antMatchers("/projects/save").hasAuthority("ADMIN")
		//.antMatchers("/employees/new").hasAuthority("ADMIN")
		//.antMatchers("/employees/save").hasAuthority("ADMIN")
		.antMatchers("/","/**").permitAll()
		.and()
		.formLogin();
		http.csrf().disable();
	}
}
