USE sistemagaragem;

CREATE TABLE vehicles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    plate VARCHAR(10) NOT NULL UNIQUE,
    category ENUM('CAR', 'MOTORCYCLE', 'TRUCK', 'PUBLIC') NOT NULL
);

-- DROP TABLE vehicles;

SELECT * FROM vehicles;

DELETE FROM vehicles WHERE plate = 'INDI123';