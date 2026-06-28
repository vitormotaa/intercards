package com.cefet.intercards.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.intercards.dto.AutentificacaoRequestDTO;
import com.cefet.intercards.dto.AutentificacaoResponseDTO;
import com.cefet.intercards.entity.Usuario;
import com.cefet.intercards.exception.ResourceNotFoundException;
import com.cefet.intercards.repository.UsuarioRepository;

@Service
public class AutentificacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public AutentificacaoResponseDTO login(AutentificacaoRequestDTO dto){
         Usuario usuario = usuarioRepository.findByLoginAndSenha(dto.getLogin(), dto.getSenha())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado. Login ou senha incorretos."));

        return new AutentificacaoResponseDTO(usuario);
    }
}
