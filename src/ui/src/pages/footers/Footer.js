import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css'
import '../imagens/springlogo2.png'

const Footer = () => {

    return (
        <>
            <footer style={{
                backgroundColor: '#333',
                color: '#fff',
                padding: '20px',
                textAlign: 'center'
            }}>
                {/* Espaço para links */}
                <div style={{marginTop: '10px',
                    color: '#fff',
                    padding: '20px',
                    textAlign: 'none'
                }}>
                    <p>Este template pode ser modificado a vontade, foi apenas criado com o objetivo de demonstrar a integração de softwares e api´s de mercado.</p>
                </div>
                {/* Linha mais grossa */}
                <hr style={{
                    borderTop: '2px solid #fff',
                    marginTop: '10px'
                }} />
            </footer>
        </>
    )

}

export default Footer;