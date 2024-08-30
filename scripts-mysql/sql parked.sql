USE sistemagaragem;

CREATE TABLE parked (
    id_vehicle INT,
    id_slot INT,
    UNIQUE (id_vehicle, id_slot),  -- Restrição de unicidade explícita
    FOREIGN KEY (id_vehicle) REFERENCES vehicle(id),
    FOREIGN KEY (id_slot) REFERENCES parking_slots(id)
);

DROP TABLE parked;

SELECT * FROM parked;