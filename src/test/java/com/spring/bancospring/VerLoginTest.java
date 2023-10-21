package com.spring.bancospring;

import com.spring.bancospring.dto.ClienteDTO;
import com.spring.bancospring.model.Cliente;
import com.spring.bancospring.repository.ClienteRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@RunWith(SpringRunner.class)
public class VerLoginTest {

    ClienteDTO testeDados = new ClienteDTO(
            "Teste do Santos Souza",
            "31/12/1930",
            "157.575.969-10",
            "Rua do testador",
            "11965845885",
            "teste@teste.com",
            "teste",
            "teste1234");

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void testCadastro(){
        Cliente cliente = clienteRepository.findByUsuario(testeDados.getUsuario());
        Cliente clienteSalvo = null;

        if (cliente == null && !clienteRepository.existsByCpf(testeDados.getCpf()) &&
                !clienteRepository.existsByUsuario(testeDados.getUsuario())) {
            cliente = new Cliente(
                    testeDados.getNome(),
                    testeDados.getDataNascimento(),
                    testeDados.getCpf(),
                    testeDados.getEndereco(),
                    testeDados.getTelefone(),
                    testeDados.getEmail(),
                    new ArrayList<>(),
                    testeDados.getUsuario(),
                    testeDados.getSenha()
            );
            cliente.setContaEscolhida(0);
            cliente.getAcesso().add("USUARIO");
            clienteSalvo =clienteRepository.save(cliente);
        }
        assertNull(clienteSalvo);
    }

    @Test
    public void verificaNome(){
        assertTrue(testeDados.getNome().length() < 30 &&
                testeDados.getNome().length() > 10);
    }

    @Test
    public void verificaDataNascimento(){
        assertTrue(testeDados.getDataNascimento().matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$"));
    }
    @Test
    public void verificaCpf(){
        String verificaCpf = testeDados.getCpf().replaceAll("[^0-9]","");
        assertEquals(11,verificaCpf.length());
    }
    @Test
    public void verificaEndereco(){
        assertTrue(testeDados.getEndereco().length() < 70 &&
                testeDados.getEndereco().length() > 10);
    }
    @Test
    public void verificaTelefone(){
        String verificaTelefone = testeDados.getTelefone().replaceAll("[^0-9]","");
        assertEquals(11,verificaTelefone.length());
    }
    @Test
    public void verificaEmail(){
        String estruturaEmail = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern padraoEmail = Pattern.compile(estruturaEmail);
        Matcher verificador = padraoEmail.matcher(testeDados.getEmail());
        assertTrue(verificador.matches());
    }
    @Test
    public void verificaUsuario(){
        Cliente verCliente = clienteRepository.findByUsuario(testeDados.getUsuario());
        assertTrue(verCliente == null && testeDados.getUsuario().length() > 5);
    }
    @Test
    public void verificaSenha(){
        assertTrue(testeDados.getSenha().matches
                (".*[!@#$%^&*()_+\\-={};':\",./<>?\\\\|].*") &&
                testeDados.getSenha().matches(".*\\d.*") &&
                testeDados.getSenha().matches(".*[A-Z].*") &&
                testeDados.getSenha().matches(".*[a-z].*"));
    }
    @Test
    public void testLoginCliente(){
        System.out.println(testeDados.getUsuario());
        
        Cliente verCliente = clienteRepository.findByUsuario(testeDados.getUsuario());
        assertTrue(verCliente != null && testeDados.getSenha().equals(verCliente.getSenha()));
    }
}
