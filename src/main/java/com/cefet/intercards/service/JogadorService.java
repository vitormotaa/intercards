package com.cefet.intercards.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.intercards.dto.JogadorRequestDTO;
import com.cefet.intercards.dto.JogadorResponseDTO;
import com.cefet.intercards.entity.Equipe;
import com.cefet.intercards.entity.Jogador;
import com.cefet.intercards.repository.EquipeRepository;
import com.cefet.intercards.repository.JogadorRepository;
import com.cefet.intercards.exception.ResourceNotFoundException;
import com.cefet.intercards.exception.BusinessException;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private EquipeRepository equipeRepository;

    // retorna todos os jogadores
    @Transactional(readOnly = true)
    public List<JogadorResponseDTO> listar() {
        List<Jogador> jogadores = jogadorRepository.findAll();
        return jogadores.stream().map(JogadorResponseDTO::new).toList();
    }

    // retorna um jogador por id
    @Transactional(readOnly = true)
    public JogadorResponseDTO buscarPorId(Long id) {
        Jogador jogador = jogadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jogador não encontrado. Id: " + id));
        return new JogadorResponseDTO(jogador);
    }

    // insere um jogador
    @Transactional()
    public JogadorResponseDTO inserir(JogadorRequestDTO dto) {
        if (jogadorRepository.existsByNumero(dto.getNumero())) {
            throw new BusinessException("Já existe um jogador com esse número.");
        }

        Equipe equipe = equipeRepository.findById(dto.getEquipeId())
                .orElseThrow(() -> new ResourceNotFoundException("Equipe não encontrada. Id: " + dto.getEquipeId()));

        List<Equipe> equipesModalidade = equipeRepository.findAllByModalidadeId(equipe.getModalidade().getId());
        int contador = 0;
        for (int i = 0; i < equipesModalidade.size(); i++) {
            contador += jogadorRepository.countByEquipeId(equipesModalidade.get(i).getId());
        }

        if (contador < equipe.getModalidade().getLimiteJogadores()) {
            Jogador jogador = new Jogador();
            jogador.setNome(dto.getNome());
            jogador.setNumero(dto.getNumero());
            jogador.setEquipe(equipe);
            jogador.setUrlImagem(dto.getUrlImagem());
            return new JogadorResponseDTO(jogadorRepository.save(jogador));
        } else {
            throw new BusinessException("Essa modalidade já atingiu o número máximo de jogadores.");
        }
    }

    // atualiza um jogador
    // FLAG: fazer a verificação aqui pro número de jogadores máximos na atualização
    @Transactional
    public JogadorResponseDTO atualizar(Long id, JogadorRequestDTO dto) {
        Jogador jogador = jogadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jogador não encontrado. Id: " + id));

        if (jogadorRepository.existsByNumeroAndIdNot(dto.getNumero(), id)) {
            throw new BusinessException("Já existe um jogador com esse número.");
        }

        Equipe equipe = equipeRepository.findById(dto.getEquipeId())
                .orElseThrow(() -> new ResourceNotFoundException("Equipe não encontrada. Id: " + dto.getEquipeId()));

        List<Equipe> equipesModalidade = equipeRepository.findAllByModalidadeId(equipe.getModalidade().getId());
        int contador = 0;
        for (int i = 0; i < equipesModalidade.size(); i++) {
            contador += jogadorRepository.countByEquipeId(equipesModalidade.get(i).getId());
        }

        if (contador < equipe.getModalidade().getLimiteJogadores()) {
            jogador.setNumero(dto.getNumero());
            jogador.setNome(dto.getNome());
            jogador.setEquipe(equipe);
            jogador.setUrlImagem(dto.getUrlImagem());
            return new JogadorResponseDTO(jogadorRepository.save(jogador));
        } else {
            throw new BusinessException("Essa modalidade já atingiu o número máximo de jogadores.");
        }
    }

    // deleta um jogador
    @Transactional
    public void excluir(Long id) {
        if (!jogadorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Jogador não encontrado. Id: " + id);
        }
        jogadorRepository.deleteById(id);
    }

}
