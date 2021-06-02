package com.cognizant.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cognizant.authorization.service.CustomerDetailsService;
import com.cognizant.authorization.service.JwtRequestFilter;

/**
 * 
 * 
 * @EnableWebSecurity is used for Spring Security configuration defined in any
 *                    WebSecurityConfigurer or more likely by extending the
 *                    WebSecurityConfigurerAdapter base class and overriding
 *                    individual methods
 *
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomerDetailsService customerDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Configure the authentication manager so that it knows where to load user for
	// matching credentials
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customerDetailsService);
	}

	/**
	 * To configure which request are to be authenticated and which are not to be.
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				// dont authenticate this particular request
				.authorizeRequests().antMatchers("/**").permitAll()
				// all other request needs to be authenticated
				.anyRequest().authenticated().and()
				// we use stateless session;session wont be used to store user's state.
				.exceptionHandling().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// Add a filter to validate the tokens with every request
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	/**
	 * By default ,it is not publicly accessible so we need to explicitly expose it
	 * as bean
	 */
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * to configure WebSecurity. For eg: if you wish to ignore certain requests
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/auth/login", "/h2-console/**", "/v2/api-docs", "/configuration/ui",
				"/configuration/security", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**", "/swagger");
	}
}
