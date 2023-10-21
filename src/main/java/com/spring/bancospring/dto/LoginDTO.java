package com.spring.bancospring.dto;

import com.spring.bancospring.controller.ContaType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class LoginDTO {

    private boolean verUsuarioLogado = false;

    private String usuario;

    private int numero;

    private String agencia;

    private double saldo;

    private UUID idCliente;

    private ContaType tipoConta;
}
