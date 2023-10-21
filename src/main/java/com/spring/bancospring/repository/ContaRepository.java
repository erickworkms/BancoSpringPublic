package com.spring.bancospring.repository;

import com.spring.bancospring.model.Cliente;
import com.spring.bancospring.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContaRepository extends JpaRepository<Conta, UUID> {
    List<Conta> findByIdCliente(UUID idCliente);

    Optional<Conta> findByIdConta(UUID idConta);

    Optional<Conta> findFirstByIdCliente(UUID idCliente);
    Optional<Conta> findByNumero(int numero);

    boolean existsByNumero(int numero);

    @Query("SELECT u FROM Conta u WHERE u.numero = :numero AND u.idCliente = :idCliente")
    Optional<Conta> findIdByNumeroAndIdCliente(@Param("numero") int numero,@Param("idCliente") UUID idCliente);

    @Query("SELECT u FROM Conta u WHERE u.agencia = :agencia AND u.numero = :numero")
    Optional<Conta> findIdByNumeroAndAgencia(@Param("numero") int numero,@Param("agencia") String agencia);
    @Query("SELECT COUNT(c) FROM Conta c")
    int countAllContas();
}
