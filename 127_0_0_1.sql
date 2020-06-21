-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 21 Cze 2020, 17:00
-- Wersja serwera: 10.4.11-MariaDB
-- Wersja PHP: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `aplikacja_dietetyczna`
--
CREATE DATABASE IF NOT EXISTS `aplikacja_dietetyczna` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `aplikacja_dietetyczna`;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uzytkownicy`
--

CREATE TABLE `uzytkownicy` (
  `Id` int(11) NOT NULL,
  `Imie` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `Drugie_imie` varchar(50) COLLATE utf8_polish_ci DEFAULT NULL,
  `Nazwisko` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `Data_urodzenia` date NOT NULL,
  `login` varchar(100) COLLATE utf8_polish_ci NOT NULL,
  `salt` varchar(500) COLLATE utf8_polish_ci NOT NULL,
  `hashHasla` varchar(500) COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;


-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `wartosci_odzywcze`
--

CREATE TABLE `wartosci_odzywcze` (
  `Id` int(11) NOT NULL,
  `Nazwa` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
  `Bialko` double NOT NULL,
  `Weglowodany` double NOT NULL,
  `Cukry` double NOT NULL,
  `Tluszcze` double NOT NULL,
  `Tluszcze_nasycone` double NOT NULL,
  `Kalorie` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `wartosci_odzywcze`
--

INSERT INTO `wartosci_odzywcze` (`Id`, `Nazwa`, `Bialko`, `Weglowodany`, `Cukry`, `Tluszcze`, `Tluszcze_nasycone`, `Kalorie`) VALUES
(1, 'Mleko 2% 100g', 3.4, 5, 5, 1, 0.6, 42),
(2, 'Twaróg chudy 100g', 25, 2.2, 2.2, 0.4, 0.4, 98),
(3, 'Ziemniaki 100g', 1.7, 19, 19, 0.2, 0.2, 75),
(4, 'Ryż 100g', 15, 75, 2.5, 1.1, 0.2, 357),
(5, 'Pierś z kurczaka 100g', 21.5, 0, 0, 3.6, 1, 164),
(6, 'Oliwa z oliwek 100g', 0, 0, 0, 100, 14, 884),
(7, 'Truskawka 100g', 0.8, 7.3, 7.3, 0.5, 0.1, 30),
(8, 'Jabłko 100g', 0.26, 13.8, 13.8, 0.17, 0.05, 52),
(9, 'Pomidor 100g', 1, 3.7, 2, 0.3, 0.1, 16),
(10, 'Jajko kurze 100g', 10.9, 0.7, 0, 0.2, 0.1, 52);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `uzytkownicy`
--
ALTER TABLE `uzytkownicy`
  ADD PRIMARY KEY (`Id`);

--
-- Indeksy dla tabeli `wartosci_odzywcze`
--
ALTER TABLE `wartosci_odzywcze`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `uzytkownicy`
--
ALTER TABLE `uzytkownicy`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT dla tabeli `wartosci_odzywcze`
--
ALTER TABLE `wartosci_odzywcze`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
