-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Client :  localhost:8889
-- Généré le :  Dim 04 Juin 2017 à 14:09
-- Version du serveur :  5.5.42
-- Version de PHP :  7.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `deposit`
--

-- --------------------------------------------------------

--
-- Structure de la table `assoc_reparation_panne`
--

CREATE TABLE `assoc_reparation_panne` (
  `id_reparation` int(11) NOT NULL,
  `id_panne` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `num_commande` int(11) NOT NULL,
  `date_commande` datetime NOT NULL,
  `prix_commande_HT` decimal(15,3) DEFAULT NULL,
  `taxe_commande` decimal(15,3) DEFAULT NULL,
  `prix_commande_TTC` decimal(15,3) DEFAULT NULL,
  `id_technicien` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `etre_composee_de`
--

CREATE TABLE `etre_composee_de` (
  `quantite_pieces` int(11) DEFAULT NULL,
  `reference_fournisseur` varchar(50) DEFAULT NULL,
  `prix_u_HT_piece` decimal(15,3) DEFAULT NULL,
  `taxe_u_piece` decimal(15,3) DEFAULT NULL,
  `prix_u_TTC_piece` decimal(15,3) DEFAULT NULL,
  `prix_total_TTC_piece` decimal(15,3) DEFAULT NULL,
  `num_commande` int(11) NOT NULL,
  `id_piece` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `etre_montable_sur`
--

CREATE TABLE `etre_montable_sur` (
  `id_piece` int(11) NOT NULL,
  `id_vehicule` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `necessiter`
--

CREATE TABLE `necessiter` (
  `id_panne` int(11) NOT NULL,
  `id_piece` int(11) NOT NULL,
  `qte_necessaire` int(11) DEFAULT NULL
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

CREATE TABLE `occuper` (
  `id_place` int(11) NOT NULL,
  `id_vehicule` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `panne`
--

CREATE TABLE `panne` (
  `id_panne` int(11) NOT NULL,
  `intitule` varchar(50) DEFAULT NULL,
  `type_panne` int(11) DEFAULT NULL,
  `descriptif_protocole` text,
  `temps_estime` time DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

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

CREATE TABLE `piece` (
  `id_piece` int(11) NOT NULL,
  `libelle_piece` varchar(50) DEFAULT NULL,
  `fabricant` varchar(25) DEFAULT NULL,
  `valeur_piece` decimal(15,3) DEFAULT NULL,
  `qte_dispo` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `piece`
--

INSERT INTO `piece` (`id_piece`, `libelle_piece`, `fabricant`, `valeur_piece`, `qte_dispo`) VALUES
(1, 'PNEU', 'MICHELIN', '30.000', -73),
(2, 'MOTEUR', 'BMW', '1430.000', 72);

-- --------------------------------------------------------

--
-- Structure de la table `place`
--

CREATE TABLE `place` (
  `id_place` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

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

CREATE TABLE `reparation_histo_temps` (
  `id_reparation` int(11) NOT NULL,
  `statut` varchar(20) NOT NULL,
  `date_debut` timestamp NULL DEFAULT NULL,
  `date_fin` timestamp NULL DEFAULT NULL,
  `id_place` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `reparation_histo_temps`
--

INSERT INTO `reparation_histo_temps` (`id_reparation`, `statut`, `date_debut`, `date_fin`, `id_place`) VALUES
(1, 'diagnostiqué', '2017-06-03 22:00:00', NULL, 2);

-- --------------------------------------------------------

--
-- Structure de la table `reparer`
--

CREATE TABLE `reparer` (
  `id_reparation` int(11) NOT NULL,
  `id_technicien` int(11) NOT NULL,
  `id_place` int(11) DEFAULT NULL,
  `id_vehicule` int(11) NOT NULL,
  `statut_reparation` varchar(25) DEFAULT NULL,
  `priorite_reparation` tinyint(4) DEFAULT NULL,
  `commentaire` varchar(250) DEFAULT NULL,
  `date_entree_vehicule` datetime DEFAULT NULL,
  `date_sortie_vehicule` datetime DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `reparer`
--

INSERT INTO `reparer` (`id_reparation`, `id_technicien`, `id_place`, `id_vehicule`, `statut_reparation`, `priorite_reparation`, `commentaire`, `date_entree_vehicule`, `date_sortie_vehicule`) VALUES
(1, 0, -1, 1, 'réparation en cours', NULL, 'C le pre a pé é boi', '0000-00-00 00:00:00', NULL),
(2, 1, 1, 1, NULL, NULL, NULL, NULL, NULL),
(3, 2, 3, 2, NULL, NULL, NULL, NULL, NULL);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `assoc_reparation_panne`
--
ALTER TABLE `assoc_reparation_panne`
  ADD PRIMARY KEY (`id_reparation`,`id_panne`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`num_commande`),
  ADD KEY `FK_Commandes_id_technicien` (`id_technicien`);

--
-- Index pour la table `etre_composee_de`
--
ALTER TABLE `etre_composee_de`
  ADD PRIMARY KEY (`num_commande`,`id_piece`),
  ADD KEY `FK_Etre_composee_de_id_piece` (`id_piece`);

--
-- Index pour la table `etre_montable_sur`
--
ALTER TABLE `etre_montable_sur`
  ADD PRIMARY KEY (`id_piece`,`id_vehicule`),
  ADD KEY `FK_Etre_montable_sur_id_vehicule` (`id_vehicule`),
  ADD KEY `etre_montable_sur_id_piece_index` (`id_piece`);

--
-- Index pour la table `necessiter`
--
ALTER TABLE `necessiter`
  ADD PRIMARY KEY (`id_panne`,`id_piece`),
  ADD KEY `FK_Necessiter_id_piece` (`id_piece`);

--
-- Index pour la table `occuper`
--
ALTER TABLE `occuper`
  ADD PRIMARY KEY (`id_vehicule`,`id_place`),
  ADD KEY `FK_Occuper_id_place` (`id_place`);

--
-- Index pour la table `panne`
--
ALTER TABLE `panne`
  ADD PRIMARY KEY (`id_panne`),
  ADD UNIQUE KEY `pannes_id_panne_uindex` (`id_panne`),
  ADD KEY `pannes_type_panne_index` (`type_panne`);

--
-- Index pour la table `piece`
--
ALTER TABLE `piece`
  ADD PRIMARY KEY (`id_piece`);

--
-- Index pour la table `place`
--
ALTER TABLE `place`
  ADD PRIMARY KEY (`id_place`);

--
-- Index pour la table `reparation_histo_temps`
--
ALTER TABLE `reparation_histo_temps`
  ADD KEY `id_reparation` (`id_reparation`),
  ADD KEY `id_place` (`id_place`);

--
-- Index pour la table `reparer`
--
ALTER TABLE `reparer`
  ADD PRIMARY KEY (`id_reparation`),
  ADD UNIQUE KEY `reparer_id_reparation_uindex` (`id_reparation`),
  ADD KEY `reparation_place_id_place_fk` (`id_place`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `num_commande` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `panne`
--
ALTER TABLE `panne`
  MODIFY `id_panne` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `piece`
--
ALTER TABLE `piece`
  MODIFY `id_piece` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `place`
--
ALTER TABLE `place`
  MODIFY `id_place` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT pour la table `reparer`
--
ALTER TABLE `reparer`
  MODIFY `id_reparation` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
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
  ADD CONSTRAINT `id_place` FOREIGN KEY (`id_place`) REFERENCES `Place` (`id_place`),
  ADD CONSTRAINT `id_reparation` FOREIGN KEY (`id_reparation`) REFERENCES `reparer` (`id_reparation`);

--
-- Contraintes pour la table `reparer`
--
ALTER TABLE `reparer`
  ADD CONSTRAINT `reparation_place_id_place_fk` FOREIGN KEY (`id_place`) REFERENCES `place` (`id_place`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
