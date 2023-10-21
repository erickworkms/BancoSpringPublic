package com.spring.bancospring.controller.security;


import com.spring.bancospring.controller.security.token.FiltroToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.beans.Customizer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    FiltroToken filtroToken;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    private static final String[] SWAGGER_WHITELIST = {
            "/v3/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/swagger-ui-custom.html"
    };


    @Bean
    SecurityFilterChain filtroSeguranca(HttpSecurity http) throws Exception {

        return http.cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .anonymous(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        configAutorizacao -> {
                                configAutorizacao.requestMatchers("/public").permitAll().
                                        requestMatchers(HttpMethod.GET,"/*", "/src/ui/build/*").permitAll().
                                        requestMatchers(HttpMethod.GET,"/swagger-ui/index.html").permitAll().

                                        requestMatchers("/sessao","/sessao/**").permitAll().

                                        requestMatchers(HttpMethod.POST, "/login/**","/logout").permitAll().
                                        requestMatchers(SWAGGER_WHITELIST).authenticated().
                                        requestMatchers(HttpMethod.POST, "/*").hasAnyRole("USUARIO")
                                        .anyRequest().permitAll();
                        }
                )
                .logout((logout) ->
                        logout.deleteCookies("remove")
                                .invalidateHttpSession(false)
                                .logoutUrl("/custom-logout")
                                .logoutSuccessUrl("/logout-success")
                ).sessionManagement((sessionManagement) ->
                        sessionManagement
                                .sessionConcurrency((sessionConcurrency) ->
                                        sessionConcurrency
                                                .maximumSessions(1)
                                                .expiredUrl("/login?expired")
                                ).sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(filtroToken, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}

