package com.spring.bancospring.model;

import com.spring.bancospring.controller.ContaType;
import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.JdbcTypeCode;


import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor(force = true)
@Table(name = "CB_CONTA")
public class Conta implements Serializable {


	//private static final long serialVersionUID = 1L;
	@Id
	@Serial
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_conta",columnDefinition = "VARCHAR(200)")
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	private UUID idConta;
	@Column(name = "id_cliente",columnDefinition = "VARCHAR(200)")
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	private UUID idCliente;
	@Column(length = 7,nullable = false)
	private String agencia;
	@Serial
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int numero;
	@Column
	private double saldo;
	@Column
	private double emprestimo;
	@Column
	private ContaType tipoConta;

	public Conta(UUID idCliente, String agencia, int numero, double saldo, double emprestimo, ContaType tipoConta) {
		this.idCliente = idCliente;
		this.agencia = agencia;
		this.numero = numero;
		this.saldo = saldo;
		this.emprestimo = emprestimo;
		this.tipoConta = tipoConta;
	}
}
