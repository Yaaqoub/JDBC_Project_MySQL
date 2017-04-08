-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 07, 2017 at 09:11 PM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projet`
--

-- --------------------------------------------------------

--
-- Table structure for table `achat`
--

CREATE TABLE `achat` (
  `date_achat` date NOT NULL,
  `id_utilisateur` int(11) NOT NULL,
  `id_pack_jeton` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `achat`
--

INSERT INTO `achat` (`date_achat`, `id_utilisateur`, `id_pack_jeton`) VALUES
('2011-01-01', 1, 1),
('2017-04-07', 1, 1),
('2017-04-27', 2, 2),
('2018-07-20', 2, 2),
('2012-02-02', 3, 5),
('2013-03-03', 4, 1),
('2014-04-04', 5, 1),
('2015-05-05', 6, 1),
('2016-06-06', 7, 1);

-- --------------------------------------------------------

--
-- Table structure for table `enchere`
--

CREATE TABLE `enchere` (
  `id` int(11) NOT NULL,
  `id_produit` int(11) DEFAULT NULL,
  `reference` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  `cout` int(11) DEFAULT NULL,
  `statut` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `enchere`
--

INSERT INTO `enchere` (`id`, `id_produit`, `reference`, `date_debut`, `date_fin`, `cout`, `statut`) VALUES
(1, 3, 'zerfSQFsqdQSD', '2017-04-12', '2017-04-28', 111111, 'SDQFQSFDQSDFQSDF'),
(2, 6, 'qsdQSDSQDSF', '2017-04-03', '2017-04-15', 22222222, 'SFGSDFqsrfzefa');

-- --------------------------------------------------------

--
-- Table structure for table `pack_jeton`
--

CREATE TABLE `pack_jeton` (
  `id` int(11) NOT NULL,
  `nb_jetons` int(11) DEFAULT NULL,
  `description` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `pack_jeton`
--

INSERT INTO `pack_jeton` (`id`, `nb_jetons`, `description`) VALUES
(1, 50, 'designationtest'),
(2, 5, 'aaaaaaaaaaaaaa'),
(3, 9, 'zzzzzzzzzzzzzz'),
(4, 6, 'ttttttttt'),
(5, 7, 'dsgfdgsdfgsdfg'),
(6, 8, 'hgkfgdhsdfhg'),
(7, 10, 'sgfdgsr'),
(8, 11, 'dsfhtyh'),
(9, 55, 'pkslpfdg');

-- --------------------------------------------------------

--
-- Table structure for table `produit`
--

CREATE TABLE `produit` (
  `id` int(11) NOT NULL,
  `reference` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `designation` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `prix` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `produit`
--

INSERT INTO `produit` (`id`, `reference`, `designation`, `prix`) VALUES
(1, 'testproduit', 'designationtest', 2000),
(2, 'aaaaaaaaaa', 'aaaaaaaaaa', 222222222),
(3, 'zzzzzzzzzzzzzz', 'zzzzzzzzzzzz', 33333333),
(4, 'rrrrrrrr', 'rrrrrrrrrr', 34444),
(5, 'tttttttt', 'tttttttt', 555555),
(6, 'yyyyyyyyyyyyyy', 'yyyyyyyyyy', 66666),
(7, 'uuuuuuuuuuuu', 'uuuuuuuuuuu', 7777),
(8, 'iiiiiiiiiiiiiii', 'iiiiiiiiiiiii', 88888),
(9, 'ooooooooooooo', 'oooooooooooooo', 9999999);

-- --------------------------------------------------------

--
-- Table structure for table `proposition`
--

CREATE TABLE `proposition` (
  `prix` int(11) NOT NULL,
  `id_enchere` int(11) NOT NULL,
  `id_utilisateur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `proposition`
--

INSERT INTO `proposition` (`prix`, `id_enchere`, `id_utilisateur`) VALUES
(50000, 1, 5),
(44444444, 2, 4);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `niveau` int(11) NOT NULL,
  `intitule` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `niveau`, `intitule`) VALUES
(1, 1, 'admin'),
(2, 2, 'user'),
(3, 3, 'autres');

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL,
  `id_role` int(11) DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `pseudo` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `id_role`, `email`, `pseudo`, `password`) VALUES
(1, 1, 'yaaqoub@gmail.com', 'yaaqoub', '123456'),
(2, 1, 'eeerere@gmail.com', 'rrrrrrrr', '123aaaa'),
(3, 2, 'med@gmail.com', 'mohamed', 'fdf896'),
(4, 3, 'ali@gmail.com', 'ali', 'aaa785'),
(5, 2, 'jhon@gmail.com', 'jhon', 'sdfs55'),
(6, 1, 'tata@gmail.com', 'tata', 'vbg222'),
(7, 1, 'toto@gmail.com', 'toto', 'wxc333');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `achat`
--
ALTER TABLE `achat`
  ADD PRIMARY KEY (`date_achat`,`id_utilisateur`,`id_pack_jeton`),
  ADD KEY `FK_achat_id_utilisateur` (`id_utilisateur`),
  ADD KEY `FK_achat_id_pack_jeton` (`id_pack_jeton`);

--
-- Indexes for table `enchere`
--
ALTER TABLE `enchere`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `reference` (`reference`),
  ADD KEY `FK_enchere_id_produit` (`id_produit`);

--
-- Indexes for table `pack_jeton`
--
ALTER TABLE `pack_jeton`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nb_jetons` (`nb_jetons`);

--
-- Indexes for table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `reference` (`reference`);

--
-- Indexes for table `proposition`
--
ALTER TABLE `proposition`
  ADD PRIMARY KEY (`prix`,`id_enchere`,`id_utilisateur`),
  ADD KEY `FK_proposition_id_enchere` (`id_enchere`),
  ADD KEY `FK_proposition_id_utilisateur` (`id_utilisateur`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `niveau` (`niveau`);

--
-- Indexes for table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `FK_utilisateur_id_role` (`id_role`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `enchere`
--
ALTER TABLE `enchere`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `pack_jeton`
--
ALTER TABLE `pack_jeton`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `produit`
--
ALTER TABLE `produit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `achat`
--
ALTER TABLE `achat`
  ADD CONSTRAINT `FK_26A9845615B16EDD` FOREIGN KEY (`id_pack_jeton`) REFERENCES `pack_jeton` (`id`),
  ADD CONSTRAINT `FK_26A9845650EAE44` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id`);

--
-- Constraints for table `enchere`
--
ALTER TABLE `enchere`
  ADD CONSTRAINT `FK_38D1870FF7384557` FOREIGN KEY (`id_produit`) REFERENCES `produit` (`id`);

--
-- Constraints for table `proposition`
--
ALTER TABLE `proposition`
  ADD CONSTRAINT `FK_C7CDC35350EAE44` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `FK_C7CDC353E64C2E7F` FOREIGN KEY (`id_enchere`) REFERENCES `enchere` (`id`);

--
-- Constraints for table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `FK_1D1C63B3DC499668` FOREIGN KEY (`id_role`) REFERENCES `role` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
