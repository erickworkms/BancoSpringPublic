import {useEffect} from "react";
import {recebeAutorizacao, removeToken} from "../autenticacao";
import axios from "axios";
import {useNavigate} from "react-router-dom";

export function VerificaLogin (){
    const history = useNavigate();
    useEffect(() => {
        const autenticacao = recebeAutorizacao();
        if(autenticacao){
            const configAut = {
                headers:{
                    Authorization: `${autenticacao}`
                }
            };
            // Faça a solicitação para o endpoint no componentDidMount ou useEffect
            axios.get('/login/verDadoslogin',configAut)
                .then((response) => {
                    // Atualize o estado com os dados recebidos do backend
                    if (response.status === 200 && response.data != null) {

                    }else {
                        alert('Sessão Encerrada');
                        console.error('Sessão Encerrada');
                        removeToken();
                        history('/');
                    }
                })
                .catch((error) => {
                    // Lide com erros, se necessário
                    alert('Sessão Encerrada');
                    removeToken();
                    console.error('Sessão Encerrada');
                    history('/');
                });
        }else {
            alert('Sessao encerrada');
            removeToken();
            console.error('Erro ao buscar dados');
            history('/');
        }
    }, []);
}