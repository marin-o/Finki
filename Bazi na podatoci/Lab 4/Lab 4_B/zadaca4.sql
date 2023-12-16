CREATE TABLE IF NOT EXISTS Korisnik(
k_ime VARCHAR(255),
ime VARCHAR(255),
prezime VARCHAR(255), 
tip VARCHAR(255), 
pretplata VARCHAR(255), 
datum_reg CHAR(10), 
tel_broj VARCHAR(255), 
email VARCHAR(255),
CONSTRAINT PK_Korisnik
    PRIMARY KEY(k_ime)
);

CREATE TABLE IF NOT EXISTS Premium_korisnik(
k_ime VARCHAR(255), 
datum CHAR(10), 
procent_popust VARCHAR(255),
CONSTRAINT PK_Premium_korisnik
    PRIMARY KEY(k_ime),
CONSTRAINT FK_Premium_korisnik
    FOREIGN KEY(k_ime)
    REFERENCES Korisnik(k_ime)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Profil(
k_ime VARCHAR(255), 
ime VARCHAR(255),
datum CHAR(10),
CONSTRAINT PK_Profil
    PRIMARY KEY(k_ime,ime),
CONSTRAINT FK_Profil
    FOREIGN KEY(k_ime)
    REFERENCES Korisnik(k_ime)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Video_zapis(
naslov VARCHAR(255), 
jazik VARCHAR(255), 
vremetraenje INTEGER, 
datum_d CHAR(10), 
datum_p CHAR(10),
CONSTRAINT PK_Video_zapis
    PRIMARY KEY(naslov)
CHECK (datum_d <= CURRENT_DATE AND datum_p <= CURRENT_DATE)
);

CREATE TABLE IF NOT EXISTS Video_zapis_zanr(
naslov VARCHAR(255),
zanr VARCHAR(255),
CONSTRAINT PK_zanr
    PRIMARY KEY(naslov, zanr),
CONSTRAINT FK_naslov_zanr
    FOREIGN KEY(naslov)
    REFERENCES Video_zapis(naslov)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Lista_zelbi(
naslov VARCHAR(255),
k_ime VARCHAR(255), 
ime VARCHAR(255),
CONSTRAINT PK_zelbi
    PRIMARY KEY(naslov,k_ime,ime),
CONSTRAINT FK_zelbi_naslov
    FOREIGN KEY(naslov)
    REFERENCES Video_zapis(naslov)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
CONSTRAINT FK_zelbi_profil
    FOREIGN KEY(k_ime,ime)
    REFERENCES Profil(k_ime,ime)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Preporaka(
ID VARCHAR(255), 
k_ime_od VARCHAR(255), 
k_ime_na VARCHAR(255), 
naslov VARCHAR(255), 
datum char(10), 
komentar VARCHAR(255), 
ocena INTEGER,
CONSTRAINT PK_Preporaka
    PRIMARY KEY(ID),
CONSTRAINT FK_k_ime_od
    FOREIGN KEY(k_ime_od)
    REFERENCES Korisnik(k_ime)
    ON UPDATE CASCADE
    ON DELETE SET NULL,
CONSTRAINT FK_k_ime_na
    FOREIGN KEY(k_ime_na)
    REFERENCES Korisnik(k_ime)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
CONSTRAINT korisnik_od_notequal_korisnik_na
    CHECK (k_ime_od <> k_ime_na),
CONSTRAINT FK_naslov
    FOREIGN KEY(naslov)
    REFERENCES Video_zapis(naslov)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
CHECK (datum <= CURRENT_DATE)
)
