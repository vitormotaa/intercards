package com.cefet.intercards.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ModalidadeRequestDTO {

    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;

    @NotNull(message = "O limite de jogadores é obrigatório")
    private Integer limtieJogadores;

    //modificar o DTO pra o numero de jogadores
}