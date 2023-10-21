import React from 'react';
import {Col, Container, Row} from "react-bootstrap";


/////Inicio da verificação de dados da conta
export function DadosContas({numeroConta, agencia, tipoConta, nomeUsuario,saldo}) {
    const corTextoBase = {
        marginRight: '20px',
        margin: '10px',
        textDecoration: 'none',
        color: '#539f5b',
        fontFamily: 'Arial Black, sans-serif',
    };

    const corTextoResult = {
        marginRight: '20px',
        margin: '10px',
        textDecoration: 'none',
        color: '#49be25',
        fontFamily: 'Arial Black, sans-serif',
    };
    return (
        <Container>
            <br/>
            <br/>
            <h1 style={{
                marginRight: '20px',
                margin: '10px',
                textDecoration: 'none',
                color: '#4ca65b',
                fontFamily: 'Arial Black, sans-serif',}}>Conta Menu</h1>
            <br/>
            <Row style={{
                backgroundColor:'#b8d9b8',
                borderRadius: '10px'
            }}>
                <Col>
                    <div><br/>
                        <label style={corTextoBase}>Usuario </label>
                        <br/>
                        <br/>
                        <label style={corTextoBase}>Número da conta </label>
                        <br/>
                        <br/>
                        <label style={corTextoBase}>Agência </label>
                        <br/>
                        <br/>
                        <label style={corTextoBase}>Tipo </label>
                        <br/>
                        <br/>
                        <label style={corTextoBase}>Saldo </label>
                        <br/>
                        <br/>

                    </div>
                </Col>
                <Col><br/>
                    <label style={corTextoResult}>{nomeUsuario}</label>
                    <br/>
                    <br/>
                    <label style={corTextoResult}>{numeroConta}</label>
                    <br/>
                    <br/>
                    <label style={corTextoResult}>{agencia}</label>

                    <br/>
                    <br/>
                    <label style={corTextoResult}>{tipoConta}</label>
                    <br/>
                    <br/>
                    <label style={corTextoResult}>{saldo}</label>
                    <br/>
                    <br/>
                </Col>
            </Row>
        </Container>
    );
}
