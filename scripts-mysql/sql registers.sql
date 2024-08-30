USE sistemagaragem;

CREATE TABLE registers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dateOfEntry DATETIME NOT NULL,
    dateOfExit DATETIME,
    vehicleId INT
);

SELECT * FROM registers;
-- DROP TABLE registers;