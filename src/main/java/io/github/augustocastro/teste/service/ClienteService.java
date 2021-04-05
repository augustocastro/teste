package io.github.augustocastro.teste.service;

import io.github.augustocastro.teste.exception.NotFoundException;
import io.github.augustocastro.teste.model.entity.Cidade;
import io.github.augustocastro.teste.model.entity.Cliente;
import io.github.augustocastro.teste.model.repository.CidadeRepository;
import io.github.augustocastro.teste.model.repository.ClienteRepository;
import io.github.augustocastro.teste.rest.dto.ClienteDTO;
import io.github.augustocastro.teste.util.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private CidadeRepository cidadeRepository;

    public Cliente salvar(ClienteDTO dto) {
        try {
            Cliente cliente = criarCliente(dto);
            repository.save(cliente);
            return cliente;
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar o cliente.");
        }
    }

    public void atualizar(Integer id, ClienteDTO dto) {
        repository
                .findById(id)
                .map(c -> {
                    try {
                        Cliente cliente = criarCliente(dto);
                        cliente.setId(c.getId());
                        repository.save(cliente);
                        return Void.TYPE;
                    } catch (Exception ex) {
                        throw new RuntimeException("Erro ao atualizar o cliente.");
                    }
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
    }

    private Cliente criarCliente(ClienteDTO dto) {
        Cidade cidade = cidadeRepository
                .findById(dto.getCidade())
                .orElseThrow(() -> new NotFoundException("Cidade não encontrada"));

        LocalDate dataNascimento = DateHelper.parse(dto.getDataNascimento());

        return Cliente
                .builder()
                .nomeCompleto(dto.getNomeCompleto())
                .sexo(dto.getSexo())
                .dataNascimento(dataNascimento)
                .cidade(cidade)
                .build();
    }

    public List<Cliente> pesquisarTodos() {
        return repository.findAll();
    }

    public void excluir(Integer id) {
        repository
                .findById(id)
                .map(cidade -> {
                    repository.delete(cidade);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
    }

    public Cliente pesquisarPorId(Integer id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
    }
}
