import React, {useState} from 'react';
import axios from 'axios';
import HeaderVoltarMenuPrincipal from "../headers/HeaderVoltarMenuPrincipal";
import Footer from "../footers/Footer";
import {Button, Col, Container, Form, Row} from "react-bootstrap";
import '../css/styles.css'

const PaginaCadastrarUsuario = () => {

    const [formulario, setFormulario] = useState({
        nome: '',
        dataNascimento: '',
        cpf: '',
        endereco: '',
        telefone: '',
        email: '',
        usuario: '',
        senha: ''
    });

    const verDataNascimento = (event) => {
        setFormulario({
            ...formulario,
            dataNascimento: event.target.value
        });
    };
    const mudancaDadosLogin = (event) => {
        const {name, value} = event.target;
        setFormulario({
            ...formulario,
            [name]: value,
        });
    };

    const mudancaTelefone = (event) => {
        const {name, value} = event.target;
        setFormulario({...formulario, [name]: value.slice(0, -1)});
        if (event.target.value.length <= 11) {
            setFormulario({...formulario, [name]: value.replace(/\D/g, ''),});
        }
    };
    const mudancaCpf = (event) => {
        const {name, value} = event.target;
        setFormulario({...formulario, [name]: value.slice(0, -1)});
        if (event.target.value.length <= 11) {
            setFormulario({...formulario, [name]: value.replace(/\D/g, ''),});
        }
    };

    const submitDadosLogin = async (event) => {
        event.preventDefault();

        try {
            const response = await axios.post('/login/crialogin', formulario);

            // Faça algo com a resposta (se necessário)
            console.log('Resposta do servidor:', response.data + formulario);
            alert('Usuário cadastrado com sucesso');
        } catch (error) {
            // Trate erros, se ocorrerem
            console.error('Erro ao realizar a solicitação POST:', error);
            alert('Erro ao realizar a solicitação POST:'+ error);
        }
    };


    return (
        <> <Container className="estiloContainer">
            <HeaderVoltarMenuPrincipal/>
            <Row className="justify-content-center align-items-center"
                 style={{
                     minHeight: '30vh',
                     display: 'flex',
                     marginBottom: '0px',
                     backgroundColor: '#cee595'
                 }}>
                <Col xs="auto"  className="text-center">
                    <Form onSubmit={submitDadosLogin}>
                        <div className="text-center" style={{
                            marginTop: "20px",
                            marginBottom: "40px",
                            textDecoration: 'none',
                            color: '#49be25',
                            fontFamily: 'Arial Black, sans-serif'
                        }}>
                            <h1>Tela de Cadastro</h1>
                        </div>
                        <div className="estiloFonte">
                            <Form.Label>Nome</Form.Label>
                            <Form.Control type="text" id="nome" name="nome" value={formulario.nome}
                                          onChange={mudancaDadosLogin} style={{textAlign: 'center'}} autoComplete="off"/>
                        </div>

                        <div className="estiloFonte">
                            <Form.Label>Data de Nascimento</Form.Label>
                            <Form.Control type={"date"} value={formulario.dataNascimento} onChange={verDataNascimento}
                                          style={{textAlign: 'center'}} autoComplete="off"/>
                        </div>

                        <div className="estiloFonte">
                            <Form.Label>Cpf</Form.Label>
                            <Form.Control type={"text"} id={"cpf"} name="cpf" value={formulario.cpf}
                                          onChange={mudancaCpf} style={{textAlign: 'center'}} autoComplete="off"/>
                        </div>


                        <div className="estiloFonte">
                            <Form.Label>Endereço</Form.Label>
                            <Form.Control type={"text"} id={"endereco"} name="endereco" value={formulario.endereco}
                                          onChange={mudancaDadosLogin} style={{textAlign: 'center'}} autoComplete="off"/>
                        </div>



                        <div className="estiloFonte">
                            <Form.Label>Telefone</Form.Label>
                            <Form.Control type={"text"} id={"telefone"} name="telefone" value={formulario.telefone}
                                          onChange={mudancaTelefone} style={{textAlign: 'center'}} autoComplete="off"/>
                        </div>


                        <div className="estiloFonte">
                            <Form.Label>Email</Form.Label>
                            <Form.Control type={"text"} id={"email"} name="email" value={formulario.email}
                                          onChange={mudancaDadosLogin} style={{textAlign: 'center'}} autoComplete="off"/>
                        </div>


                        <div className="estiloFonte">
                            <Form.Label>Usuario</Form.Label>
                            <Form.Control type={"text"} id={"usuario"} name="usuario" value={formulario.usuario}
                                          onChange={mudancaDadosLogin} style={{textAlign: 'center'}} autoComplete="off"/>
                        </div>



                        <div className="estiloFonte">
                            <Form.Label>Senha de acesso</Form.Label>
                            <Form.Control type={"password"} id={"senha"} name="senha" value={formulario.senha}
                                          onChange={mudancaDadosLogin} style={{textAlign: 'center'}} autoComplete="off"/>
                        </div>

                        <div className="text-center">
                            <Button type={"submit"} className="w-100"
                                    style={{
                                        maxWidth: '200px',
                                        marginTop: '0px',
                                        marginBottom: '25px',
                                        backgroundColor: '#49be25',
                                        borderColor: '#49be25'
                                    }}>Cadastrar Usuario</Button>
                        </div>
                    </Form>
                </Col>
            </Row>
            <Footer/>
        </Container>
        </>
    )
}

export default PaginaCadastrarUsuario;
