package com.spring.bancospring.service;

import com.spring.bancospring.dto.ClienteDTO;
import com.spring.bancospring.dto.LoginDTO;

public interface ILogin {

	boolean loginInicio();
	boolean loginUsuario(ClienteDTO clienteDTO);
	LoginDTO verInicioLogin();
	void criarUsuario(ClienteDTO clienteDTO);
	void deletarUsuario(ClienteDTO clienteDTO);

}
