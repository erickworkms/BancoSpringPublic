import React, {useState} from 'react';

import axios from "axios";
import HeaderVoltarMenuLogin from "../headers/HeaderVoltarMenuLogin";
import Footer from "../footers/Footer";
import {Button, Col, Container, Form, Row} from "react-bootstrap";
import {recebeAutorizacao} from "../autenticacao";
import {VerificaLogin} from "../basePaginasPublicas/VerificaLogin";


export function Extrato() {

    /////Verificações do extrato
    const [data, setData] = useState({
        dataInicial: '',
        dataFinal: ''
    });

    const [depositos, setDeposito] = useState([]);

    const [pagamentos, setPagamento] = useState([]);

    const [transferencias, setTransferencia] = useState([]);

    const verDataInicial = (event) => {
        setData({
            ...data,
            dataInicial: event.target.value,
        });
    };

    const verDataFinal = (event) => {
        setData({
            ...data,
            dataFinal: event.target.value,
        });
    };

    const submitDadosExtrato = async (event) => {
        event.preventDefault();

        const autenticacao = recebeAutorizacao();
        if (autenticacao) {
            const configAut = {
                headers: {
                    Authorization: `${autenticacao}`
                }
            };

            try {

                let response = await axios.post('/login/extratoDeposito', data, configAut);

                setDeposito(response.data);
                response = await axios.post('/login/extratoPagamento', data, configAut);
                setPagamento(response.data);
                response = await axios.post('/login/extratoTransferencia', data, configAut);
                setTransferencia(response.data);
            } catch (error) {
                console.error('Erro ao realizar a solicitação POST:', error);
            }
        }
    };

    VerificaLogin ();
    return (
        <Container className="estiloContainer">
            <HeaderVoltarMenuLogin/>
            <Row className="justify-content-center align-items-center"
                 style={{
                     minHeight: '80vh',
                     display: 'flex',
                     marginBottom: '0px',
                     backgroundColor: '#cee595',
                     alignContent: 'center',
                     justifyContent: 'center'
                 }}>
                <Col xs={12} md={6} className="text-center" style={{
                    marginRight: '50px',
                    margin: '10px',
                    textDecoration: 'none',
                    color: '#49be25',
                    fontFamily: 'Arial Black, sans-serif',
                    backgroundColor: '#Cde6cb',
                    borderRadius: '10px'
                }}>
                    <Form onSubmit={submitDadosExtrato}>
                        <div className="estiloFonte">
                            <Form.Label style={{fontSize: '30px'}}>Extrato</Form.Label>
                        </div>
                        <div className="estiloFonte justify-content-left">
                            <Form.Label>Escolha abaixo o periodo e tipo de conta para extrato bancário </Form.Label>
                            <div className="estiloFonte"
                                 style={{display: 'flex', flexDirection: 'column', alignItems: 'center'}}>
                                <Form.Label>Data de pagamento</Form.Label>
                                <Form.Control type={"date"} value={data.dataInicial} onChange={verDataInicial}
                                              style={{textAlign: 'center', maxWidth: '500px'}} autoComplete="off"/>
                                <Form.Label>Data Final : </Form.Label>
                                <Form.Control type={"date"} value={data.dataFinal} onChange={verDataFinal}
                                              style={{textAlign: 'center', maxWidth: '500px'}} autoComplete="off"/>
                            </div>
                            <div className="text-center">
                                <Button type={"submit"} className="w-100"
                                        style={{
                                            maxWidth: '200px',
                                            marginTop: '25px',
                                            marginBottom: '25px',
                                            backgroundColor: '#49be25',
                                            borderColor: '#49be25'
                                        }}>Buscar</Button>
                            </div>
                        </div>
                    </Form>
                </Col>
                <Col xs={6} md={5} style={{
                    marginRight: '70px'
                }}>

                    <div style={{
                        padding: '20px',
                        border: '1px solid #ccc',
                        backgroundColor: '#f8f9fa',
                        minHeight: '500px',
                        borderRadius: '15px',
                        fontSize:'8px',
                        maxWidth: '800px',
                        margin: '0 auto'
                    }}>
                        <h1 className="estiloFonte">DEPOSITOS</h1>
                        {Array.isArray(depositos) && depositos.map((deposito) => (
                            <div key={deposito.id}>
                                <p className="estiloFonte">
                                    Data de depósito : {deposito.dataDeposito}
                                </p>
                                <p className="estiloFonte">
                                    Conta : {deposito.numeroConta}
                                </p>
                                <p className="estiloFonte">
                                    Agencia : {deposito.agencia}
                                </p>
                                <p className="estiloFonte">
                                    Tipo de conta : {deposito.tipoConta}
                                </p>
                                <p className="estiloFonte">
                                    Cpf : {deposito.cpf}
                                </p>
                                <p className="estiloFonte">
                                    Depositante : {deposito.depositante}
                                </p>
                                <p className="estiloFonte">
                                    Recebedor : {deposito.recebedor}
                                </p>
                                <p className="estiloFonte">
                                    Valor : {deposito.valor}
                                </p>
                            </div>
                        ))}


                        <h1 className="estiloFonte">TRANSFERENCIAS</h1>
                        {Array.isArray(transferencias) && transferencias.map((transferencia) => (
                            <div key={transferencia.id}>
                                <p className="estiloFonte">
                                    Destinatário : {transferencia.destinatario}
                                </p>
                                <p className="estiloFonte">
                                    Recebedor : {transferencia.recebedor}
                                </p>
                                <p className="estiloFonte">
                                    Data de transferência  : {transferencia.data}
                                </p>
                                <p className="estiloFonte">
                                    Valor : {transferencia.valor}
                                </p>
                            </div>
                        ))}

                        <h1 className="estiloFonte">PAGAMENTOS</h1>
                        {Array.isArray(pagamentos) && pagamentos.map((pagamento) => (
                            <div key={pagamento.id}>
                                <p className="estiloFonte">
                                    Data de pagamento : {pagamento.dataPagamento}
                                </p>
                                <p className="estiloFonte">
                                    Código de barras : {pagamento.codigoBarra}
                                </p>
                                <p className="estiloFonte">
                                    Valor : {pagamento.valor}
                                </p>
                                <p className="estiloFonte">
                                    Data de vencimento : {pagamento.dataVencimento}
                                </p>
                            </div>
                        ))}

                    </div>

                </Col>
            </Row>
            <Footer/>
        </Container>
    );
}
