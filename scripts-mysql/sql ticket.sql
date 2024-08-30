USE sistemagaragem;

CREATE TABLE tickets (
    id INT AUTO_INCREMENT PRIMARY KEY,
    plate VARCHAR(20) NOT NULL,
    entryTime DATETIME NOT NULL,
    exitTime DATETIME,
    entryBarrier INT,
    exitBarrier INT,
    slotNumber INT,
    amountDue DECIMAL(10, 2) -- Isso faz com que seja decimal
);

-- DROP tickets;

SELECT * FROM tickets;