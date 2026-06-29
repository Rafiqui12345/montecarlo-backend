package com.montecarlo.config;

import com.montecarlo.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/auth/**", "/usuarios").permitAll()
                        .requestMatchers("/usuarios/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/configuracion").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/configuracion").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/disponibilidad").permitAll()
                        .requestMatchers(HttpMethod.POST, "/consultas").authenticated()
                        .requestMatchers(HttpMethod.GET, "/consultas/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/consultas/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/historial/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/reservas/**").hasRole("ADMIN")
                        .requestMatchers("/dashboard/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        http
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}