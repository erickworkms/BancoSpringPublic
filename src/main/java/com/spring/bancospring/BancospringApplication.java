package com.spring.bancospring;

import com.spring.bancospring.model.Cliente;
import com.spring.bancospring.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages="com.spring.bancospring.repository")
@EntityScan("com.spring.bancospring.model")
public class BancospringApplication implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;

	public static void main(String[] args) {
		SpringApplication.run(BancospringApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		Cliente user = clienteRepository.findByUsuario("admin");
		if (user == null){
			user = new Cliente();
			user.setNome("admin");
			user.setUsuario("admin");
			user.setSenha("teste1234");
			user.setCpf("123456789");
			user.setEndereco("admin");
			user.setDataNascimento("111111");
			user.setTelefone("1111111");
			user.setEmail("admin@admin");
			user.getAcesso().add("ADMIN");
			user.getAcesso().add("USUARIO");
			clienteRepository.save(user);
		}
	}
}
