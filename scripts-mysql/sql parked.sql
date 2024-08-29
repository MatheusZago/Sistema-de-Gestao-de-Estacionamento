USE sistemagaragem;

CREATE TABLE parked (
    plate VARCHAR(20) NOT NULL,
    dateOfEntry DATETIME NOT NULL,
    numberSlot INT NOT NULL,
    category ENUM('CAR', 'MOTORCYCLE', 'TRUCK') NOT NULL,
    UNIQUE (dateOfEntry, numberSlot)
);

-- DROP parked;

SELECT * FROM parked;