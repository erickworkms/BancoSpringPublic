import React from 'react';
import { Table, Form } from 'react-bootstrap';
import {recebeAutorizacao} from "../autenticacao";
import axios from "axios";

function ListaContas({contas}) {

    const corTabela = {
        marginRight: '20px',
        margin: '10px',
        textDecoration: 'none',
        color: '#49be25',
        fontFamily: 'Arial Black, sans-serif',
        backgroundColor:'#Cde6cb',
        borderRadius: '10px',
        verticalAlign: 'middle',
    };

    const corTextoBase = {
        marginRight: '20px',
        margin: '10px',
        textDecoration: 'none',
        color: '#539f5b',
        fontFamily: 'Arial Black, sans-serif',
        backgroundColor:'#Cde6cb',
        borderRadius: '10px',
        verticalAlign: 'middle',
    };

    const corTextoResult = {
        textDecoration: 'none',
        color: '#49be25',
        fontFamily: 'Arial Black, sans-serif',
        backgroundColor:'#Cde6cb',
        borderRadius: '10px',
        verticalAlign: 'middle',
        textAlign: 'left',
    };

    const verContaSelecionada = async (event) => {
        event.preventDefault();

        const autenticacao = recebeAutorizacao();
        const contaSelecionada = event.target.value;
        if(autenticacao){
            const configAut = {
                headers:{
                    Authorization: `${autenticacao}`
                }
            };
            try {
                // Realize a solicitação POST com Axios
                const response = await axios.post('/login/atualizaContaEscolhida',contaSelecionada,configAut);

                // Faça algo com a resposta (se necessário)
                console.log('Resposta do servidor:', response.data);
                console.log('Dados para enviar:', contaSelecionada);
                recebeAutorizacao(response.data)
            } catch (error) {
                // Trate erros, se ocorrerem
                alert('falha '+ contaSelecionada);
                console.error('Erro ao realizar a solicitação POST:', error);
            }
    }};

    return (
        <Table striped bordered hover style={corTabela}>
            <thead>
            <tr>
                <th style={corTextoBase}></th>
                <th style={corTextoBase}>Agencia</th>
                <th style={corTextoBase}>Numero</th>
                <th style={corTextoBase}>Tipo</th>
                <th style={corTextoBase}>Saldo</th>
            </tr>
            </thead>
            <tbody>

            {Array.isArray(contas) && contas.map((conta) => (
                <tr key={conta.id}>
                    <td >
                        <Form.Check
                            type="radio"
                            name="contas"
                            style={corTabela}
                            value={conta.numero}
                            //checked={conta.id === contaSelecionada}
                            onChange={verContaSelecionada}
                        />
                    </td>
                    <td style={corTextoResult}>{conta.agencia}</td>
                    <td style={corTextoResult}>{conta.numero}</td>
                    <td style={corTextoResult}>{conta.tipoConta}</td>
                    <td style={corTextoResult}>{conta.saldo}</td>
                </tr>
            ))}
            </tbody>
        </Table>
    );
}

export default ListaContas;