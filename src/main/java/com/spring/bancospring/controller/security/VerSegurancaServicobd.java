package com.spring.bancospring.controller.security;

import com.spring.bancospring.model.Cliente;
import com.spring.bancospring.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class VerSegurancaServicobd implements UserDetailsService {
    @Autowired
    private ClienteRepository cliente;
    @Override
    public UserDetails loadUserByUsername(String usuario) {
        Cliente usuarioDados = cliente.findByUsuario(usuario);
        if(usuarioDados == null){
            throw new UsernameNotFoundException(usuario);
        }
        Set<GrantedAuthority> previlegio = new HashSet<GrantedAuthority>();
        usuarioDados.getAcesso().forEach(
                acesso -> {
                    previlegio.add(new SimpleGrantedAuthority("CLIENTE_" + acesso));
                }
        );
        UserDetails detalheUsuario = new org.springframework.security.core.userdetails.User(
                usuarioDados.getUsuario(),
                usuarioDados.getSenha(),
                previlegio);
        return detalheUsuario;
    }
}
