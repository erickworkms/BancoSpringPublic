import React, {useState} from 'react';
import {Link, useNavigate} from 'react-router-dom'
import axios from 'axios';
import {Col, Container, Form, Row} from "react-bootstrap";
import Button from 'react-bootstrap/Button'
import Footer from "../footers/Footer";
import HeaderVoltarMenuPrincipal from "../headers/HeaderVoltarMenuPrincipal";
import {adicionaAutorizacao, removeToken} from "../autenticacao";

const PaginaLogin = () => {

    const history = useNavigate();

    const [formulario, setFormulario] = useState({
        nome: '',
        senha: ''
    });

    const mudancaDadosLogin = (event) => {
        const {name, value} = event.target;
        setFormulario({
            ...formulario,
            [name]: value,
        });
    };

    const submitDadosLogin = async (event) => {
        event.preventDefault();

        try {
            const response = await axios.post('/login/verlogin', null, {
                params: {
                    nome: formulario.nome,
                    senha: formulario.senha,
                },
            });
            if (response.status === 200) {
                adicionaAutorizacao(response.data)
                history('/sessao');
            }else{
                removeToken();
                alert('Senha ou Usuario incorretos'+response.data);
            }
        } catch (error) {
            removeToken();
            alert('Senha ou Usuario incorretos');
        }
    };

    return (
        <>  <Container className="estiloContainer">
       <HeaderVoltarMenuPrincipal/>
            <Row className="justify-content-center align-items-center"
                 style={{minHeight: '50vh',
                     display: 'flex',
                     marginBottom:'0px',
                     backgroundColor:'#cee595'}}>
                <Col xs="auto" className="mx-auto">
                    <div className="text-center" style={{
                        marginTop:"20px",
                        marginBottom:"40px",
                        textDecoration: 'none',
                        color: '#49be25',
                        fontFamily: 'Arial Black, sans-serif'}}>
                        <h1>Login</h1>
                    </div>
                    <Form onSubmit={submitDadosLogin}>
                        <div className="estiloFonte">
                            <Form.Label>Usuario</Form.Label>
                            <Form.Control type="text" id="nome" name="nome" value={formulario.nome}
                                   onChange={mudancaDadosLogin} autoComplete="off"/>
                        </div>
                        <br/>
                        <div className="estiloFonte">
                            <Form.Label>Senha</Form.Label>
                            <Form.Control type={"password"} id={"senha"} name="senha" value={formulario.senha}
                                   onChange={mudancaDadosLogin} autoComplete="off"/>
                        </div>
                        <div className="text-center">
                            <Button type={"submit"} className="w-100"
                                    style={{
                                        maxWidth: '200px',
                                        marginTop:'25px',
                                        backgroundColor:'#49be25',
                                        borderColor:'#49be25'}}>Enviar</Button>
                        </div>
                        <div className="text-center" style={{
                            margin:'10px',
                            textDecoration: 'none',
                            color: '#49be25',
                            fontFamily: 'Arial Black, sans-serif',
                            marginTop:'15px',
                        }}>
                        <p>Ainda não tem usuario cadastrado?</p>
                            <Link to={'/Cadastrar'} > Clique Aqui!</Link>
                        </div>
                    </Form>
                </Col>
            </Row>
            <Footer/>
        </Container>
        </>
    )
}

export default PaginaLogin;
