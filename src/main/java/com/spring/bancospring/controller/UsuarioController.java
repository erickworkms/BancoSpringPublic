package com.spring.bancospring.controller;

import com.spring.bancospring.dto.*;
import com.spring.bancospring.model.Cliente;

import com.spring.bancospring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/login")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //Cria um novo usuario de Login
    @PostMapping("/crialogin")
    public ResponseEntity<String> criarUsuario(@RequestBody ClienteDTO cliente) {

        Cliente verCliente = usuarioService.criaUsuario(cliente);
        if (verCliente != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Usuario foi criado com sucesso");
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario já existe no sistema");
    }

    //Verifica usuario e senha e faz a autenticação de usuario
    @PostMapping("/verlogin")
    public ResponseEntity<String> verLoginUsuario(@RequestParam String nome, @RequestParam String senha) {

        return usuarioService.verLoginUsuario(nome, senha);
    }

    //Verifica dados para o menu principal após o login e retorna as variaveis no front-end
    @GetMapping("/verDadoslogin")
    public ResponseEntity<LoginDTO> verDadosLogin(@RequestHeader("Authorization") String usuario) {

        return usuarioService.verDadosLogin(usuario);
    }

    @GetMapping("/verDadosCadastro")
    public ResponseEntity<ClienteDTO> verDadosCadastro(@RequestHeader("Authorization") String usuario) {

        return usuarioService.verDadosCadastro(usuario);
    }

    //Deleta o usuario do banco de dados junto com suas contas
    @DeleteMapping("/{id}")
    public void deletarUsuario(ClienteDTO clienteDTO) {

    }

    //Verifica qual conta foi escolhida pelo usuario
    @PostMapping("/atualizaContaEscolhida")
    public ResponseEntity<String> verContaEscolhida(@RequestBody String numeroConta, @RequestHeader("Authorization") String usuario) {
        System.out.println("passou no teste da conta escolhida" + numeroConta);
        return usuarioService.verContaEscolhida(numeroConta, usuario);
    }

    @PostMapping("/transferencia")
    public ResponseEntity<String> verTransferencia(@RequestBody TransferenciaDTO transferenciaDTO, @RequestHeader("Authorization") String usuario) {

        return usuarioService.verTransferencia(transferenciaDTO, usuario);
    }

    @PostMapping("/pagamento")
    public ResponseEntity<String> verPagamento(@RequestBody PagamentoDTO pagamentoDTO, @RequestHeader("Authorization") String usuario) {

        return usuarioService.verPagamento(pagamentoDTO, usuario);
    }

    @PostMapping("/deposito")
    public ResponseEntity<String> verDeposito(@RequestBody DepositoDTO depositoDTO, @RequestHeader("Authorization") String usuario) {

        return usuarioService.verDeposito(depositoDTO, usuario);
    }

    @PostMapping("/emprestimo")
    public ResponseEntity<String> verEmprestimo(@RequestBody EmprestimoDTO emprestimoDTO, @RequestHeader("Authorization") String usuario) {

        return usuarioService.verEmprestimo(emprestimoDTO, usuario);
    }

    @PostMapping("/atualizarCadastro")
    public ResponseEntity<String> verAtualizaCadastro(@RequestBody ClienteDTO cliente, @RequestHeader("Authorization") String usuario) {

        return usuarioService.verAtualizaCadastro(cliente, usuario);
    }

    @PostMapping("/extratoDeposito")
    public ResponseEntity<List<RegistroDepositoDTO>> verListaDepositos(@RequestBody DataTransacoesDTO dataTransacoesDTO, @RequestHeader("Authorization") String usuario) {

        return usuarioService.verListaDepositos(dataTransacoesDTO, usuario);
    }

    @PostMapping("/extratoPagamento")
    public ResponseEntity<List<PagamentoDTO>> verListaPagamentos(@RequestBody DataTransacoesDTO dataTransacoesDTO, @RequestHeader("Authorization") String usuario) {

        return usuarioService.verListaPagamentos(dataTransacoesDTO, usuario);
    }

    @PostMapping("/extratoTransferencia")
    public ResponseEntity<List<RegistroTransferenciaDTO>> verListaTransferencias(@RequestBody DataTransacoesDTO dataTransacoesDTO, @RequestHeader("Authorization") String usuario) {

        return usuarioService.verListaTransferencias(dataTransacoesDTO, usuario);
    }
}

