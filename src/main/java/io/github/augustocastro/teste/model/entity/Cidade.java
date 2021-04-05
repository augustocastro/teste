package io.github.augustocastro.teste.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @NotEmpty(message = "{campo.estado.obrigatorio}")
    private String estado;

}
