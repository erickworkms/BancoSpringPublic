package com.spring.bancospring.controller.security.token;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GeradorToken {
    private String usuario;
    private Date dataCriacaoToken;
    private Date dataExpiracaoToken;
    private List<String> regras;

    public void setRegras(List<String> regras) {
        this.regras = regras;
    }
}
