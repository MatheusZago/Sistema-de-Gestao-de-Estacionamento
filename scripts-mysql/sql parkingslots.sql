USE sistemagaragem;

-- CREATE TABLE parking_slots (
--    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
--    type ENUM('GENERAL', 'MONTHLY_SUBSCRIBER') NOT NULL,
--   occupied BOOLEAN NOT NULL DEFAULT FALSE,
--	occupiedby INT NULL,
--    UNIQUE (id),
--    FOREIGN KEY (occupiedby) REFERENCES vehicles(id)
-- );


--  DROP TABLE parking_slots;
SELECT * FROM parking_slots;

UPDATE parking_slots SET occupied = TRUE WHERE id IN  (60, 49, 21);