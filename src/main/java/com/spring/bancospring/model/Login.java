package com.spring.bancospring.model;

import com.spring.bancospring.controller.ContaType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CB_LOGIN")
public class Login implements Serializable {

    private boolean verUsuarioLogado = false;

    @Id
    @Serial
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private int idCliente;

    @Column(length = 10,nullable = false)
    private String usuario="";

    @Column(length = 15,nullable = false)
    private String senha="";
    @Column
    private ContaType tipoConta;
}
