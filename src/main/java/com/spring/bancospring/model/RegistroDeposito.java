package com.spring.bancospring.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;

import java.util.UUID;

@Data
@NoArgsConstructor(force = true)
@Entity
@Table(name = "CB_DEPOSITOS")
public class RegistroDeposito {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "VARCHAR(200)")
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    private UUID registroDepositosAntigos;
    @Column(columnDefinition = "VARCHAR(200)")
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    private UUID depositante;
    @Column(columnDefinition = "VARCHAR(200)")
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    private UUID recebedor;
    @Column
    private String data="";
    @Column
    private double valor=0;

    public RegistroDeposito(UUID destinatario, UUID recebedor, String data, double valor) {
        this.depositante = destinatario;
        this.recebedor = recebedor;
        this.data = data;
        this.valor = valor;
    }
}
