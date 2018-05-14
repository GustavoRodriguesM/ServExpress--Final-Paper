package br.com.web.servexpress.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetail userDao;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/assets/**").permitAll()
				.antMatchers("/admin/**").hasRole("ADMINISTRADOR")
				.antMatchers("/cliente/**").hasRole("CLIENTE")
				.antMatchers("/entregador/**").hasRole("ENTREGADOR")
				.antMatchers("/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin().loginPage("/login").defaultSuccessUrl("/redirect")
				.and()
				.logout().logoutSuccessUrl("/login?logout=true")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDao).passwordEncoder(new BCryptPasswordEncoder());
	}

}
