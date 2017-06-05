-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Lun 05 Juin 2017 à 20:24
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `deposit`
--

-- --------------------------------------------------------

--
-- Structure de la table `assoc_reparation_panne`
--

CREATE TABLE IF NOT EXISTS `assoc_reparation_panne` (
  `id_reparation` int(11) NOT NULL,
  `id_panne` int(11) NOT NULL,
  PRIMARY KEY (`id_reparation`,`id_panne`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE IF NOT EXISTS `commande` (
  `num_commande` int(11) NOT NULL AUTO_INCREMENT,
  `date_commande` datetime NOT NULL,
  `prix_commande_HT` decimal(15,3) DEFAULT NULL,
  `taxe_commande` decimal(15,3) DEFAULT NULL,
  `prix_commande_TTC` decimal(15,3) DEFAULT NULL,
  `id_technicien` int(11) DEFAULT NULL,
  PRIMARY KEY (`num_commande`),
  KEY `FK_Commandes_id_technicien` (`id_technicien`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `etre_composee_de`
--

CREATE TABLE IF NOT EXISTS `etre_composee_de` (
  `quantite_pieces` int(11) DEFAULT NULL,
  `reference_fournisseur` varchar(50) DEFAULT NULL,
  `prix_u_HT_piece` decimal(15,3) DEFAULT NULL,
  `taxe_u_piece` decimal(15,3) DEFAULT NULL,
  `prix_u_TTC_piece` decimal(15,3) DEFAULT NULL,
  `prix_total_TTC_piece` decimal(15,3) DEFAULT NULL,
  `num_commande` int(11) NOT NULL,
  `id_piece` int(11) NOT NULL,
  PRIMARY KEY (`num_commande`,`id_piece`),
  KEY `FK_Etre_composee_de_id_piece` (`id_piece`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `etre_montable_sur`
--

CREATE TABLE IF NOT EXISTS `etre_montable_sur` (
  `id_piece` int(11) NOT NULL,
  `id_vehicule` int(11) NOT NULL,
  PRIMARY KEY (`id_piece`,`id_vehicule`),
  KEY `FK_Etre_montable_sur_id_vehicule` (`id_vehicule`),
  KEY `etre_montable_sur_id_piece_index` (`id_piece`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `necessiter`
--

CREATE TABLE IF NOT EXISTS `necessiter` (
  `id_panne` int(11) NOT NULL,
  `id_piece` int(11) NOT NULL,
  `qte_necessaire` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_panne`,`id_piece`),
  KEY `FK_Necessiter_id_piece` (`id_piece`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `necessiter`
--

INSERT INTO `necessiter` (`id_panne`, `id_piece`, `qte_necessaire`) VALUES
(1, 1, 3),
(1, 2, 2),
(2, 1, 16);

-- --------------------------------------------------------

--
-- Structure de la table `occuper`
--

CREATE TABLE IF NOT EXISTS `occuper` (
  `id_place` int(11) NOT NULL,
  `id_vehicule` int(11) NOT NULL,
  PRIMARY KEY (`id_vehicule`,`id_place`),
  KEY `FK_Occuper_id_place` (`id_place`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `panne`
--

CREATE TABLE IF NOT EXISTS `panne` (
  `id_panne` int(11) NOT NULL AUTO_INCREMENT,
  `intitule` varchar(50) DEFAULT NULL,
  `type_panne` int(11) DEFAULT NULL,
  `descriptif_protocole` text,
  `temps_estime` time DEFAULT NULL,
  PRIMARY KEY (`id_panne`),
  UNIQUE KEY `pannes_id_panne_uindex` (`id_panne`),
  KEY `pannes_type_panne_index` (`type_panne`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `panne`
--

INSERT INTO `panne` (`id_panne`, `intitule`, `type_panne`, `descriptif_protocole`, `temps_estime`) VALUES
(1, 'PNEU CREVE', 3, NULL, NULL),
(2, 'PAREBRISE CASSE', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `piece`
--

CREATE TABLE IF NOT EXISTS `piece` (
  `id_piece` int(11) NOT NULL AUTO_INCREMENT,
  `libelle_piece` varchar(50) DEFAULT NULL,
  `fabricant` varchar(25) DEFAULT NULL,
  `valeur_piece` decimal(15,3) DEFAULT NULL,
  `qte_dispo` int(11) NOT NULL,
  PRIMARY KEY (`id_piece`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Contenu de la table `piece`
--

INSERT INTO `piece` (`id_piece`, `libelle_piece`, `fabricant`, `valeur_piece`, `qte_dispo`) VALUES
(1, 'PNEU', 'MICHELIN', '30.000', 143),
(2, 'MOTEUR', 'ELRUN', '1430.000', 66),
(3, 'AMPOULE', 'PHILIPS', '4.000', 100),
(4, 'RETROVISEUR', 'RETROGLASS', '19.000', 50),
(5, 'PARE-BRISE', 'CARGLASS', '30.000', 120),
(6, 'CLE', 'ELRUN', '14.000', 24),
(7, 'VOLANT', 'ELRUN', '32.000', 12),
(8, 'BATTERIE', 'ELRUN', '60.000', 20),
(9, 'CÂBLE', 'ELECC2', '1.000', 80),
(10, 'ANTENNE', 'PHILIPS', '3.000', 12),
(11, 'SIEGE', 'ELRUN', '96.000', 8),
(12, 'CEINTURE', 'DRIVSAF', '25.000', 15),
(13, 'POIGNÉE PORTE', 'ELRUN', '12.000', 8),
(14, 'EMBRAYAGE', 'ELRUN', '170.000', 4),
(15, 'BOÎTE DE VITESSE', 'ELRUN', '302.000', 6),
(16, 'BOÎTE À GANTS', 'ELRUN', '13.000', 2),
(17, 'PLAQUETTE FREIN', 'SKRT', '20.000', 26),
(18, 'TAPIS SOL', 'ALC', '10.000', 45);

-- --------------------------------------------------------

--
-- Structure de la table `place`
--

CREATE TABLE IF NOT EXISTS `place` (
  `id_place` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_place`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Contenu de la table `place`
--

INSERT INTO `place` (`id_place`) VALUES
(-1),
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9),
(10),
(11),
(12),
(13),
(14);

-- --------------------------------------------------------

--
-- Structure de la table `reparation_histo_temps`
--

CREATE TABLE IF NOT EXISTS `reparation_histo_temps` (
  `id_reparation` int(11) NOT NULL,
  `statut` varchar(20) NOT NULL,
  `date_debut` timestamp NULL DEFAULT NULL,
  `date_fin` timestamp NULL DEFAULT NULL,
  `id_place` int(11) NOT NULL,
  KEY `id_reparation` (`id_reparation`),
  KEY `id_place` (`id_place`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `reparation_histo_temps`
--

INSERT INTO `reparation_histo_temps` (`id_reparation`, `statut`, `date_debut`, `date_fin`, `id_place`) VALUES
(1, 'diagnostiqué', '2017-05-31 22:00:00', '2017-06-05 08:02:49', 2),
(1, 'réparé', '2017-06-05 08:02:49', NULL, 2);

-- --------------------------------------------------------

--
-- Structure de la table `reparer`
--

CREATE TABLE IF NOT EXISTS `reparer` (
  `id_reparation` int(11) NOT NULL AUTO_INCREMENT,
  `id_technicien` int(11) NOT NULL,
  `id_place` int(11) DEFAULT NULL,
  `id_vehicule` int(11) NOT NULL,
  `statut_reparation` varchar(25) DEFAULT NULL,
  `priorite_reparation` tinyint(4) DEFAULT NULL,
  `commentaire` varchar(250) DEFAULT NULL,
  `date_entree_vehicule` datetime DEFAULT NULL,
  `date_sortie_vehicule` datetime DEFAULT NULL,
  PRIMARY KEY (`id_reparation`),
  UNIQUE KEY `reparer_id_reparation_uindex` (`id_reparation`),
  KEY `reparation_place_id_place_fk` (`id_place`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `reparer`
--

INSERT INTO `reparer` (`id_reparation`, `id_technicien`, `id_place`, `id_vehicule`, `statut_reparation`, `priorite_reparation`, `commentaire`, `date_entree_vehicule`, `date_sortie_vehicule`) VALUES
(1, 0, -1, 1, 'réparation en cours', NULL, 'C le pre a pé é boi', '0000-00-00 00:00:00', NULL),
(2, 1, 1, 1, NULL, NULL, NULL, NULL, NULL),
(3, 2, 3, 2, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `technicien`
--

CREATE TABLE IF NOT EXISTS `technicien` (
  `id_technicen` int(11) NOT NULL AUTO_INCREMENT,
  `prenom` varchar(25) NOT NULL,
  `nom` varchar(25) NOT NULL,
  `num_tel` varchar(10) NOT NULL,
  `mot_de_passe` varchar(10) NOT NULL,
  `rights` enum('MANAGER','TECHNICIAN') NOT NULL,
  PRIMARY KEY (`id_technicen`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Contenu de la table `technicien`
--

INSERT INTO `technicien` (`id_technicen`, `prenom`, `nom`, `num_tel`, `mot_de_passe`, `rights`) VALUES
(1, 'Ambre', 'WODLING', '0678984565', 'password', 'MANAGER'),
(2, 'Louis', 'ENDELICHER', '0783456734', 'louis', 'TECHNICIAN'),
(3, 'Djouher', 'KAHEL', '0786745634', 'djodjo', 'TECHNICIAN'),
(4, 'Noël', 'DIRIL', '0784653827', 'noel', 'TECHNICIAN'),
(5, 'Evan', 'SUAU', '0786475637', 'evan', 'TECHNICIAN'),
(6, 'Ahn Tu', 'LE', '0789475839', 'ahntu', 'TECHNICIAN'),
(7, 'Charles', 'BAUDELAIRE', '0789574837', 'charles', 'TECHNICIAN'),
(8, 'Jean-Philippe', 'JAWORSKI', '0789576849', 'jeanphilip', 'TECHNICIAN'),
(9, 'Antoine', 'DE SAINT EXUPERY', '0789576938', 'antoine', 'TECHNICIAN'),
(10, 'Robert', 'JORDAN', '0657489342', 'robert', 'TECHNICIAN');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `assoc_reparation_panne`
--
ALTER TABLE `assoc_reparation_panne`
  ADD CONSTRAINT `assoc_reparation_panne_panne_id_panne_fk` FOREIGN KEY (`id_reparation`) REFERENCES `panne` (`id_panne`);

--
-- Contraintes pour la table `etre_composee_de`
--
ALTER TABLE `etre_composee_de`
  ADD CONSTRAINT `FK_Etre_composee_de_id_piece` FOREIGN KEY (`id_piece`) REFERENCES `piece` (`id_piece`),
  ADD CONSTRAINT `FK_Etre_composee_de_num_commande` FOREIGN KEY (`num_commande`) REFERENCES `commande` (`num_commande`);

--
-- Contraintes pour la table `etre_montable_sur`
--
ALTER TABLE `etre_montable_sur`
  ADD CONSTRAINT `FK_Etre_montable_sur_id_piece` FOREIGN KEY (`id_piece`) REFERENCES `piece` (`id_piece`);

--
-- Contraintes pour la table `necessiter`
--
ALTER TABLE `necessiter`
  ADD CONSTRAINT `FK_Necessiter_id_piece` FOREIGN KEY (`id_piece`) REFERENCES `piece` (`id_piece`),
  ADD CONSTRAINT `FK_Necessiter_id_panne` FOREIGN KEY (`id_panne`) REFERENCES `panne` (`id_panne`);

--
-- Contraintes pour la table `occuper`
--
ALTER TABLE `occuper`
  ADD CONSTRAINT `FK_Occuper_id_place` FOREIGN KEY (`id_place`) REFERENCES `place` (`id_place`);

--
-- Contraintes pour la table `reparation_histo_temps`
--
ALTER TABLE `reparation_histo_temps`
  ADD CONSTRAINT `id_place` FOREIGN KEY (`id_place`) REFERENCES `place` (`id_place`),
  ADD CONSTRAINT `id_reparation` FOREIGN KEY (`id_reparation`) REFERENCES `reparer` (`id_reparation`);

--
-- Contraintes pour la table `reparer`
--
ALTER TABLE `reparer`
  ADD CONSTRAINT `reparation_place_id_place_fk` FOREIGN KEY (`id_place`) REFERENCES `place` (`id_place`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
