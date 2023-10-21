package com.spring.bancospring.controller.security.token;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


import com.spring.bancospring.model.Cliente;
import com.spring.bancospring.repository.ClienteRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
public class FiltroToken extends OncePerRequestFilter {

    @Autowired
    GeradorJWTToken verificaToken;

    @Autowired
    ClienteRepository clienteRepository;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        //esta implementação só esta validando a integridade do token
        //Não esquecer, o token tem que estar completo para o authorization funcionar, logo não tirar o bearer antes do tempo

        var token = recoverToken(request);

        if (token != null && !token.isEmpty()) {

            var login = verificaToken.verificaToken(token);

            Cliente dadosUsuario = clienteRepository.findByUsuario(login);
            //Cria uma variavel que vai armazenar o tipo de acesso como admin user public)
            List<SimpleGrantedAuthority> authorities = authorities(dadosUsuario.getAcesso());
            //Cria a variavel com o token para autenticação com usuario e senha, credenciais e authorities(admin,user,public etc)
            UsernamePasswordAuthenticationToken tokenUsuario =
                    new UsernamePasswordAuthenticationToken(
                            dadosUsuario.getUsuario(),
                            null,
                            authorities);

            SecurityContextHolder.getContext().setAuthentication(tokenUsuario);
        } else {
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request, response);
    }

    //Cria um vetor com todos os acessos do usuario, por exemplo, admin teria acesso ADMIN e USUARIO
    private List<SimpleGrantedAuthority> authorities(List<String> roles) {
        return roles.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");

        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}

