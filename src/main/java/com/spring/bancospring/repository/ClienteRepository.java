package com.spring.bancospring.repository;

import com.spring.bancospring.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    @Query("SELECT u FROM Cliente u JOIN FETCH u.acesso acesso WHERE u.usuario = :usuario")
    public Cliente findByUsuario(@Param("usuario") String usuario);

    @Query("SELECT u.idCliente FROM Cliente u WHERE u.usuario = :usuario")
    public String findIdByUsuario(@Param("usuario") String usuario);

    boolean existsByCpf(String cpf);
    boolean existsByUsuario(String Usuario);
}
