import React from 'react'
import {Link, Outlet} from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.min.css'
import '../imagens/springlogo2.png'

const LinksImagensRedondas = ({ imagemEndereco, linkEndereco,tamanhoMax,margemTop,descricao }) => {

    return (
        <div style={{ marginLeft:'20px',height: '120px', textAlign: 'center', marginBottom: '20px',marginRight:'10px' }}>
            <Link to={linkEndereco} style={{ display: 'flex',flexDirection: 'column', justifyContent: 'center', alignItems: 'center',height: '100%' }}>
                <button className="btn btn-circle btn-primary" style={{
                    backgroundColor: '#Cde6cb',
                    borderColor: '#Cde6cb',
                    display: 'flex',
                    alignItems: 'center', // Centraliza verticalmente
                    justifyContent: 'center', // Centraliza horizontalmente

                }}>
                    <div className="circle text-center">
                        <img src={imagemEndereco} alt="deposito"
                             className="img-fluid" style={{ maxWidth: tamanhoMax,verticalAlign: "middle" ,marginTop:margemTop }}/>
                    </div>
                </button>
            </Link>
            <label className="text-center estiloFonte" style={{fontSize:"10px",marginRight:"10px"}}>{descricao}</label>
        </div>
    )

}

export default LinksImagensRedondas;