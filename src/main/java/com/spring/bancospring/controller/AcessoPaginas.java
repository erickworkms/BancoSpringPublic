package com.spring.bancospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AcessoPaginas {

    @GetMapping({"/","/Login","/logout","/Cadastrar","/sessao",
            "/transferencia","/criarConta","/extrato","/pagamento",
            "/deposito","/atualizaCadastro","/emprestimo","/ajuda"})
    public String index() {
        return "forward:index.html";
    }
}
