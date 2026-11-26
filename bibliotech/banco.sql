-- BiblioTech: a estrutura do banco de dados.
-- Rodar como administrador, no terminal Linux, de dentro da pasta bibliotech:
--     sudo mariadb < banco.sql
-- Pode rodar quantas vezes quiser: cada comando so faz o que ainda falta.
-- O usuario da aplicacao e a senha dele NAO moram aqui: veja o README.

CREATE DATABASE IF NOT EXISTS bibliotech
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE bibliotech;

CREATE TABLE IF NOT EXISTS livros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    ano INT NOT NULL,
    capa VARCHAR(255) NULL
);

-- Para o banco criado na Aula 42, quando a capa ainda nao existia.
ALTER TABLE livros
    ADD COLUMN IF NOT EXISTS capa VARCHAR(255) NULL;
