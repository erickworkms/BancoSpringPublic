import React from 'react';
import {Link} from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css'
import {Container,Row, Col} from 'react-bootstrap';
import LogoHeader from "../headers/LogoHeader";
import Footer from "../footers/Footer";


const PaginaInicial = () => {

    return (
        <>
            <div>
            <Container className="estiloContainer">
                <div className="HeaderMenu">
                    <Row style={{background: '#Cde6cb'}} >
                        <Col xs={2} style={{marginTop:'10px',display:'flex'}}>
                            <LogoHeader></LogoHeader>
                        </Col>
                        <Col xs={10} className="d-flex justify-content-end" style={{marginTop:'10px'}}>
                            <Link to={"/Login"} style={{marginRight:'20px',
                                margin:'10px',
                                textDecoration: 'none',
                                color: '#49be25',
                                fontFamily: 'Arial Black, sans-serif',
                                marginTop:'15px',
                            }}>Login</Link>
                            <Link to={"/Cadastrar"} style={{marginRight:'20px',
                                margin:'10px',
                                textDecoration: 'none',
                                color: '#49be25',
                                fontFamily: 'Arial Black, sans-serif',
                                marginTop:'15px',
                            }}>Cadastrar</Link>
                        </Col>
                    </Row>

                    <Row className="justify-content-center" style={{ display:'flex'}}>

                        <Col style={{marginTop:'0px'}}> <img className="figure-img img-fluid"
                             src={require('../imagens/SpringBanner.png')}
                             alt="SpringMain"
                             style={{width: '100%', height: '100%'}}
                        />
                        </Col>
                    </Row>

                    <Row className="justify-content-center" style={{ display:'flex'}}>

                        <Col style={{marginTop:'0px'}}> <img className="figure-img img-fluid"
                                                             src={require('../imagens/FundoFrontend.png')}
                                                             alt="SpringMain"
                                                             style={{width: '100%', height: '100%'}}
                        />
                        </Col>
                    </Row>
                </div>
                <Footer/>
            </Container>
            </div>
        </>
    )
}

export default PaginaInicial;
