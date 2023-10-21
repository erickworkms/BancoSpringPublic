package com.spring.bancospring.controller;

public enum ContaType {

    CONTACORRENTE(0,"nome"),

    CONTAPOUPANCA(6.75,""),

    CONTASALARIO(0,"");

    private double rendimento;

    private String nome;

    ContaType(double TipoConta,String nome) {
        this.nome= nome;
        this.rendimento = TipoConta;
    }

    public double getRendimento(){
        return rendimento;
    }
}
