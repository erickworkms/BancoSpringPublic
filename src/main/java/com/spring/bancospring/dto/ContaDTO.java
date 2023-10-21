package com.spring.bancospring.dto;

import com.spring.bancospring.controller.ContaType;
import lombok.*;
import org.springframework.lang.Nullable;

import java.util.UUID;

@Data
@AllArgsConstructor
//@Builder
public class ContaDTO {


    private UUID idCliente;

    private String agencia="A360-9";

    private int numero=0;

    private double saldo=0;

    private ContaType tipoConta=ContaType.CONTACORRENTE;
}
