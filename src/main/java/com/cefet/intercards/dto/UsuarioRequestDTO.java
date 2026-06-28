package com.cefet.intercards.dto;

import com.cefet.intercards.entity.Usuario.TipoUsuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsuarioRequestDTO {

    @NotBlank(message = "O login é obrigatório")
    private String login;

    @NotBlank(message = "A senha é obrigatória")
    private String senha;

    @NotNull(message = "Perfil é obrigatório")
    private TipoUsuario perfil;
}
