import React from 'react'
import {Link, Outlet} from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.min.css'
import '../imagens/springlogo2.png'

const LogoHeader = () => {

    return (
        <>
            <header>
                <h1>
                    <Link to={'/'} className={'header-link'}>
                        <img className="figure-img img-fluid"
                             src={require('../imagens/springlogo2.png')}
                             alt="SpringLogo"
                             style={{width: '100px', height:'auto'}}
                        />
                    </Link>
                </h1>
            </header>
            <div id={'LogoHeader'}>
                <Outlet/>
            </div>
        </>
    )

}

export default LogoHeader;