import React, {useState} from 'react';
import DatePicker from "react-datepicker";
import axios from "axios";
import HeaderVoltarMenuLogin from "../headers/HeaderVoltarMenuLogin";
import Footer from "../footers/Footer";
import {Button, Col, Container, Form, Row} from "react-bootstrap";
import {recebeAutorizacao} from "../autenticacao";
import {VerificaLogin} from "../basePaginasPublicas/VerificaLogin";


export function PaginaPagamento() {

    /////Verificações de pagamento
    const [pagamento, setPagamento] = useState({
        dataPagamento:'',
        codigoBarra: '',
        valor: '',
        dataVencimento:''
    });

    const limpaNumero = (evento) => {
        const {name, value} = evento.target;
        const tamanhoCodigo = evento.target.value.length;
        setPagamento({...pagamento, [name]: value.slice(0, -1)});
        if (tamanhoCodigo < 30 && /^[0-9]+$/.test(evento.target.value.charAt(tamanhoCodigo - 1))) {
            switch (tamanhoCodigo) {
                case 4:
                    setPagamento({...pagamento, [name]: value + '.'});
                    break;
                case 9:
                    setPagamento({...pagamento, [name]: value + '.'});
                    break;
                case 14:
                    setPagamento({...pagamento, [name]: value + '.'});
                    break;
                case 19:
                    setPagamento({...pagamento, [name]: value + '.'});
                    break;
                case 24:
                    setPagamento({...pagamento, [name]: value + '.'});
                    break;
                default:
                    setPagamento({...pagamento, [name]: value});
                    break;
            }

        }
    };
    const verDataVencimento = (event) => {
        setPagamento({
            ...pagamento,
            dataVencimento: event.target.value
        });
    };
    const verDataPagamento = (event) => {
        setPagamento({
            ...pagamento,
            dataPagamento: event.target.value
        });
    };

    const submitDadosPagamento = async (event) => {
        event.preventDefault(); // Impede o comportamento padrão do formulário

        const autenticacao = recebeAutorizacao();
        if(autenticacao){
            const configAut = {
                headers:{
                    Authorization: `${autenticacao}`
                }
            };

        try {
            const response = await axios.post('/login/pagamento', pagamento,configAut);

            console.log('Resposta do servidor:', response.data);
            alert('Pagamento realizado com sucesso');
        } catch (error) {
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

        setPagamento({...pagamento, [name]: value.slice(0,-1)});
        if (evento.target.value.length <= 13) {
            setPagamento({
                ...pagamento,
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
                        <Form onSubmit={submitDadosPagamento}>

                            <div className="estiloFonte">
                                <Form.Label style={{fontSize: '30px'}}>Pagamento</Form.Label>
                            </div>
                            <div className="estiloFonte justify-content-left">
                                <Form.Label>Escolha abaixo a data para o agendar o pagamento </Form.Label>
                            </div>
                            <div className="estiloFonte">
                                <Form.Label>Data Pagamento : </Form.Label>
                                <Form.Control type={"date"} value={pagamento.dataPagamento} onChange={verDataPagamento}
                                              style={{textAlign: 'center'}} autoComplete="off"/>
                            </div>
                            <div className="estiloFonte">
                                <Form.Label>Codigo de barra : </Form.Label>
                                <Form.Control type={"text"} id="codigoBarra" name="codigoBarra"
                                              value={pagamento.codigoBarra}
                                              onChange={limpaNumero} style={{textAlign: 'center'}} autoComplete="off"/>
                            </div>
                            <div className="estiloFonte">
                                <Form.Label>Valor : </Form.Label>
                                <Form.Control type={"text"} id="valor" name="valor" value={pagamento.valor}
                                              onChange={mudaValor} style={{textAlign: 'center'}} autoComplete="off"/>
                            </div>
                            <div className="estiloFonte">
                                <Form.Label>Vencimento : </Form.Label>
                                <Form.Control type={"date"} value={pagamento.dataVencimento} onChange={verDataVencimento}
                                              style={{textAlign: 'center'}} autoComplete="off"/>
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

