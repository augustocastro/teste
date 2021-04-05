package io.github.augustocastro.teste.rest;

import io.github.augustocastro.teste.model.entity.Cidade;
import io.github.augustocastro.teste.model.repository.CidadeRepository;
import io.github.augustocastro.teste.rest.dto.CidadeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cidades")
@RequiredArgsConstructor
public class CidadeController {

    private final CidadeRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade salvar(@Valid @RequestBody CidadeDTO dto) {
        Cidade cidade = new Cidade();
        cidade.setNome(dto.getNome());
        cidade.setEstado(dto.getEstado());
        return repository.save(cidade);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @Valid @RequestBody Cidade cidade) {
        repository
                .findById(id)
                .map(c -> {
                    c.setNome(cidade.getNome());
                    c.setEstado(cidade.getEstado());
                    repository.save(c);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cidade não encontrada."));
    }

    @GetMapping
    public List<Cidade> pesquisarTodos() {
        return repository.findAll();
    }

    @GetMapping("/nome/{nome}")
    public Cidade pesquisarPorNome(@PathVariable String nome) {
        return repository
                .findByNome(nome)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cidade não encontrada."));
    }

    @GetMapping("/estado/{estado}")
    public List<Cidade> pesquisarPorEstado(@PathVariable String estado) {
        return repository
                .findByEstado(estado)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cidade não encontrada."));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer id) {
        repository
                .findById(id)
                .map(cidade -> {
                    repository.delete(cidade);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cidade não encontrada."));
    }
}
