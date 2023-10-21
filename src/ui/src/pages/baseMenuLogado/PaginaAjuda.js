import React from 'react';

import HeaderVoltarMenuLogin from "../headers/HeaderVoltarMenuLogin";
import Footer from "../footers/Footer";
import {Col, Container, Form, Row} from "react-bootstrap";
import {VerificaLogin} from "../basePaginasPublicas/VerificaLogin";


const PaginaAjuda = () => {
    const corTextoBase = {
        marginRight: '20px',
        margin: '10px',
        textDecoration: 'none',
        color: '#539f5b',
        fontFamily: 'Arial Black, sans-serif',
        textAlign: 'left'
    };
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
                        <div style={{
                            backgroundColor: '#6fd067',
                            borderRadius: '10px'}}>
                            <div className="estiloFonte">
                                <Form.Label style={{fontSize: '30px', color: '#269432'}}>FAQ</Form.Label>
                            </div>
                            <div className="estiloFonte" style={{textAlign: 'center', color: '#539f5b'}}><Form.Label>Lista
                                com explicações das funcionalidades </Form.Label><br/></div>
                        </div>
                        <div style={{textAlign: 'left'}}>
                            <Form.Label style={corTextoBase}> - Conta - </Form.Label><Form.Label className="estiloFonte"
                                                                                                 style={{marginLeft: '100px'}}> Teste
                            funcionalidades de criação e escolha de tipos de contas </Form.Label><br/>
                            <Form.Label style={corTextoBase}> - Transferência - </Form.Label><Form.Label
                            className="estiloFonte" style={{marginLeft: '30px'}}>Teste a transfêrencia de valores em
                            contas diferentes </Form.Label><br/>
                            <Form.Label style={corTextoBase}> - Pagamento - </Form.Label><Form.Label
                            className="estiloFonte" style={{marginLeft: '55px'}}>Teste o pagamento de boleto e leitura
                            de valores </Form.Label><br/>
                            <Form.Label style={corTextoBase}> - Deposito - </Form.Label><Form.Label
                            className="estiloFonte" style={{marginLeft: '75px'}}>Teste o deposito de valores em contas
                            diferentes ou propria </Form.Label><br/>
                            <Form.Label style={corTextoBase}> - Atualizar Dados - </Form.Label><Form.Label
                            className="estiloFonte" style={{marginLeft: '15px'}}>Mude dados gravados após cadastrar suas
                            contas por este menu </Form.Label><br/>
                            <Form.Label style={corTextoBase}> - Extrato - </Form.Label><Form.Label
                            className="estiloFonte" style={{marginLeft: '85px'}}>Tire o extrato de ações feitas nas
                            contas</Form.Label><br/>
                            <Form.Label style={corTextoBase}> - Emprestimo - </Form.Label><Form.Label
                            className="estiloFonte" style={{marginLeft: '45px'}}>Registre emprestimos para uma
                            conta</Form.Label><br/>
                        </div>
                    </Col>
                </Row>
                <Footer/>
            </Container>
        </>
    )
}

export default PaginaAjuda;