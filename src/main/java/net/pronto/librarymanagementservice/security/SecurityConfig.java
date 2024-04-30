package net.pronto.librarymanagementservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig{
	
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);	
	}

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> authz
        		.requestMatchers(AntPathRequestMatcher.antMatcher("/api/book")).hasAuthority("USER")
        		.requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
        		.requestMatchers(AntPathRequestMatcher.antMatcher("/swagger-ui/**")).permitAll()
        		.requestMatchers(AntPathRequestMatcher.antMatcher("/health-check")).permitAll()
        		.anyRequest()
        		.authenticated()
        		)
        .headers(headers -> headers.frameOptions().disable())
        .csrf(csrf -> csrf.disable())
	    .httpBasic();       
	        return http.build();
    }
}
