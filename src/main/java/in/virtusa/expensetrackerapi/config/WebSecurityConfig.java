package in.virtusa.expensetrackerapi.config;

import in.virtusa.expensetrackerapi.jwtSecurity.JWTAuthenticationEntryPoint;
import in.virtusa.expensetrackerapi.security.CustomUserDetailsService;
import in.virtusa.expensetrackerapi.security.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static in.virtusa.expensetrackerapi.constant.SecurityConstant.*;

@Configuration
public class WebSecurityConfig {

	private final CustomUserDetailsService userDetailsService;

	private final JwtRequestFilter jwtRequestFilter;

	private  final JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public WebSecurityConfig(CustomUserDetailsService userDetailsService, JwtRequestFilter jwtRequestFilter, JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth->
				   auth.requestMatchers(LOGIN,REGISTER).permitAll()
						   .requestMatchers(UPDATE_EXPENSE,DELETE_EXPENSE).hasAuthority(ROLE_ADMIN)
						   .requestMatchers(GET_EXPENSE_BY_CATEGORY,
								   GET_EXPENSE_BY_NAME,GET_EXPENSE_BY_DATE,
								   NEW_EXPENSE,GET_ALL_EXPENSES,GET_EXPENSE_BY_ID).hasAnyAuthority(ROLE_ADMIN,ROLE_USER)
						   .anyRequest().authenticated()
				)
				.exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint)) // sending exception user is not valid
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // to make stateless
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // use jwtAuthenticationRequestFilter then use UsernamePasswordAuthenticationFilter to authenticated

		return http.build();

	}

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	

	
}


























