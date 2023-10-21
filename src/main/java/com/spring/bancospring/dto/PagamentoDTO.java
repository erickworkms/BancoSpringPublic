package com.spring.bancospring.dto;

import com.spring.bancospring.controller.ContaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoDTO {

    private String dataPagamento;

    private String codigoBarra;

    private String valor;

    private String dataVencimento;
}
