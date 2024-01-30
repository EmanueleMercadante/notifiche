package it.dedagroup.Authorization.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import it.dedagroup.Authorization.enums.Ruolo;

@Configuration
public class FilterChainManager {

    @Autowired
    private JwtFilter filter;
    @Autowired
    private AuthenticationProvider provider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(t -> t.disable())
                .authorizeHttpRequests(req -> req
//                      .requestMatchers("/all/**").permitAll()
                        .requestMatchers("/admin/**").hasRole(Ruolo.ADMIN.toString())
                        .requestMatchers("/notifica/**").hasAnyRole(Ruolo.CLIENTE.toString(), Ruolo.ADMIN.toString())
                        .requestMatchers("/utente/**").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(provider)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return httpSecurity.build();
    }
}
