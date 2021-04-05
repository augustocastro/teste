package io.github.augustocastro.teste.rest;

import io.github.augustocastro.teste.model.entity.Cliente;
import io.github.augustocastro.teste.model.repository.ClienteRepository;
import io.github.augustocastro.teste.rest.dto.ClienteDTO;
import io.github.augustocastro.teste.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteRepository repository;

    private final ClienteService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@Valid @RequestBody ClienteDTO dto) {
        return service.salvar(dto);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @Valid @RequestBody ClienteDTO dto) {
        service.atualizar(id, dto);
    }

    @GetMapping
    public List<Cliente> pesquisarTodos() {
        return service.pesquisarTodos();
    }

    @GetMapping("{id}")
    public Cliente pesquisarPorId(@PathVariable Integer id) {
        return service.pesquisarPorId(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer id) {
        service.excluir(id);
    }
}
