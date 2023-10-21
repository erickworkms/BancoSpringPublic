package com.spring.bancospring.controller.exception;

import org.hibernate.ObjectNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ContaException extends RuntimeException{
    public void saldoInsuficiente(String mensagem) {
        System.out.println("Saldo insuficiente");
    }
    @ExceptionHandler(ObjectNotFoundException.class)
    public void objetoInvalido() {
        System.out.println("Objeto não encontrado");
    }
    @ExceptionHandler(NullPointerException.class)
    public void objetoNullo(NullPointerException objetoVazio) {
        System.out.println("Objeto está vazio" + objetoVazio.getMessage());
    }
    public void contaInvalida(String conta) {
        System.out.println("Conta "+ conta +" não encontrada");
    }
    public void usuarioInvalido(String usuario) {
        System.out.println("Usuário "+usuario+" não encontrado");
    }
}
