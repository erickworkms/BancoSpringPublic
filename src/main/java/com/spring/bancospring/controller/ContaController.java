package com.spring.bancospring.controller;

import com.spring.bancospring.dto.ContaDTO;
import com.spring.bancospring.model.Conta;
import com.spring.bancospring.service.ContaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contas")
public class ContaController{

    @Autowired
    private ContaService contaService;

    @PostMapping("/criarConta")
    public ResponseEntity<String> criarConta(@RequestBody String tipoConta,@RequestHeader("Authorization") String usuario) {
        return contaService.criarConta(tipoConta,usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conta> atualizaConta(@PathVariable(value = "id") UUID id, @RequestBody ContaDTO contaDTO) {
        return contaService.atualizaConta(id,contaDTO);
    }

     @DeleteMapping("/{id}")
    public String deletarConta(@PathVariable(value = "id") UUID id) {
        return contaService.deletarConta(id);
    }

    @GetMapping("/retornarcontas")
    public ResponseEntity<List<ContaDTO>> retornarContas(@RequestHeader("Authorization") String usuario) {
        var contaRetorno = contaService.retornarContas(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(contaRetorno);
    }
}
