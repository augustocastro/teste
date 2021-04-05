package io.github.augustocastro.teste.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class CidadeDTO {

    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @NotEmpty(message = "{campo.estado.obrigatorio}")
    private String estado;
}
