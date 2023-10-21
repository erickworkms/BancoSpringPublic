package com.spring.bancospring.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;


import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor // cria construtor vazio

@Entity
@Table(name = "CB_CLIENTE")
public class Cliente implements Serializable {

	//private static final long serialVersionUID = 1L;
	@Id
	@Serial
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_cliente",columnDefinition = "VARCHAR(200)")
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	private UUID idCliente;

	@Column(length = 100,nullable = false)
	private String nome="";
	@Column(length = 20,nullable = false)
	private String dataNascimento="";
	@Column(length = 11,nullable = false)
	private String cpf="";

	@Column(length = 50,nullable = false)
	private String endereco="";
	@Column(length = 50,nullable = false)
	private String telefone="";
	@Column(length = 50,nullable = false)
	private String email="";

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "tab_usuario_regras",joinColumns = @JoinColumn(name="id_cliente"))
	@Column(name = "acesso_id")
	private List<String> acesso = new ArrayList<>();

	@Column(length = 50,nullable = false)
	private String usuario="";

	@Column(length = 200,nullable = false)
	private String senha="";

	@Column(length = 200,nullable = false)
	private int contaEscolhida=0;

	public Cliente(String nome, String dataNascimento, String cpf, String endereco, String telefone, String email, List<String> acesso, String usuario, String senha) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		this.acesso = acesso;
		this.usuario = usuario;
		this.senha = senha;
	}
}
