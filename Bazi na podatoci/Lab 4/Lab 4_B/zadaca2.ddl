CREATE TABLE IF NOT EXISTS Lice(
id INTEGER,
mbr INTEGER, 
ime VARCHAR(255), 
prezime VARCHAR(255),
data_r CHAR(10), 
vozrast INTEGER, 
pol VARCHAR(255),
CONSTRAINT PK_Lice
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS Med_lice(
id INTEGER,
staz INTEGER,
CONSTRAINT PK_Med_lice
    PRIMARY KEY(id),
CONSTRAINT FK_Med_lice
    FOREIGN KEY(id)
    REFERENCES Lice(id)
    ON UPDATE CASCADE
    ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS Test(
id INTEGER,
shifra INTEGER, 
tip VARCHAR(255), 
datum CHAR(10), 
rezultat VARCHAR(255), 
laboratorija VARCHAR(255),
CONSTRAINT PK_Test
    PRIMARY KEY(id,shifra),
CONSTRAINT FK_Test
    FOREIGN KEY(id)
    REFERENCES Lice(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
CHECK ((laboratorija <> 'lab-abc') OR (laboratorija = 'lab-abc' AND tip = 'seroloshki'))
);

CREATE TABLE IF NOT EXISTS Vakcina(
shifra INTEGER, 
ime VARCHAR(255), 
proizvoditel VARCHAR(255),
CONSTRAINT PK_Vakcina
    PRIMARY KEY(shifra)
);

CREATE TABLE IF NOT EXISTS Vakcinacija(
id_lice INTEGER,
id_med_lice INTEGER,
shifra_vakcina INTEGER,
CONSTRAINT PK_Vakcinacija
    PRIMARY KEY(id_lice,id_med_lice,shifra_vakcina),
CONSTRAINT FK_lice
    FOREIGN KEY(id_lice)
    REFERENCES Lice(id)
    ON UPDATE CASCADE
    ON DELETE SET NULL,
CONSTRAINT FK_med_lice
    FOREIGN KEY(id_med_lice)
    REFERENCES Med_lice(id)
    ON UPDATE CASCADE
    ON DELETE SET NULL,
CONSTRAINT FK_vakcina
    FOREIGN KEY(shifra_vakcina)
    REFERENCES Vakcina(shifra)
    ON UPDATE CASCADE
    ON DELETE SET NULL,
CHECK (id_lice <> id_med_lice)
);

CREATE TABLE IF NOT EXISTS Vakcinacija_datum(
id_lice INTEGER,
id_med_lice INTEGER,
shifra_vakcina INTEGER,
datum CHAR(10),
CONSTRAINT PK_Vakcinacija_datum
    PRIMARY KEY(id_lice,id_med_lice,shifra_vakcina,datum),
CONSTRAINT FK_id_id_shifra
    FOREIGN KEY(id_lice,id_med_lice,shifra_vakcina)
    REFERENCES Vakcinacija(id_lice,id_med_lice,shifra_vakcina)
    ON UPDATE CASCADE
    ON DELETE SET NULL
);