package com.cefet.intercards.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.intercards.entity.Jogador;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {

    boolean existsByNumero(Integer numero);

    boolean existsByNumeroAndIdNot(Integer numero, Long id);

    int countByEquipeId(Long id);
}
