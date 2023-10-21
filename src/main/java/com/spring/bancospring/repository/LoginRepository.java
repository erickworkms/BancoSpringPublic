package com.spring.bancospring.repository;

import com.spring.bancospring.model.Cliente;
import com.spring.bancospring.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoginRepository extends JpaRepository<Login, UUID> {

}
