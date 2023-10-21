package com.spring.bancospring.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;

import java.util.UUID;

@Data
@NoArgsConstructor(force = true)
@Entity
@Table(name = "CB_PAGAMENTOS")
public class RegistroPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "VARCHAR(200)")
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    private UUID registroPagamentos;
    @Column(columnDefinition = "VARCHAR(200)")
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    private UUID pagador;
    @Column
    private String dataPagamento;
    @Column
    private String codigoBarra;
    @Column
    private String valor;
    @Column
    private String dataVencimento;

    public RegistroPagamento(UUID pagador, String dataPagamento, String codigoBarra, String valor, String dataVencimento) {
        this.pagador = pagador;
        this.dataPagamento = dataPagamento;
        this.codigoBarra = codigoBarra;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
    }
}
