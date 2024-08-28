CREATE TABLE parking_spaces (
    id INT AUTO_INCREMENT PRIMARY KEY,
    number INT NOT NULL,
    type ENUM('GENERAL', 'MONTHLY_SUBSCRIBER') NOT NULL,
    occupied BOOLEAN NOT NULL DEFAULT FALSE,
    UNIQUE (number)
);

 DROP TABLE parking_spaces;

SELECT * FROM parking_spaces;

UPDATE parking_spaces SET occupied = TRUE WHERE number IN  (60, 49, 21);