-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 02, 2024 at 10:06 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projetpoo`
--

-- --------------------------------------------------------

--
-- Table structure for table `article`
--

CREATE TABLE `article` (
  `id_article` int(11) NOT NULL,
  `etat` enum('NA','UE','ACC','REJ') DEFAULT 'NA',
  `id_user` int(11) DEFAULT NULL,
  `titre_article` varchar(255) DEFAULT NULL,
  `fichier_pdf` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `article`
--

INSERT INTO `article` (`id_article`, `etat`, `id_user`, `titre_article`, `fichier_pdf`) VALUES
(11, 'UE', 1244, 'l\'importance du sport', NULL),
(13, 'UE', 1244, 'le sommeil', NULL),
(14, 'UE', 1244, 'la respiration', NULL),
(15, 'ACC', 1244, 'la circulation sanguine', NULL),
(16, 'NA', NULL, 'la circulation sanguine', NULL),
(17, 'UE', 1244, 'le stresse', NULL),
(18, 'NA', NULL, 'le stresse', NULL),
(19, 'NA', NULL, 'jhfkja', NULL),
(20, 'NA', NULL, 'Fumer', NULL),
(21, 'NA', NULL, 'jljljl', NULL),
(22, 'NA', 1244, 'fsfd', NULL),
(23, 'NA', NULL, 'test test', NULL),
(24, 'NA', NULL, 'l apprentissage', NULL),
(25, 'UE', 1250, 'les neurones', NULL),
(26, 'NA', NULL, 'test test', NULL),
(27, 'NA', NULL, 'ljfakjsd', NULL),
(28, 'NA', NULL, 'kjafds', '/home/mohamed/Téléchargements/BPM ACTIVITÉ N°1 .pdf');

-- --------------------------------------------------------

--
-- Table structure for table `auteur`
--

CREATE TABLE `auteur` (
  `id_auteur` int(11) NOT NULL,
  `nom` varchar(100) DEFAULT NULL,
  `prenom` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `institution` varchar(255) DEFAULT NULL,
  `id_conf` int(11) DEFAULT 1111,
  `id_article` int(11) DEFAULT 1111
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `auteur`
--

INSERT INTO `auteur` (`id_auteur`, `nom`, `prenom`, `email`, `institution`, `id_conf`, `id_article`) VALUES
(1, 'intissar', 'boudhraa', 'mghanmi@gmail.com', 'ISG', 1111, 24),
(2, 'ahemd', 'boualeg', 'ahmed@gmial.com', 'FST', 1111, 25),
(3, 'lila', 'laabed', 'lila@gmail.com', 'ESEN', 1111, 25),
(4, 'hedi', 'ben ali', 'hedibenali@gmail.com', 'oxford', 1111, 26),
(5, 'ljfa', 'djslj', 'ljfka', 'ljka', 1111, 28);

-- --------------------------------------------------------

--
-- Table structure for table `conference`
--

CREATE TABLE `conference` (
  `id_conf` int(11) NOT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `president_nom` varchar(255) DEFAULT NULL,
  `president_prenom` varchar(255) DEFAULT NULL,
  `instit_org` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `lieu` varchar(255) DEFAULT NULL,
  `topics` varchar(255) DEFAULT NULL,
  `date_limite_inscription` date DEFAULT NULL,
  `frais_inscription` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `conference`
--

INSERT INTO `conference` (`id_conf`, `titre`, `president_nom`, `president_prenom`, `instit_org`, `date`, `lieu`, `topics`, `date_limite_inscription`, `frais_inscription`) VALUES
(1111, 'conference scientifique', 'Ghanmi', 'Mohamed', 'ISG Tunis', '2024-04-30', 'Tunis', 'human body', '2024-04-25', 60.00),
(1112, 'sports', 'ahmed', 'mahmoud', 'fseg', '2024-05-02', 'manouba', 'the importance of practicing sports', '2024-04-30', 50.00),
(1115, 'books', 'karim ahmed', 'mohsni ', 'esen', '2024-05-02', 'manouba', 'we will talk about the importance of reading books..', '2024-04-23', 70.00),
(1116, 'films', 'arbi', 'ghanmi', 'MAG ', '2024-05-16', 'Tunis', 'talking about films', '2024-05-09', 90.00),
(1117, 'movies ', 'adem', 'mouelhi', 'MAG', '2024-05-10', 'Tunis', 'talking about movies	', '2024-05-01', 30.00),
(1119, 'Smoking', 'mohamed', 'GHANMI', 'Isg', '2024-04-10', 'Tunis', 'Impact of smoking		', '2024-04-08', 50.00);

-- --------------------------------------------------------

--
-- Table structure for table `conferencier`
--

CREATE TABLE `conferencier` (
  `id_conferencier` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `titre_presentation` varchar(255) DEFAULT NULL,
  `institution` varchar(255) DEFAULT NULL,
  `pays_origine` varchar(255) DEFAULT NULL,
  `id_conf` int(11) DEFAULT 1111
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `conferencier`
--

INSERT INTO `conferencier` (`id_conferencier`, `nom`, `titre_presentation`, `institution`, `pays_origine`, `id_conf`) VALUES
(1, 'Mohamed Arbi Ghanmi', 'l importance de meditation', 'FST', 'Tunisie', 1111),
(5, 'ahmed riahi', 'l\'importance du sport', 'FST', 'Tunisie', 1111),
(8, 'ali nouiri', 'test1', 'FST', 'Tunisie', 1111),
(9, 'hedi mahdoui', 'test2', NULL, 'Tunisie', 1111),
(10, 'alya hedhli ', 'test3', 'isg', 'Tunisie', 1111);

-- --------------------------------------------------------

--
-- Table structure for table `inscription`
--

CREATE TABLE `inscription` (
  `id_inscrit` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `institution` varchar(255) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `id_conf` int(11) DEFAULT NULL,
  `methode_paiement` varchar(55) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `inscription`
--

INSERT INTO `inscription` (`id_inscrit`, `nom`, `email`, `institution`, `id_user`, `id_conf`, `methode_paiement`) VALUES
(2, 'yassin khemiri', 'yassin@gmail.com', 'iset rades', 2, NULL, 'virement bancaire'),
(8, 'hedi aabdi', 'hedi@gmail.com', 'FST', 1249, NULL, 'espece');

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id_user` int(11) NOT NULL,
  `type` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `nom` varchar(100) DEFAULT NULL,
  `prenom` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `id_conf` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `utilisateur`
