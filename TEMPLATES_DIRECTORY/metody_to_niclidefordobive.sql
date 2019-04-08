-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time:  8 апр 2019 в 19:19
-- Версия на сървъра: 10.1.16-MariaDB
-- PHP Version: 7.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rhdbase`
--

-- --------------------------------------------------------

--
-- Структура на таблица `metody_to_niclidefordobive`
--

CREATE TABLE `metody_to_niclidefordobive` (
  `ID_METODY_TO_NICLIDEFORDOBIVE` int(11) NOT NULL,
  `METODY_ID_METODY` int(11) DEFAULT NULL,
  `NUCLIDE_ID_NUCLIDE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Схема на данните от таблица `metody_to_niclidefordobive`
--

INSERT INTO `metody_to_niclidefordobive` (`ID_METODY_TO_NICLIDEFORDOBIVE`, `METODY_ID_METODY`, `NUCLIDE_ID_NUCLIDE`) VALUES
(1, 1, 66),
(2, 1, 65),
(3, 1, 67),
(4, 3, 47),
(5, 1, 55),
(6, 7, 65),
(7, 7, 66),
(8, 7, 67),
(9, 7, 55),
(10, 10, 63),
(11, 10, 55),
(12, 12, 62),
(13, 13, 46),
(14, 14, 46),
(15, 15, 48),
(16, 15, 49),
(17, 15, 64),
(18, 15, 50);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `metody_to_niclidefordobive`
--
ALTER TABLE `metody_to_niclidefordobive`
  ADD PRIMARY KEY (`ID_METODY_TO_NICLIDEFORDOBIVE`),
  ADD KEY `FK_METODY_TO_NICLIDEFORDOBIVE_NUCLIDE_ID_NUCLIDE` (`NUCLIDE_ID_NUCLIDE`),
  ADD KEY `FK_METODY_TO_NICLIDEFORDOBIVE_METODY_ID_METODY` (`METODY_ID_METODY`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `metody_to_niclidefordobive`
--
ALTER TABLE `metody_to_niclidefordobive`
  MODIFY `ID_METODY_TO_NICLIDEFORDOBIVE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- Ограничения за дъмпнати таблици
--

--
-- Ограничения за таблица `metody_to_niclidefordobive`
--
ALTER TABLE `metody_to_niclidefordobive`
  ADD CONSTRAINT `FK_METODY_TO_NICLIDEFORDOBIVE_METODY_ID_METODY` FOREIGN KEY (`METODY_ID_METODY`) REFERENCES `metody` (`ID_METODY`),
  ADD CONSTRAINT `FK_METODY_TO_NICLIDEFORDOBIVE_NUCLIDE_ID_NUCLIDE` FOREIGN KEY (`NUCLIDE_ID_NUCLIDE`) REFERENCES `nuclide` (`ID_NUCLIDE`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
