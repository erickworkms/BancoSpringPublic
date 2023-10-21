package com.spring.bancospring.dto;

import com.spring.bancospring.controller.ContaType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferenciaDTO {

    private String dataPagamento;

    private int numero;

    private String agencia;

    private ContaType tipoConta;

    private String cpf;

    private String valor;

}
