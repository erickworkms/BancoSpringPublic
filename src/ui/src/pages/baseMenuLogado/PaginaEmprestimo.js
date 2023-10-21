import React, {useState} from 'react';
import axios from "axios";
import HeaderVoltarMenuLogin from "../headers/HeaderVoltarMenuLogin";
import Footer from "../footers/Footer";
import {Button, Col, Container, Form, Row} from "react-bootstrap";
import {recebeAutorizacao} from "../autenticacao";
import {VerificaLogin} from "../basePaginasPublicas/VerificaLogin";


const PaginaEmprestimo = () => {

    const [emprestimo, setEmprestimo] = useState({
        dataPagamento: '',
        valor: '',
    });

    const verDataPagamento = (event) => {
        setEmprestimo({
            ...emprestimo,
            dataPagamento: event.target.value
        });
    };

    const submitDadosEmprestimo = async (event) => {
        event.preventDefault();

        const autenticacao = recebeAutorizacao();
        if (autenticacao) {
            const configAut = {
                headers: {
                    Authorization: `${autenticacao}`
                }
            };

            try {
                // Realize a solicitação POST com Axios
                const response = await axios.post('/login/emprestimo', emprestimo,configAut);

                // Faça algo com a resposta (se necessário)
                console.log('Resposta do servidor:', response.data);
                alert('Emprestimo realizado com sucesso');
            } catch (error) {
                // Trate erros, se ocorrerem
                console.error('Erro ao realizar a solicitação POST:', error);
            }
        }}
        ;

        const formatarMoeda = (value) => {
            // Remove todos os caracteres não numéricos
            const numeroLimpo = value.replace(/\D/g, '');

            // Formata o número como uma moeda
            return (numeroLimpo / 100).toLocaleString('pt-BR', {
                style: 'currency',
                currency: 'BRL',
            });
        };

        const mudaValor = (evento) => {
            const {name, value} = evento.target;

            setEmprestimo({...emprestimo, [name]: value.slice(0, -1)});
            if (evento.target.value.length <= 13) {
                setEmprestimo({
                    ...emprestimo,
                    [name]: formatarMoeda(value),
                });
            }
        }
    VerificaLogin ();
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
                        <Col xs="auto" className="text-center" style={{
                            backgroundColor: '#Cde6cb',
                            borderRadius: '10px'
                        }}>
                            <Form onSubmit={submitDadosEmprestimo}>

                                <div className="estiloFonte">
                                    <Form.Label style={{fontSize: '30px'}}>Emprestimo</Form.Label>
                                </div>
                                <div className="estiloFonte justify-content-left">
                                    <Form.Label>Escolha abaixo a data para o agendar o emprestimo </Form.Label>
                                </div>
                                <div className="estiloFonte">
                                    <Form.Label>Data Pagamento : </Form.Label>
                                    <Form.Control type={"date"} value={emprestimo.dataPagamento} onChange={verDataPagamento}
                                                  style={{textAlign: 'center'}} autoComplete="off"/>
                                </div>
                                <div className="estiloFonte">
                                    <Form.Label>Valor : </Form.Label>
                                    <Form.Control type={"text"} id="valor" name="valor" value={emprestimo.valor}
                                                  onChange={mudaValor} style={{textAlign: 'center'}}
                                                  autoComplete="off"/>
                                </div>
                                <div className="text-center">
                                    <Button type={"submit"} className="w-100"
                                            style={{
                                                maxWidth: '200px',
                                                marginTop: '25px',
                                                marginBottom: '25px',
                                                backgroundColor: '#49be25',
                                                borderColor: '#49be25'
                                            }}>Pagar</Button>
                                </div>
                            </Form>
                        </Col>
                    </Row>
                    <Footer/>
                </Container>
            </>
        )
    }

    export default PaginaEmprestimo;