package com.spring.bancospring.repository;

import com.spring.bancospring.model.Cliente;
import com.spring.bancospring.model.RegistroDeposito;
import com.spring.bancospring.model.RegistroPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DepositoRepository extends JpaRepository<RegistroDeposito, UUID> {
    Optional<List<RegistroDeposito>> findByDataBetweenAndRecebedorOrDepositante(String dataInicial,String dataFinal, UUID recebedor, UUID depositante);
}
