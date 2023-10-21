package com.spring.bancospring.dto;

import com.spring.bancospring.model.Conta;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDTO {

	private String nome="";

	private String dataNascimento="";

	private String cpf="";

	private String endereco="";

	private String telefone="";

	private String email="";

	private String usuario="";

	private String senha="";

	public ClienteDTO(String nome, String dataNascimento, String cpf, String endereco, String telefone, String email, String usuario, String senha) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		this.usuario = usuario;
		this.senha = senha;
	}
}
