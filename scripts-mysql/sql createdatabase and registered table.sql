 CREATE DATABASE sistemagaragem;
 
USE sistemagaragem;

-- DROP TABLE registered;

CREATE TABLE registered (
    id INT AUTO_INCREMENT PRIMARY KEY,
    plate VARCHAR(20) NOT NULL,
    category VARCHAR(20) NOT NULL,
    UNIQUE (plate)  -- Adiciona uma restrição para garantir que a placa seja única
);

SELECT * FROM registered;