import React, {useEffect, useState} from "react";
import {Link} from 'react-router-dom';
import 'react-datepicker/dist/react-datepicker.css';
import axios from "axios";
import {DadosContas} from "../funcoes/PaginaConta";
import { useNavigate } from 'react-router-dom';
import {Col, Container, Row} from "react-bootstrap";
import Footer from "../footers/Footer";

import LinksImagensRedondas from "../funcoes/LinksImagensRedondas";
import HeaderLogout from "../headers/HeaderLogout";
import {recebeAutorizacao} from "../autenticacao";
import {removeToken} from "../autenticacao";

const TelaInicialUsuarioLogado = () => {
    const history = useNavigate();

    const [dadosLogin, setDadosLogin] = useState({
        usuario: '',
        numero: '',
        agencia:'',
        tipo:'',
        saldo:''
    });

    useEffect(() => {
        const autenticacao = recebeAutorizacao();
        if(autenticacao){
            const configAut = {
                headers:{
                    Authorization: `${autenticacao}`
                }
            };
        // Faça a solicitação para o endpoint no componentDidMount ou useEffect
        axios.get('/login/verDadoslogin',configAut)
            .then((response) => {
                // Atualize o estado com os dados recebidos do backend
                if (response.status === 200 && response.data != null) {
                    setDadosLogin(response.data);
                }else {
                    alert('Sessao encerrada:' +response);
                    console.error('Erro ao buscar dados:', response);
                    removeToken();
                    history('/');
                }
            })
            .catch((error) => {
                // Lide com erros, se necessário
                alert('Sessao encerrada:' +error);
                removeToken();
                console.error('Erro ao buscar dados:', error);
                history('/');
            });
    }else {
            alert('Sessao encerrada');
            removeToken();
            console.error('Erro ao buscar dados');
            history('/');
        }
    }, []);

    const estilosDivBaseDir = {
        marginLeft: '200px',
        margin: '10px',
        textDecoration: 'none',
        color: '#49be25',
        fontFamily: 'Arial Black, sans-serif'
    };
    const estilosDivBaseEsq = {
        marginRight: '50px',
        margin: '10px',
        textDecoration: 'none',
        color: '#49be25',
        fontFamily: 'Arial Black, sans-serif',
        backgroundColor:'#Cde6cb',
        borderRadius: '10px'
    };

    //Inicio Pagina Html
    return (
        <Container className="estiloContainer">
            <HeaderLogout/>
            <Row className="justify-content-center align-items-center"
                 style={{
                     minHeight: '80vh',
                     display: 'flex',
                     marginBottom: '0px',
                     backgroundColor: '#cee595'
                 }}>
                <Col xs={12} md={6}  style={estilosDivBaseEsq}>
                    <div>
                        <DadosContas
                            nomeUsuario={dadosLogin.usuario}
                            numeroConta={dadosLogin.numero}
                            agencia={dadosLogin.agencia}
                            tipoConta={dadosLogin.tipoConta}
                            saldo={dadosLogin.saldo}
                        />
                    </div>
                </Col>
                <Col xs={6} md={5} style={{
                    marginRight: '70px'
                }}>
                    <div className="d-flex">
                        <LinksImagensRedondas
                            linkEndereco="/criarConta"
                            imagemEndereco={require('../imagens/Conta.png')}
                            tamanhoMax={"60px"}
                            margemTop={"15px"}
                            descricao = "Conta"
                        />
                        <LinksImagensRedondas
                            linkEndereco="/transferencia"
                            imagemEndereco={require('../imagens/transferenciaLogo.png')}
                            tamanhoMax={"60px"}
                            margemTop={"25px"}
                            descricao = "Transfêrencia"
                        />
                        <LinksImagensRedondas
                            linkEndereco="/pagamento"
                            imagemEndereco={require('../imagens/pagamentoLogo.png')}
                            tamanhoMax={"60px"}
                            margemTop={"20px"}
                            descricao = "Pagamento"
                        />
                        <LinksImagensRedondas
                            linkEndereco="/deposito"
                            imagemEndereco={require('../imagens/depositoLogo.png')}
                            tamanhoMax={"60px"}
                            margemTop={"5px"}
                            descricao = "Deposito"
                        />
                    </div>

                    <div className="d-flex">
                        <LinksImagensRedondas
                            linkEndereco="/atualizaCadastro"
                            imagemEndereco={require('../imagens/verificaPose.png')}
                            tamanhoMax={"60px"}
                            margemTop={"15px"}
                            descricao = "Atualizar Dados"
                        />
                        <LinksImagensRedondas
                            linkEndereco="/extrato"
                            imagemEndereco={require('../imagens/enviarDinheiro.png')}
                            tamanhoMax={"40px"}
                            margemTop={"10px"}
                            descricao = "Extrato"
                        />
                        <LinksImagensRedondas
                            linkEndereco="/emprestimo"
                            imagemEndereco={require('../imagens/emprestimo.png')}
                            tamanhoMax={"60px"}
                            margemTop={"15px"}
                            descricao = "Emprestimo"
                        />
                        <LinksImagensRedondas
                            linkEndereco="/ajuda"
                            imagemEndereco={require('../imagens/AjudaLogo.png')}
                            tamanhoMax={"60px"}
                            margemTop={"15px"}
                            descricao = "Ajuda"
                        />
                    </div>
                </Col>
            </Row>
            <Footer/>
        </Container>
    )
}
export default TelaInicialUsuarioLogado