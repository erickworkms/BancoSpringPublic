package com.spring.bancospring.service;


import com.spring.bancospring.dto.ClienteDTO;
import com.spring.bancospring.dto.ContaDTO;
import com.spring.bancospring.dto.LoginDTO;

public interface ICliente {

	void criarConta(ContaDTO ContaDTO);
	void deletarConta(int contaId, ClienteDTO clienteDTO);
	boolean loginInicio();
	boolean loginUsuario(ClienteDTO clienteDTO);
	LoginDTO verInicioLogin();
	boolean verContaEscolhida(int numeroConta,int agencia,String usuario);
	void criarUsuario(ClienteDTO clienteDTO);
	void deletarUsuario(ClienteDTO clienteDTO);
	void sacar(double valor,ContaDTO ContaDTO);
	void depositar(double valor,ContaDTO ContaDTO,int cpf);
	void transferir(double valor, ContaDTO ContaDTO,int cpf);
	void imprimirExtrato(ContaDTO ContaDTO);
}
