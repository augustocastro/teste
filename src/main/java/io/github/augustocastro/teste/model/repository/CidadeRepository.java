package io.github.augustocastro.teste.model.repository;

import io.github.augustocastro.teste.model.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

    Optional<Cidade> findByNome(String nome);

    Optional<Cidade> findByEstado(String estado);
}