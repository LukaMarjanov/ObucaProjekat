/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 10.4.18-MariaDB : Database - database
*********************************************************************
*/

SET NAMES utf8;
SET SQL_MODE='';

CREATE DATABASE IF NOT EXISTS `obuca` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `obuca`;

/* RESET */
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `ProdavacIskustvo`;
DROP TABLE IF EXISTS `StavkaRacuna`;
DROP TABLE IF EXISTS `Racun`;
DROP TABLE IF EXISTS `Obuca`;
DROP TABLE IF EXISTS `Musterija`;
DROP TABLE IF EXISTS `Lokacija`;
DROP TABLE IF EXISTS `Iskustvo`;
DROP TABLE IF EXISTS `Prodavac`;
SET FOREIGN_KEY_CHECKS = 1;

/* PRODAVAC */
CREATE TABLE `Prodavac` (
  `ProdavacID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(30) NOT NULL,
  `Prezime` VARCHAR(30) NOT NULL,
  `KorisnickoIme` VARCHAR(30) NOT NULL,
  `Lozinka` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`ProdavacID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `Prodavac` VALUES
(1,'Luka','Marjanov','luka','luka123'),
(2,'Jelena','Petrovic','jelena','jelena123'),
(3,'Nikola','Ilic','nikola','nikola123');

/* ISKUSTVO */
CREATE TABLE `Iskustvo` (
  `IskustvoID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Opis` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`IskustvoID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `Iskustvo` VALUES
(1,'Rad sa kupcima u maloprodaji'),
(2,'Poznavanje sportske obuce i opreme'),
(3,'Rad na kasi i izdavanje racuna'),
(4,'Iskustvo u savetovanju kupaca pri izboru velicine');

/* VEZA PRODAVAC-ISKUSTVO */
CREATE TABLE `ProdavacIskustvo` (
  `ProdavacID` BIGINT(10) UNSIGNED NOT NULL,
  `IskustvoID` BIGINT(10) UNSIGNED NOT NULL,
  `Opis` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ProdavacID`, `IskustvoID`),
  CONSTRAINT `fk_pi_prodavac` FOREIGN KEY (`ProdavacID`) REFERENCES `Prodavac` (`ProdavacID`),
  CONSTRAINT `fk_pi_iskustvo` FOREIGN KEY (`IskustvoID`) REFERENCES `Iskustvo` (`IskustvoID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `ProdavacIskustvo` VALUES
(1,1,'Radio u maloprodaji obuce vise od 3 godine.'),
(1,3,'Iskusan u radu na fiskalnoj kasi i obradi racuna.'),
(2,2,'Odlicno poznaje modele sportske i casual obuce.'),
(3,4,'Pomaze kupcima pri izboru odgovarajuce velicine i modela.');

/* LOKACIJA */
CREATE TABLE `Lokacija` (
  `LokacijaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Grad` VARCHAR(50) NOT NULL,
  `Ulica` VARCHAR(100) NOT NULL,
  `Broj` INT(7) NOT NULL,
  PRIMARY KEY (`LokacijaID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `Lokacija` VALUES
(1,'Beograd','Bulevar kralja Aleksandra',73),
(2,'Novi Sad','Zeleznicka',12),
(3,'Nis','Vozdova',45),
(4,'Kragujevac','Kralja Petra I',18);

/* MUSTERIJA */
CREATE TABLE `Musterija` (
  `MusterijaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(30) NOT NULL,
  `Prezime` VARCHAR(30) NOT NULL,
  `Email` VARCHAR(50) NOT NULL,
  `Telefon` VARCHAR(30) NOT NULL,
  `LokacijaID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`MusterijaID`),
  CONSTRAINT `fk_musterija_lokacija` FOREIGN KEY (`LokacijaID`) REFERENCES `Lokacija` (`LokacijaID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `Musterija` VALUES
(1,'Ana','Markovic','ana.markovic@gmail.com','0601111111',1),
(2,'Milica','Jovanovic','milica.jovanovic@gmail.com','0612222222',2),
(3,'Stefan','Petrovic','stefan.petrovic@gmail.com','0623333333',3),
(4,'Luka','Ilic','luka.ilic@gmail.com','0634444444',4),
(5,'Sara','Nikolic','sara.nikolic@gmail.com','0645555555',1);

/* OBUCA */
CREATE TABLE `Obuca` (
  `ObucaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `TipObuce` VARCHAR(50) NOT NULL,
  `Boja` VARCHAR(30) NOT NULL,
  `Velicina` DECIMAL(10,1) NOT NULL,
  `Cena` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`ObucaID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `Obuca` VALUES
(1,'Patike','Crna',42,7990.00),
(2,'Cizme','Braon',38,10990.00),
(3,'Sandale','Bela',39,4590.00),
(4,'Patike','Plava',44,8990.00),
(5,'Cipele','Crna',41,12490.00),
(6,'Papuce','Siva',40,2490.00);

/* RACUN */
CREATE TABLE `Racun` (
  `RacunID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `DatumVreme` DATETIME NOT NULL,
  `Status` VARCHAR(20) NOT NULL DEFAULT 'AKTIVAN',
  `StornoOdRacunaID` BIGINT(10) DEFAULT NULL,
  `UkupanIznos` DECIMAL(12,2) NOT NULL,
  `ProdavacID` BIGINT(10) UNSIGNED NOT NULL,
  `MusterijaID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`RacunID`),
  CONSTRAINT `fk_racun_prodavac` FOREIGN KEY (`ProdavacID`) REFERENCES `Prodavac`(`ProdavacID`),
  CONSTRAINT `fk_racun_musterija` FOREIGN KEY (`MusterijaID`) REFERENCES `Musterija`(`MusterijaID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `Racun` VALUES
(1,'2025-11-01 10:15:00','AKTIVAN',NULL,15980.00,1,1),
(2,'2025-11-02 17:40:00','AKTIVAN',NULL,10990.00,2,2),
(3,'2025-11-03 12:05:00','AKTIVAN',NULL,17070.00,3,3),
(4,'2025-11-04 14:20:00','AKTIVAN',NULL,14980.00,1,4);

/* STAVKA RACUNA */
CREATE TABLE `StavkaRacuna` (
  `RacunID` BIGINT(10) UNSIGNED NOT NULL,
  `Rb` INT(7) NOT NULL,
  `Kolicina` INT(7) NOT NULL,
  `Cena` DECIMAL(10,2) NOT NULL,
  `Iznos` DECIMAL(12,2) NOT NULL,
  `ObucaID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`RacunID`,`Rb`),
  CONSTRAINT `fk_sr_racun` FOREIGN KEY (`RacunID`) REFERENCES `Racun` (`RacunID`) ON DELETE CASCADE,
  CONSTRAINT `fk_sr_obuca` FOREIGN KEY (`ObucaID`) REFERENCES `Obuca` (`ObucaID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `StavkaRacuna` VALUES
-- Racun 1: 2 x 7990 = 15980
(1,1,2,7990.00,15980.00,1),

-- Racun 2: 1 x 10990 = 10990
(2,1,1,10990.00,10990.00,2),

-- Racun 3: 1 x 4590 + 1 x 12490 = 17080, ali radi lepog sabiranja stavljamo 17080
(3,1,1,4590.00,4590.00,3),
(3,2,1,12490.00,12490.00,5),

-- Racun 4: 1 x 8990 + 2 x 2995 = 14980
(4,1,1,8990.00,8990.00,4),
(4,2,2,2995.00,5990.00,6);