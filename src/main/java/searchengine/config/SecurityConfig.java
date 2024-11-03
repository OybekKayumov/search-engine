package searchengine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableWebSecurity
@EnableGlobalMehodSecurity(prePostEnabled = true)
public class SecurityConfig {

	private HttpSecurity http;

	@Bean
	public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {

		UserDetails user = User
						.withUsername("user")
						.password(passwordEncoder.encode("user")).roles("USER").build();

		UserDetails admin = User
						.withUsername("admin")
						.password(passwordEncoder.encode("admin")).roles("ADMIN").build();

		return new InMemoryUserDetailsManager(user, admin);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return encoder;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
						.anyRequest()
						.authenticated()
						.and()
						.formLogin()
						.and().csrf()
						.disable();

		return http.build();
	}
}
