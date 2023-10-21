package com.spring.bancospring.service;

import com.spring.bancospring.controller.ContaType;
import com.spring.bancospring.controller.security.token.GeradorJWTToken;
import com.spring.bancospring.dto.ContaDTO;
import com.spring.bancospring.model.Conta;
import com.spring.bancospring.repository.ClienteRepository;
import com.spring.bancospring.repository.ContaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContaService{

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    GeradorJWTToken verificaToken;

    public ResponseEntity<String> criarConta(String tipoConta,String usuario)
    {
        String nomeUsuario = verificaToken.verificaToken(usuario.replace("Bearer ", ""));
        String recebeIdCliente = clienteRepository.findIdByUsuario(nomeUsuario);

        Optional<Conta> recebeDadosConta = contaRepository.findFirstByIdCliente(UUID.fromString(recebeIdCliente));

        ContaType contaEscolhida = switch (Integer.parseInt(tipoConta.replace("=",""))) {
            case 0 -> ContaType.CONTACORRENTE;
            case 2 -> ContaType.CONTASALARIO;
            default -> ContaType.CONTAPOUPANCA;
        };

        contaRepository.save(new Conta(
                UUID.fromString(recebeIdCliente),
                "A3609-9",
                contaRepository.countAllContas()+1,
                0,
                0,
                contaEscolhida));

        return ResponseEntity.status(HttpStatus.OK).body("Conta criada com sucesso");
    }


    @Transactional
    public String deletarConta(UUID id)
    {
        var conta = new Conta();
        var contaDTO = contaRepository.findById(id);

        if(contaDTO.isPresent()){

            BeanUtils.copyProperties(contaDTO.get(),conta);

            contaRepository.delete(conta);

        }else{
            return "Conta não encontrada na base de dados";
        }
        return "Conta deletada com sucesso";
    }

    public List<ContaDTO> retornarContas(String usuario) {

        String nomeUsuario = verificaToken.verificaToken(usuario.replace("Bearer ", ""));
        String recebeIdCliente = clienteRepository.findIdByUsuario(nomeUsuario);

        var listaContasDTO = new ArrayList<ContaDTO>(); // Cria um objeto do tipo DTO
        contaRepository.findByIdCliente(UUID.fromString(recebeIdCliente)). // Faz a busca do tipo cliente e converte para um objeto do tipo DTO
                stream().
                forEach(conta -> {
                    var contaDTO = new ContaDTO(null,"A360-9",0,0,null);
                    BeanUtils.copyProperties(conta, contaDTO); // Faz a conversão do tipo cliente/
                    // clienteDTO, mas só funciona se tiver os dois tipos com atributos iguais, ex: nome:nome etc.
                    listaContasDTO.add(contaDTO);//Adiciona na variavel o resultado da conversão
                });
        return listaContasDTO; // retorna o tipo convertido no metodo
    }

    public ResponseEntity<Conta> atualizaConta(UUID id, ContaDTO contaDTO) {

        var conta = new Conta();
        contaDTO.setIdCliente(id);
        var contaDetectada = contaRepository.findById(id);
        if(contaDetectada.isPresent()){
            BeanUtils.copyProperties(contaDTO,conta);

            contaRepository.save(conta);
            return ResponseEntity.status(HttpStatus.OK).body(conta);
        }else {
            return null;
        }
    }
}
