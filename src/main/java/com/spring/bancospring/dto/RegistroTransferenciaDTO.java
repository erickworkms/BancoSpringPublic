package com.spring.bancospring.dto;

import com.spring.bancospring.controller.ContaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroTransferenciaDTO {

    private String destinatario;

    private String recebedor;

    private String data;

    private double valor;
}
