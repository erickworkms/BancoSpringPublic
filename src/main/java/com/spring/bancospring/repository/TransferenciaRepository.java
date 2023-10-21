package com.spring.bancospring.repository;

import com.spring.bancospring.model.RegistroTransferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransferenciaRepository extends JpaRepository<RegistroTransferencia, UUID> {
    @Query("SELECT u FROM RegistroTransferencia u WHERE u.destinatario = :destinatario")
    public RegistroTransferencia findByDestinatario(@Param("destinatario") UUID destinatario);

    @Query("SELECT u FROM RegistroTransferencia u WHERE u.recebedor = :recebedor")
    public RegistroTransferencia findByRecebedor(@Param("recebedor") UUID recebedor);

    @Query("SELECT u FROM RegistroTransferencia u " +
            "WHERE (u.data BETWEEN :dataInicial AND :dataFinal) " +
            "AND (u.recebedor = :recebedor OR u.destinatario = :destinatario)")
    Optional<List<RegistroTransferencia>> findByDataBetweenAndRecebedorOrDestinatario(String dataInicial, String dataFinal, UUID recebedor, UUID destinatario);
}
