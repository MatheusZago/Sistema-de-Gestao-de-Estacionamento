USE sistemagaragem;

CREATE TABLE tickets (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vehicleId INT NOT NULL,
    plate VARCHAR(20) NOT NULL,
    entryTime DATETIME NOT NULL,
    exitTime DATETIME,
    entryBarrier INT,
    exitBarrier INT,
    slotNumber VARCHAR(20),
    amountDue DECIMAL(10, 2) -- Isso faz com que seja decimal
);

 -- DROP TABLE tickets;

SELECT * FROM tickets;