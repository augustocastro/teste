package io.github.augustocastro.teste.model.repository;

import io.github.augustocastro.teste.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
