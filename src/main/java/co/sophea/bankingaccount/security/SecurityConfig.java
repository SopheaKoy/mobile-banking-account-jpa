package co.sophea.bankingaccount.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.JeeConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // it means you want customize
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();

        auth.setUserDetailsService(userDetailsService);

        auth.setPasswordEncoder(passwordEncoder);

        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //disable ui spring security when you do api

        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(auth->{

            auth.anyRequest().authenticated();

        });

        // Security Mechanism
        // Http Basic   Authentication (STATELESS)
        // http.httpBasic(Customizer.withDefaults());

        // Security Mechanism JWT
        // JWT (STATELESS)
        // where you change mechanism jwt need jwtAuthenticationConverter
        http.oauth2ResourceServer( oauth2 -> oauth2.jwt(
                jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter())
        ));


        // do api it stateless it means when has user request and response it didn't save history.

        http.sessionManagement(session->{
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });


        // create bean jwtAuthentication


        return http.build();
    }
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(){

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

       // converter.convert();

        return converter;
    }

}
