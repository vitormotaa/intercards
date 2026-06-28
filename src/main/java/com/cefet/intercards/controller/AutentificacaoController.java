package com.cefet.intercards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cefet.intercards.dto.AutentificacaoRequestDTO;
import com.cefet.intercards.dto.AutentificacaoResponseDTO;
import com.cefet.intercards.service.AutentificacaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
@Tag(name = "Usuário")
public class AutentificacaoController {

    @Autowired
    private AutentificacaoService autentificacaoService;

    @PostMapping
    @Operation(summary = "Realizar login")
    public ResponseEntity<AutentificacaoResponseDTO> login(@Valid @RequestBody AutentificacaoRequestDTO autentificacaoRequestDTO) {
        AutentificacaoResponseDTO autentificacaoResponseDTO = autentificacaoService.login(autentificacaoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(autentificacaoResponseDTO);
    }

}
