package io.github.augustocastro.teste.model.repository;

import io.github.augustocastro.teste.model.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

    Optional<List<Cidade>> findByEstado(String estado);

    Optional<List<Cidade>> findByNome(String nome);

    Optional<List<Cidade>> findByEstadoAndNome(String estado, String nome);
}
