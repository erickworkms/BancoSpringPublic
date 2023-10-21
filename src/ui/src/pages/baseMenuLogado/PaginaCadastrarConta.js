import React, {useEffect, useState} from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import HeaderVoltarMenuLogin from "../headers/HeaderVoltarMenuLogin";
import Footer from "../footers/Footer";
import {Button, Col, Container, Form, Row} from "react-bootstrap";
import ListarContas from "../funcoes/ListarContas";
import {recebeAutorizacao} from "../autenticacao";
import {VerificaLogin} from "../basePaginasPublicas/VerificaLogin";

const PaginaCadastrarConta = () => {

    const [contas, setContas] = useState([]);

    const [tipoConta, setTipoConta] = useState('')

    const mudancaDadosCadastro = (event) => {

        const valorSelecionado = event.target.value;

        setTipoConta(valorSelecionado);
    };

    const submitDadosCadastro = async (event) => {
        event.preventDefault();

        const autenticacao = recebeAutorizacao();
        if(autenticacao){
            const configAut = {
                headers:{
                    Authorization: `${autenticacao}`
                }
            };
            const dadosParaEnviar = {
                tipoConta: tipoConta
            };
            try {

                const response = await axios.post('/contas/criarConta', tipoConta,configAut);


                console.log('Resposta do servidor:', response.data);
                console.log('Dados para enviar:', dadosParaEnviar);
                recebeAutorizacao(response.data)
            } catch (error) {

                alert('falha '+ tipoConta.tipoConta);
                console.error('Erro ao realizar a solicitação POST:', error);
            }
        }};
    VerificaLogin ();
    useEffect(() => {
        const autenticacao = recebeAutorizacao();
        if(autenticacao){
            const configAut = {
                headers:{
                    Authorization: `${autenticacao}`
                }
            };

            axios.get('/contas/retornarcontas',configAut)
                .then((response) => {
                    setContas(response.data);
                })
                .catch((error) => {
                    console.error('Erro ao buscar dados:', error);
                });
        }else {
            alert('falhou')
        }
    }, []);

    return (
        <>
            <Container className="estiloContainer">
                <HeaderVoltarMenuLogin/>
                <Row className="justify-content-center align-items-center"
                     style={{
                         minHeight: '80vh',
                         display: 'flex',
                         marginBottom: '0px',
                         backgroundColor: '#cee595'
                     }}>
                    <Col xs={12} md={6} className="text-center" style={{
                        backgroundColor: '#Cde6cb',
                        borderRadius: '10px',
                    }}>
                        <Form onSubmit={submitDadosCadastro}>
                            <div className="estiloFonte">
                                <Form.Label style={{fontSize: '30px',color:'#539f5b'}}>Criar Conta Bancaria</Form.Label>
                            </div>
                            <div style={{
                                backgroundColor: '#b2e8b9',
                                borderRadius: '10px',
                            }}>
                                <div className="estiloFonte">
                                    <Form.Label>
                                        <Form.Check type="radio" name="contas" value="0"
                                                    onChange={mudancaDadosCadastro} autoComplete="off"/> Conta Corrente
                                    </Form.Label>
                                </div>

                                <div className="estiloFonte">
                                    <Form.Label>
                                        <Form.Check type="radio" name="contas" value="1"
                                                    onChange={mudancaDadosCadastro} autoComplete="off"/> Conta Poupança
                                    </Form.Label>
                                </div>

                                <div className="estiloFonte">
                                    <Form.Label>
                                        <Form.Check type="radio" name="contas" value="2"
                                                    onChange={mudancaDadosCadastro} autoComplete="off"/> Conta Salario
                                    </Form.Label>
                                </div>
                            </div>

                            <div className="text-center">
                                <Button type={"submit"} className="w-100"
                                        style={{
                                            maxWidth: '200px',
                                            marginTop: '25px',
                                            marginBottom: '25px',
                                            backgroundColor: '#49be25',
                                            borderColor: '#49be25'
                                        }}>Criar</Button>
                            </div>
                        </Form>
                    </Col>
                    <Col xs={6} md={5} style={{
                        marginRight: '70px'
                    }}>
                        <ListarContas contas={contas}/>
                    </Col>
                </Row>
                <Footer/>
            </Container>
        </>
    )
}

export default PaginaCadastrarConta;
