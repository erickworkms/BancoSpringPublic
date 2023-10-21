import React, {useState} from 'react';
import axios from "axios";
import HeaderVoltarMenuLogin from "../headers/HeaderVoltarMenuLogin";
import Footer from "../footers/Footer";
import {Button, Col, Container, Form, Row} from "react-bootstrap";
import {recebeAutorizacao} from "../autenticacao";
import {VerificaLogin} from "../basePaginasPublicas/VerificaLogin";


const PaginaDeposito = () => {

    const [formulario, setFormulario] = useState({
        dataDeposito:'',
        numeroConta: '',
        agencia: '',
        tipoConta: '',
        cpf: '',
        valor: ''
    });

    const mudancaConta = (event) => {
        const {name, value} = event.target;
        setFormulario({
            ...formulario,
            [name]: value,
        });
    };

    const verNumeroConta = (event) => {
        const {name, value} = event.target;
        if(event.target.value.length < 6 && /^[0-9]+$/.test(event.target.value) || event.target.value === ""){
            setFormulario({
                ...formulario,
                [name]: value,
            });
        }
    };

    const verDataDeposito = (event) => {
        setFormulario({
            ...formulario,
            dataDeposito: event.target.value
        });
    };
    const validarAgencia = (event) => {
        const {name, value} = event.target;

        setFormulario({...formulario, [name]: value.slice(0,-1)});

        if (event.target.value.length <= 7) {

            switch (formulario.agencia.length) {
                case 0:
                    if (/^[A-Za-z]+$/.test(event.target.value.charAt(0))) {
                        setFormulario({...formulario, [name]: value});
                    }
                  break;
                case 1:
                    if (/^[0-9]+$/.test(event.target.value.charAt(1))){
                        setFormulario({...formulario, [name]: value});
                    }
                    break;
                case 2:
                    if (/^[0-9]+$/.test(event.target.value.charAt(2))){
                        setFormulario({...formulario, [name]: value});
                    }
                    break;
                case 3:
                    if (/^[0-9]+$/.test(event.target.value.charAt(3))){
                        setFormulario({...formulario, [name]: value});
                    }
                    break;
                case 4:
                    if (/^[0-9]+$/.test(event.target.value.charAt(4))){
                        setFormulario({...formulario, [name]: value + '-'});
                    }
                    break;
                case 6:
                    if (/^[0-9]+$/.test(event.target.value.charAt(6))){
                        setFormulario({...formulario, [name]: value});
                    }
                    break;
            }
        }
    };

    const mudancaCpf = (event) => {
        const novoCpf = event.target.value.replace(/\D/g, ''); // Filtra caracteres não numéricos
        if (event.target.value.length <= 11) {
            setFormulario({...formulario, cpf: novoCpf});
        }
    };


    const submitDadosTransferencia = async (event) => {
        event.preventDefault();

        const autenticacao = recebeAutorizacao();
        if(autenticacao){
            const configAut = {
                headers:{
                    Authorization: `${autenticacao}`
                }
            };

        try {

            const response = await axios.post('/login/deposito', formulario,configAut);

            console.log('Resposta do servidor:', response.data);
            alert('Deposito realizado com sucesso');
        } catch (error) {
            // Trate erros, se ocorrerem
            console.error('Erro ao realizar a solicitação POST:', error);
        }
    }};

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
        setFormulario({...formulario, [name]: value.slice(0,-1)});
        if (evento.target.value.length <= 13) {
            setFormulario({
                ...formulario,
                [name]: formatarMoeda(value),
            });
        }
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
                        backgroundColor:'#Cde6cb',
                        borderRadius: '10px'
                    }}>
                        <Form onSubmit={submitDadosTransferencia}>
                            <div className="estiloFonte">
                                <Form.Label style={{fontSize: '30px'}}>Deposito</Form.Label>
                            </div>
                            <div className="estiloFonte justify-content-left">
                                <Form.Label>Coloque os dados abaixo para o deposito em conta </Form.Label>
                            </div>
                            <div className="estiloFonte">
                                <Form.Label>Data de deposito</Form.Label>
                                <Form.Control type={"date"} value={formulario.dataDeposito} onChange={verDataDeposito}
                                              style={{textAlign: 'center'}} autoComplete="off"/>
                            </div>
                            <div className="estiloFonte">
                                <Form.Label>Número da conta</Form.Label>
                                <Form.Control type="text" id="numeroConta" name="numeroConta"
                                              value={formulario.numeroConta}
                                              onChange={verNumeroConta} style={{textAlign: 'center'}} autoComplete="off"/>
                            </div>
                            <div className="estiloFonte">
                                <Form.Label>Agência</Form.Label>
                                <Form.Control type="text" id="agencia" name="agencia" value={formulario.agencia}
                                              onChange={validarAgencia} style={{textAlign: 'center'}} autoComplete="off"/>
                            </div>
                            <div className="estiloFonte">
                                <Form.Label>Tipo de conta</Form.Label>
                                <Form.Select type="text" id="tipoConta" name="tipoConta" value={formulario.tipoConta}
                                             onChange={mudancaConta} style={{textAlign: 'center'}}  autoComplete="off">
                                    <option value="ContaCorrente">Conta Corrente</option>
                                    <option value="ContaPoupanca">Conta Poupança</option>
                                    <option value="ContaSalario">Conta Salario</option>
                                </Form.Select>
                            </div>
                            <div className="estiloFonte">
                                <Form.Label>CPF do beneficiario</Form.Label>
                                <Form.Control type="text" id="cpf" name="cpf" value={formulario.cpf}
                                              onChange={mudancaCpf} style={{textAlign: 'center'}} autoComplete="off"/>
                            </div>
                            <div className="estiloFonte">
                                <Form.Label>Valor</Form.Label>
                                <Form.Control type="text" id="valor" name="valor" value={formulario.valor}
                                              onChange={mudaValor} style={{textAlign: 'center'}} autoComplete="off"/>
                            </div>
                            <div className="text-center">
                                <Button type={"submit"} className="w-100"
                                        style={{
                                            maxWidth: '200px',
                                            marginTop: '25px',
                                            marginBottom: '25px',
                                            backgroundColor: '#49be25',
                                            borderColor: '#49be25'
                                        }}>Depositar</Button>
                            </div>
                        </Form>
                    </Col>
                </Row>
                <Footer/>
            </Container>
        </>
    )
}

export default PaginaDeposito;
