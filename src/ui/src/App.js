
import {
    BrowserRouter,
    Route,
    Routes
} from 'react-router-dom';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import PaginaInicial from "./pages/basePaginasPublicas/PaginaInicial";
import PaginaLogin from "./pages/basePaginasPublicas/PaginaLogin";
import PaginaCadastrarUsuario from "./pages/basePaginasPublicas/PaginaCadastrarUsuario";
import TelaInicialUsuarioLogado from "./pages/baseMenuLogado/TelaInicialUsuarioLogado";
import PaginaTransferencia from "./pages/baseMenuLogado/PaginaTransferencia";
import PaginaCadastrarConta from "./pages/baseMenuLogado/PaginaCadastrarConta";
import {Extrato} from "./pages/baseMenuLogado/PaginaExtrato";
import {PaginaPagamento} from "./pages/baseMenuLogado/PaginaPagamento";
import PaginaDeposito from "./pages/baseMenuLogado/PaginaDeposito";
import PaginaAtualizarCadastro from "./pages/baseMenuLogado/PaginaAtualizarCadastro";
import PaginaEmprestimo from "./pages/baseMenuLogado/PaginaEmprestimo";
import PaginaAjuda from "./pages/baseMenuLogado/PaginaAjuda";


function App() {
return (
    <div className="App">
      <BrowserRouter>
        <Routes>
            <Route path={'/'} element={<PaginaInicial/>}/>
            <Route path={'/login'} element={<PaginaLogin/>}/>
            <Route path={'/logout'} element={<PaginaLogin/>}/>
            <Route path={'/cadastrar'} element={<PaginaCadastrarUsuario/>}/>
            <Route path={'/sessao'} element={<TelaInicialUsuarioLogado/>}/>
            <Route path={'/transferencia'} element={<PaginaTransferencia/>}/>
            <Route path={'/criarConta'} element={<PaginaCadastrarConta/>}/>
            <Route path={'/extrato'} element={<Extrato/>}/>
            <Route path={'/pagamento'} element={<PaginaPagamento/>}/>
            <Route path={'/deposito'} element={<PaginaDeposito/>}/>
            <Route path={'/atualizaCadastro'} element={<PaginaAtualizarCadastro/>}/>
            <Route path={'/emprestimo'} element={<PaginaEmprestimo/>}/>
            <Route path={'/ajuda'} element={<PaginaAjuda/>}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
