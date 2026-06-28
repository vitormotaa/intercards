-- Modalidades
INSERT INTO tb_modalidade (id, descricao, limite_jogadores) VALUES (1, 'Futsal', 3);
INSERT INTO tb_modalidade (id, descricao, limite_jogadores) VALUES (2, 'Voleibol', 10);
INSERT INTO tb_modalidade (id, descricao, limite_jogadores) VALUES (3, 'Basquetebol', 10);

-- Equipes
INSERT INTO tb_equipe (id, nome, modalidade_id) VALUES (1, 'Tigres', 1);
INSERT INTO tb_equipe (id, nome, modalidade_id) VALUES (2, 'Águias', 2);
INSERT INTO tb_equipe (id, nome, modalidade_id) VALUES (3, 'Leões', 3);

-- Jogadores
INSERT INTO tb_jogador (numero, equipe_id, id, nome) VALUES (10, 1, 1, 'Neymar');
INSERT INTO tb_jogador (numero, equipe_id, id, nome) VALUES (9, 2, 2, 'Vini Jr');
INSERT INTO tb_jogador (numero, equipe_id, id, nome) VALUES (7, 3, 3, 'Paqueta');