package com.spring.bancospring.service;

import com.spring.bancospring.controller.ContaType;
import com.spring.bancospring.controller.security.token.GeradorJWTToken;
import com.spring.bancospring.controller.security.token.GeradorToken;
import com.spring.bancospring.dto.*;
import com.spring.bancospring.model.*;
import com.spring.bancospring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private DepositoRepository depositoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Value("${security.config.expiration}")
    private Long configSegurancaToken;

    @Autowired
    private PasswordEncoder codificador;

    @Autowired
    GeradorJWTToken verificaToken;

    public Cliente criaUsuario(ClienteDTO usuario) {
        Cliente cliente = clienteRepository.findByUsuario(usuario.getUsuario());

        if (cliente == null && !clienteRepository.existsByCpf(usuario.getCpf()) &&
                !clienteRepository.existsByUsuario(usuario.getUsuario())) {

            int contagemContas = contaRepository.countAllContas();
            boolean checaDisponibilidade = false;

            while (!checaDisponibilidade) {
                if (contaRepository.existsByNumero(contagemContas)) {
                    contagemContas++;
                } else {
                    checaDisponibilidade = true;
                }
            }

            cliente = new Cliente(
                    usuario.getNome(),
                    usuario.getDataNascimento(),
                    usuario.getCpf(),
                    usuario.getEndereco(),
                    usuario.getTelefone(),
                    usuario.getEmail(),
                    new ArrayList<>(),
                    usuario.getUsuario(),
                    codificador.encode(usuario.getSenha())
            );

            cliente.setContaEscolhida(contagemContas);
            cliente.getAcesso().add("USUARIO");
            clienteRepository.save(cliente);
            String recebeIdCliente = clienteRepository.findIdByUsuario(cliente.getUsuario());


            contaRepository.save(new Conta(
                    UUID.fromString(recebeIdCliente),
                    "A3609-9",
                    contagemContas,
                    0,
                    0,
                    ContaType.CONTAPOUPANCA));
            return cliente;
        } else {

            return null;
        }

    }

    public ResponseEntity<String> verLoginUsuario(String nome, String senha) {
        Authentication autenticacao = SecurityContextHolder.getContext().getAuthentication();
        if (autenticacao != null && autenticacao.isAuthenticated()) {
            // return ResponseEntity.status(HttpStatus.OK).body("usuario ja esta autenticado " + autenticacao.getCredentials());
            //System.out.println(autenticacao.getPrincipal().toString());
            System.out.println("Usuário autenticado com sucesso");
        }
        Cliente cliente = clienteRepository.findByUsuario(nome);
        System.out.println(cliente.toString());
        if (cliente != null) {
            boolean passwordOk = codificador.matches(senha, cliente.getSenha());
            if (!passwordOk) {
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Senha inválida para o login: " + nome);
            }
            //Estamos enviando um objeto Sessão para retornar mais informações do usuário
            GeradorToken clienteToken = new GeradorToken();
            clienteToken.setDataCriacaoToken(new Date(System.currentTimeMillis()));

            clienteToken.setDataExpiracaoToken((new Date(System.currentTimeMillis() + configSegurancaToken)));

            clienteToken.setRegras(cliente.getAcesso());
            clienteToken.setUsuario(cliente.getUsuario());

            return ResponseEntity.status(HttpStatus.OK).body(verificaToken.criaToken(clienteToken));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao tentar fazer login, verifique seu usuário e senha");
        }
    }

    public ResponseEntity<LoginDTO> verDadosLogin(String usuario) {

        String nomeUsuario = verificaToken.verificaToken(usuario.replace("Bearer ", ""));
        Cliente recebeCliente = DetectaClientes(usuario);

        Optional<Conta> recebeDadosConta = contaRepository.findByNumero(recebeCliente.getContaEscolhida());

        LoginDTO loginDTO = new LoginDTO(true,
                nomeUsuario,
                recebeDadosConta.get().getNumero(),
                recebeDadosConta.get().getAgencia(),
                recebeDadosConta.get().getSaldo(),
                recebeDadosConta.get().getIdCliente(),
                recebeDadosConta.get().getTipoConta());

        return ResponseEntity.status(HttpStatus.OK).body(loginDTO);
    }

    public ResponseEntity<String> verContaEscolhida(String numeroConta, String usuario) {

        String nomeUsuario = verificaToken.verificaToken(usuario.replace("Bearer ", ""));
        Cliente clienteUpdate = clienteRepository.findByUsuario(nomeUsuario);
        clienteUpdate.setContaEscolhida(Integer.parseInt(numeroConta.replace("=", "")));

        clienteRepository.save(clienteUpdate);

        return ResponseEntity.status(HttpStatus.OK).body("Conta escolhida atualizada");
    }

    public ResponseEntity<String> verTransferencia(TransferenciaDTO transferenciaDTO, String usuario) {

        Cliente usuarioTransferencia = DetectaClientes(usuario);

        Optional<Conta> contaTransferidor = contaRepository.findIdByNumeroAndIdCliente(usuarioTransferencia.getContaEscolhida(), usuarioTransferencia.getIdCliente());
        Optional<Conta> contaRecebedor = contaRepository.findIdByNumeroAndAgencia(transferenciaDTO.getNumero(), transferenciaDTO.getAgencia());

        double valorDouble = ConverteSaldo(transferenciaDTO.getValor());

        contaTransferidor.get().setSaldo(
                contaTransferidor.get().getSaldo() - valorDouble);

        contaRecebedor.get().setSaldo(contaRecebedor.get().getSaldo() + valorDouble);

        RegistroTransferencia registro = new RegistroTransferencia(
                contaTransferidor.get().getIdConta(),
                contaRecebedor.get().getIdConta(),
                transferenciaDTO.getDataPagamento(),
                valorDouble);

        transferenciaRepository.save(registro);

        contaRepository.save(contaTransferidor.get());
        contaRepository.save(contaRecebedor.get());

        return ResponseEntity.status(HttpStatus.OK).body("Transferencia realizada com sucesso");
    }

    private static double ConverteSaldo(String valorTransferencia) {

        String valorNumericoStr = valorTransferencia.replaceAll("[^\\d,.]", "");

        valorNumericoStr = valorNumericoStr.replaceFirst("\\.", "").replace(",", ".");

        return Double.parseDouble(valorNumericoStr);
    }

    public ResponseEntity<String> verPagamento(PagamentoDTO pagamentoDTO, String usuario) {

        Cliente usuarioPagamento = DetectaClientes(usuario);

        Optional<Conta> contaPagador =
                contaRepository.findIdByNumeroAndIdCliente(
                        usuarioPagamento.getContaEscolhida(),
                        usuarioPagamento.getIdCliente());

        double valorDouble =ConverteSaldo(pagamentoDTO.getValor());

        contaPagador.get().setSaldo(contaPagador.get().getSaldo() - valorDouble);

        RegistroPagamento registroPagamento = new RegistroPagamento(
                usuarioPagamento.getIdCliente(),
                pagamentoDTO.getDataPagamento(),
                pagamentoDTO.getCodigoBarra(),
                pagamentoDTO.getValor(),
                pagamentoDTO.getDataVencimento());

        pagamentoRepository.save(registroPagamento);
        contaRepository.save(contaPagador.get());

        return ResponseEntity.status(HttpStatus.OK).body("Pagamento realizado com sucesso");
    }

    public ResponseEntity<String> verDeposito(DepositoDTO depositoDTO, String usuario) {

        Cliente usuarioPagamento = DetectaClientes(usuario);

        Optional<Conta> contaDepositante = contaRepository.findByNumero(usuarioPagamento.getContaEscolhida());
        Optional<Conta> contaRecebedor =
                contaRepository.findIdByNumeroAndAgencia(Integer.parseInt(depositoDTO.getNumeroConta()), depositoDTO.getAgencia());

        double valorDouble = ConverteSaldo(depositoDTO.getValor());

        contaRecebedor.get().setSaldo(contaRecebedor.get().getSaldo() + valorDouble);

        contaRepository.save(contaRecebedor.get());

        RegistroDeposito registroDeposito = new RegistroDeposito(
                contaDepositante.get().getIdConta(),
                contaRecebedor.get().getIdConta(),
                depositoDTO.getDataDeposito(),
                valorDouble);

        depositoRepository.save(registroDeposito);

        return ResponseEntity.status(HttpStatus.OK).body("Deposito realizado com sucesso");
    }

    public ResponseEntity<String> verEmprestimo(EmprestimoDTO emprestimoDTO, String usuario) {

        Cliente usuarioEmprestimo = DetectaClientes(usuario);

        Optional<Conta> contaRecebedor =
                contaRepository.findIdByNumeroAndIdCliente(usuarioEmprestimo.getContaEscolhida(), usuarioEmprestimo.getIdCliente());

        String valorNumericoStr = emprestimoDTO.getValor().replaceAll("[^\\d,.]", "");

        valorNumericoStr = valorNumericoStr.replaceFirst("\\.", "").replace(",", ".");


        double valorDouble = ConverteSaldo(emprestimoDTO.getValor());

        contaRecebedor.get().setSaldo(contaRecebedor.get().getSaldo() + valorDouble);
        contaRecebedor.get().setEmprestimo(valorDouble);

        contaRepository.save(contaRecebedor.get());

        return ResponseEntity.status(HttpStatus.OK).body("Emprestimo realizado com sucesso");
    }

    public ResponseEntity<ClienteDTO> verDadosCadastro(String usuario) {

        Cliente usuarioBusca = DetectaClientes(usuario);

        ClienteDTO dadosCliente = new ClienteDTO(
                usuarioBusca.getNome(),
                usuarioBusca.getDataNascimento(),
                usuarioBusca.getCpf(),
                usuarioBusca.getEndereco(),
                usuarioBusca.getTelefone(),
                usuarioBusca.getEmail(),
                usuarioBusca.getUsuario(),
                ""
        );
        return ResponseEntity.status(HttpStatus.OK).body(dadosCliente);
    }

    public ResponseEntity<String> verAtualizaCadastro(ClienteDTO cliente, String usuario) {

        Cliente usuarioCadastro = DetectaClientes(usuario);

        usuarioCadastro.setNome(cliente.getNome());
        usuarioCadastro.setDataNascimento(cliente.getDataNascimento());
        usuarioCadastro.setCpf(cliente.getCpf());
        usuarioCadastro.setEndereco(cliente.getEndereco());
        usuarioCadastro.setTelefone(cliente.getTelefone());
        usuarioCadastro.setEmail(cliente.getEmail());
        usuarioCadastro.setUsuario(cliente.getUsuario());
        usuarioCadastro.setSenha(codificador.encode(cliente.getSenha()));

        clienteRepository.save(usuarioCadastro);

        return ResponseEntity.status(HttpStatus.OK).body("Cadastro atualizado com sucesso");
    }

    public ResponseEntity<List<RegistroDepositoDTO>> verListaDepositos(DataTransacoesDTO dataTransacoesDTO, String usuario) {

        Cliente usuarioDeposito = DetectaClientes(usuario);

        Optional<Conta> verContaDepositante = contaRepository.findByNumero(usuarioDeposito.getContaEscolhida());

        Optional<List<RegistroDeposito>> registroDepositos =
                depositoRepository.findByDataBetweenAndRecebedorOrDepositante(
                        dataTransacoesDTO.getDataInicial(),
                        dataTransacoesDTO.getDataFinal(),
                        verContaDepositante.get().getIdConta(),
                        verContaDepositante.get().getIdConta());

        List<RegistroDepositoDTO> depositos = registroDepositos.get().stream().
                map(registroDeposito -> {

                    RegistroDepositoDTO depositoConversor = new RegistroDepositoDTO();

                    Optional<Conta> contaDepositante = contaRepository.findByIdConta(registroDeposito.getDepositante());
                    Optional<Conta> contaRecebedor = contaRepository.findByIdConta(registroDeposito.getRecebedor());

                    Cliente registroDepositanteCliente = clienteRepository.findById(contaDepositante.get().getIdCliente()).get();

                    Cliente registroRecebedorCliente = clienteRepository.findById(contaRecebedor.get().getIdCliente()).get();

                    depositoConversor.setDataDeposito(registroDeposito.getData());
                    depositoConversor.setNumeroConta(String.valueOf(contaRecebedor.get().getNumero()));
                    depositoConversor.setAgencia(contaRecebedor.get().getAgencia());
                    depositoConversor.setTipoConta(contaRecebedor.get().getTipoConta().toString());
                    depositoConversor.setCpf(registroRecebedorCliente.getCpf());
                    depositoConversor.setDepositante(registroDepositanteCliente.getNome());
                    depositoConversor.setRecebedor(registroRecebedorCliente.getNome());
                    depositoConversor.setValor(String.valueOf(registroDeposito.getValor()));

                    return depositoConversor;
                }).toList();


        return ResponseEntity.status(HttpStatus.OK).body(depositos);
    }

    public ResponseEntity<List<PagamentoDTO>> verListaPagamentos(DataTransacoesDTO dataTransacoesDTO, String usuario) {

        Cliente usuarioDeposito = DetectaClientes(usuario);

        Optional<List<RegistroPagamento>> pagamentosRegistro = pagamentoRepository.findByDataPagamentoBetweenAndPagador(
                dataTransacoesDTO.getDataInicial(),
                dataTransacoesDTO.getDataFinal(),
                usuarioDeposito.getIdCliente());

        List<PagamentoDTO> pagamentos = pagamentosRegistro.get().stream().
                map(registroPagamento -> {
                    PagamentoDTO pagamentoConversor = new PagamentoDTO();
                    pagamentoConversor.setDataPagamento(registroPagamento.getDataPagamento());
                    pagamentoConversor.setCodigoBarra(registroPagamento.getCodigoBarra());
                    pagamentoConversor.setValor(registroPagamento.getValor());
                    pagamentoConversor.setDataVencimento(registroPagamento.getDataVencimento());
                    return pagamentoConversor;
                }).toList();

        return ResponseEntity.status(HttpStatus.OK).body(pagamentos);
    }

    public ResponseEntity<List<RegistroTransferenciaDTO>> verListaTransferencias(DataTransacoesDTO dataTransacoesDTO, String usuario) {

        Cliente usuarioDeposito = DetectaClientes(usuario);

        Optional<Conta> destinatarioConta = contaRepository.findByNumero(usuarioDeposito.getContaEscolhida());

        Optional<List<RegistroTransferencia>> registroTransferencias = transferenciaRepository.findByDataBetweenAndRecebedorOrDestinatario(
                dataTransacoesDTO.getDataInicial(),
                dataTransacoesDTO.getDataFinal(),
                destinatarioConta.get().getIdConta(),
                destinatarioConta.get().getIdConta());

        List<RegistroTransferenciaDTO> transferencias = registroTransferencias.get().stream().
                map(registroTransferencia -> {
                    RegistroTransferenciaDTO transferenciaConversor = new RegistroTransferenciaDTO();

                    Optional<Conta> contaDestinatario = contaRepository.findByIdConta(registroTransferencia.getDestinatario());
                    Optional<Cliente> dadosDestinatario = clienteRepository.findById(contaDestinatario.get().getIdCliente());

                    Optional<Conta> contaRecebedor = contaRepository.findByIdConta(registroTransferencia.getRecebedor());
                    Optional<Cliente> dadosRecebedor = clienteRepository.findById(contaRecebedor.get().getIdCliente());

                    transferenciaConversor.setDestinatario(dadosDestinatario.get().getNome());
                    transferenciaConversor.setRecebedor(dadosRecebedor.get().getNome());
                    transferenciaConversor.setData(registroTransferencia.getData());
                    transferenciaConversor.setValor(registroTransferencia.getValor());

                    return transferenciaConversor;
                }).toList();

        return ResponseEntity.status(HttpStatus.OK).body(transferencias);
    }

    private Cliente DetectaClientes(String usuario) {
        String nomeUsuario = verificaToken.verificaToken(usuario.replace("Bearer ", ""));
        return clienteRepository.findByUsuario(nomeUsuario);
    }
}

