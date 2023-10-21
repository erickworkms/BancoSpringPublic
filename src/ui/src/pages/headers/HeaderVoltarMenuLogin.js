import React from 'react'
import {Link} from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.min.css'
import '../imagens/springlogo2.png'
import {Col, Row} from "react-bootstrap";
import LogoHeader from "./LogoHeader";

const HeaderVoltarMenuLogin = () => {
    const estiloFonte = {
        marginRight: '20px',
        margin: '10px',
        textDecoration: 'none',
        color: '#49be25',
        fontFamily: 'Arial Black, sans-serif'
    };
    return (
        <>
            <Row style={{background: '#Cde6cb'}}>
                <Col xs={2} style={{marginTop: '10px', display: 'flex'}}>
                    <LogoHeader/>
                </Col>
                <Col xs={10} className="d-flex justify-content-end" style={{marginTop: '10px'}}>
                    <Link to={"/sessao"} style={estiloFonte}>Voltar ao menu inicial</Link>
                </Col>
            </Row>
        </>
    )

}

export default HeaderVoltarMenuLogin;