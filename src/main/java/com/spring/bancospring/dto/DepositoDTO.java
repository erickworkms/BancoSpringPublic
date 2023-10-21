package com.spring.bancospring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositoDTO {

    private String dataDeposito;

    private String numeroConta;

    private String agencia;

    private String tipoConta;

    private String cpf;

    private String valor;
}
