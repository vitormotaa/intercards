package com.cefet.intercards.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JogadorRequestDTO {

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;
    
    @NotNull(message = "O número é obrigatório.")
    private Integer numero;

    @NotNull(message = "A equipe é obrigatória.")
    private Long equipeId;

    @NotNull(message = "A URL é obrigatória.")
    private String urlImagem;
}
