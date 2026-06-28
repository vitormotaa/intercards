package com.cefet.intercards.dto;

import com.cefet.intercards.entity.Usuario;
import com.cefet.intercards.entity.Usuario.TipoUsuario;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AutentificacaoResponseDTO {
    private Long id;
    private String login;
    private TipoUsuario perfil;

    public AutentificacaoResponseDTO(Usuario usuario){
        this.id = usuario.getId();
        this.login = usuario.getLogin();
        this.perfil = usuario.getPerfil();
    }
}
