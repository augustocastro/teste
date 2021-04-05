package io.github.augustocastro.teste.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.augustocastro.teste.model.entity.Cidade;
import io.github.augustocastro.teste.model.entity.Sexo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ClienteDTO {

    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nomeCompleto;

    @NotNull(message = "{campo.sexo.obrigatorio}")
    private Sexo sexo;

    @NotEmpty(message = "{campo.data_nascimento.obrigatorio}")
    @Pattern(
            message = "{campo.data_nascimento.invalido}",
            regexp = "[0-9]{2}/[0-9]{2}/[0-9]{4}"
    )
    @JsonFormat(pattern = "dd/MM/yyyy")
    private String dataNascimento;

    @NotNull(message = "{campo.cidade.obrigatorio}")
    private Integer cidade;
}
