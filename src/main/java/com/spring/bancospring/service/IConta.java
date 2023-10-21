package com.spring.bancospring.service;


import com.spring.bancospring.dto.ClienteDTO;
import com.spring.bancospring.dto.ContaDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface IConta {

	void criarConta(ContaDTO ContaDTO);
	void deletarConta(UUID id);
	//ResponseEntity<List<ContaDTO>> retornarContas();
	boolean verContaEscolhida(int numeroConta,int agencia,String usuario);
	void sacar(double valor,ContaDTO ContaDTO);
	void depositar(double valor,ContaDTO ContaDTO,int cpf);
	void transferir(double valor, ContaDTO ContaDTO,int cpf);
	void imprimirExtrato(ContaDTO ContaDTO);
}
