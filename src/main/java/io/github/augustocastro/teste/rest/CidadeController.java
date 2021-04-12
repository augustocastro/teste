package io.github.augustocastro.teste.rest;

import io.github.augustocastro.teste.model.entity.Cidade;
import io.github.augustocastro.teste.model.repository.CidadeRepository;
import io.github.augustocastro.teste.rest.dto.CidadeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    public void atualizar(@PathVariable Integer id, @Valid @RequestBody CidadeDTO dto) {
        repository
                .findById(id)
                .map(c -> {
                    c.setNome(dto.getNome());
                    c.setEstado(dto.getEstado());
                    repository.save(c);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cidade não encontrada."));
    }

    @GetMapping
    public List<Cidade> pesquisarTodos() {
        return repository.findAll();
    }

    @GetMapping("/query")
    public List<Cidade> pesquisar(@RequestParam(required = false) String estado, @RequestParam(required = false) String nome) {
        Optional<List<Cidade>> optional;

        if (estado != null && nome != null) {
            optional = repository.findByEstadoAndNome(estado, nome);
        } else if (estado != null) {
            optional = repository.findByEstado(estado);
        } else if (nome != null) {
            optional = repository.findByNome(nome);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Informe ao menos um paramêtro: Nome ou Estado.");
        }

        return optional
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma cidade encontrada."));
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
