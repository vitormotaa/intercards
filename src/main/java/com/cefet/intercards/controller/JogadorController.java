package com.cefet.intercards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cefet.intercards.dto.JogadorRequestDTO;
import com.cefet.intercards.dto.JogadorResponseDTO;
import com.cefet.intercards.service.JogadorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/jogadores")
@Tag(name = "Jogador")
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    @GetMapping
    @Operation(summary = "Listar jogadores")
    public ResponseEntity<List<JogadorResponseDTO>> listar(){
        List<JogadorResponseDTO> jogadores = jogadorService.listar();
        return ResponseEntity.ok(jogadores);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Bsucar jogador por ID")
    public ResponseEntity<JogadorResponseDTO> buscarPorId(@PathVariable Long id){
        JogadorResponseDTO jogadorResponseDTO = jogadorService.buscarPorId(id);
        return ResponseEntity.ok(jogadorResponseDTO);
    }

    @PostMapping
    @Operation(summary = "Cadastrar jogador")
    public ResponseEntity<JogadorResponseDTO> inserir(@Valid @RequestBody JogadorRequestDTO jogadorRequestDTO){
        JogadorResponseDTO jogadorResponseDTO = jogadorService.inserir(jogadorRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(jogadorResponseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar jogador")
    public ResponseEntity<JogadorResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody JogadorRequestDTO jogadorRequestDTO) {

    	JogadorResponseDTO jogadorResponseDTO = jogadorService.atualizar(id, jogadorRequestDTO);

        return ResponseEntity.ok(jogadorResponseDTO);
    }    

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir jogador")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        jogadorService.excluir(id);
        return ResponseEntity.noContent().build();
    }


}