--

INSERT INTO `utilisateur` (`id_user`, `type`, `username`, `pwd`, `nom`, `prenom`, `email`, `id_conf`) VALUES
(1, 'Admin', 'mohamed ghanmi', 'mohamed', 'mohamed', 'ghanmi', 'mohamed@gmail.com', NULL),
(2, 'Participant', 'yassin', 'yassin123', 'yassin', 'khemiri', 'yassin@gmail.com', NULL),
(6, 'Author', 'yassin', 'yassin123', 'yassin', 'bouras', 'yassin1@gmail.com', 1111),
(7, 'Author', 'yassin kh', 'yassin123', 'yassin', 'khemiri', 'yassin3@gmail.com', 1111),
(1244, 'com_sc', 'imed', 'imed123', 'imed', 'gharbi', 'imedgharbi@gmail.com', 1111),
(1245, 'auteur', 'rami', 'rami12', 'rami', 'bel hedi', 'ramibel@gmail.com', 1111),
(1248, 'participant', 'ahmed', 'ahemd234', 'ahmed', 'mahjoub', 'ahmedmahjoub@gmail.com', 1111),
(1249, 'invité', 'hedi aabdi', 'hedi12', 'hedi', 'aabdi', 'hedi@gmail.com', 1111),
(1250, 'com_sc', 'karim heni', 'karim123', 'karim', 'heni', 'karim@gmail.com', NULL),
(1253, 'auteur', 'mahdi ghram', 'mahdi123', 'mahdi', 'ghram', 'mahdighram@gmail.com', 1111),
(1254, 'auteur', 'adel', 'adel123', 'adel', 'bouhlel', 'adel@gmail.com', 1111);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`id_article`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `auteur`
--
ALTER TABLE `auteur`
  ADD PRIMARY KEY (`id_auteur`),
  ADD KEY `id_conf` (`id_conf`),
  ADD KEY `id_article` (`id_article`);

--
-- Indexes for table `conference`
--
ALTER TABLE `conference`
  ADD PRIMARY KEY (`id_conf`);

--
-- Indexes for table `conferencier`
--
ALTER TABLE `conferencier`
  ADD PRIMARY KEY (`id_conferencier`),
  ADD KEY `id_conf` (`id_conf`);

--
-- Indexes for table `inscription`
--
ALTER TABLE `inscription`
  ADD PRIMARY KEY (`id_inscrit`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_conf` (`id_conf`);

--
-- Indexes for table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `id_conf` (`id_conf`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `article`
--
ALTER TABLE `article`
  MODIFY `id_article` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `auteur`
--
ALTER TABLE `auteur`
  MODIFY `id_auteur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `conference`
--
ALTER TABLE `conference`
  MODIFY `id_conf` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1120;

--
-- AUTO_INCREMENT for table `conferencier`
--
ALTER TABLE `conferencier`
  MODIFY `id_conferencier` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `inscription`
--
ALTER TABLE `inscription`
  MODIFY `id_inscrit` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1255;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `article`
--
ALTER TABLE `article`
  ADD CONSTRAINT `fk_utilisateur` FOREIGN KEY (`id_user`) REFERENCES `utilisateur` (`id_user`);

--
-- Constraints for table `auteur`
--
ALTER TABLE `auteur`
  ADD CONSTRAINT `auteur_ibfk_1` FOREIGN KEY (`id_conf`) REFERENCES `conference` (`id_conf`),
  ADD CONSTRAINT `auteur_ibfk_2` FOREIGN KEY (`id_article`) REFERENCES `article` (`id_article`);

--
-- Constraints for table `conferencier`
--
ALTER TABLE `conferencier`
  ADD CONSTRAINT `conferencier_ibfk_1` FOREIGN KEY (`id_conf`) REFERENCES `conference` (`id_conf`);

--
-- Constraints for table `inscription`
--
ALTER TABLE `inscription`
  ADD CONSTRAINT `inscription_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `utilisateur` (`id_user`),
  ADD CONSTRAINT `inscription_ibfk_2` FOREIGN KEY (`id_conf`) REFERENCES `conference` (`id_conf`);

--
-- Constraints for table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `utilisateur_ibfk_1` FOREIGN KEY (`id_conf`) REFERENCES `conference` (`id_conf`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
