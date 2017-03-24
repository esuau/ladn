#------------------------------------------------------------
# Table: Véhicules
#------------------------------------------------------------

CREATE TABLE Vehicules (
  id_vehicule      VARCHAR(25) NOT NULL,
  immat            VARCHAR(25),
  marque           VARCHAR(25),
  version          VARCHAR(25),
  nom              VARCHAR(50),
  num_vin          VARCHAR(25),
  motorisation     VARCHAR(50),
  puissance_max_kW INT,
  affectation      VARCHAR(25),
  in_out           ENUM ("dans le dépôt", "en service"),
  PRIMARY KEY (id_vehicule)
)
  ENGINE = InnoDB;

#------------------------------------------------------------
# Table: Techniciens
#------------------------------------------------------------

CREATE TABLE Techniciens (
  id_technicien      INT(11) AUTO_INCREMENT NOT NULL,
  nom_technicien     VARCHAR(25),
  prenom_technicien  VARCHAR(25),
  num_tel_technicien VARCHAR(10),
  password_software  VARCHAR(25),
  rights             ENUM ("chef d'atelier", "technicien"),
  PRIMARY KEY (id_technicien)
)
  ENGINE = InnoDB;

#------------------------------------------------------------
# Table: Places
#------------------------------------------------------------

CREATE TABLE Places (
  id_place     INT(11) AUTO_INCREMENT NOT NULL,
  statut_place ENUM ("libre", "occupée"),
  PRIMARY KEY (id_place)
)
  ENGINE = InnoDB;

#------------------------------------------------------------
# Table: Pannes
#------------------------------------------------------------

CREATE TABLE Pannes (
  id_panne             INT(11) AUTO_INCREMENT NOT NULL,
  type_prob            VARCHAR(25),
  intitule             VARCHAR(50),
  descriptif_protocole TEXT,
  temps_estime         TIME,
  PRIMARY KEY (id_panne)
)
  ENGINE = InnoDB;

#------------------------------------------------------------
# Table: Pièces
#------------------------------------------------------------

CREATE TABLE Pieces (
  id_piece      INT(11) AUTO_INCREMENT NOT NULL,
  libelle_piece VARCHAR(50),
  fabricant     VARCHAR(25),
  qte_dispo     INT,
  valeur_piece  DECIMAL(15, 3),
  PRIMARY KEY (id_piece)
)
  ENGINE = InnoDB;

#------------------------------------------------------------
# Table: Commandes
#------------------------------------------------------------

CREATE TABLE Commandes (
  num_commande      INT(11) AUTO_INCREMENT NOT NULL,
  date_commande     DATE,
  prix_commande_HT  DECIMAL(15, 3),
  taxe_commande     DECIMAL(15, 3),
  prix_commande_TTC DECIMAL(15, 3),
  id_technicien     INT,
  PRIMARY KEY (num_commande)
)
  ENGINE = InnoDB;

#------------------------------------------------------------
# Table: Occuper
#------------------------------------------------------------

CREATE TABLE Occuper (
  date_entree_vehicule DATE,
  date_de_sortie       DATE,
  id_vehicule          VARCHAR(25) NOT NULL,
  id_place             INT         NOT NULL,
  PRIMARY KEY (id_vehicule, id_place)
)
  ENGINE = InnoDB;

#------------------------------------------------------------
# Table: Nécessiter
#------------------------------------------------------------

CREATE TABLE Necessiter (
  qte_necessaire INT,
  id_panne       INT NOT NULL,
  id_piece       INT NOT NULL,
  PRIMARY KEY (id_panne, id_piece)
)
  ENGINE = InnoDB;

#------------------------------------------------------------
# Table: Être montable sur
#------------------------------------------------------------

CREATE TABLE Etre_montable_sur (
  id_piece    INT         NOT NULL,
  id_vehicule VARCHAR(25) NOT NULL,
  PRIMARY KEY (id_piece, id_vehicule)
)
  ENGINE = InnoDB;

#------------------------------------------------------------
# Table: Réparer
#------------------------------------------------------------

CREATE TABLE Reparer (
  statut_reparation     ENUM ("non-diagnostiqué", "diagnostiqué", "en cours de réparation", "réparé"),
  date_debut_reparation DATE,
  date_fin_reparation   DATE,
  temps_reel_reparation TIME,
  id_technicien         INT         NOT NULL,
  id_panne              INT         NOT NULL,
  id_vehicule           VARCHAR(25) NOT NULL,
  PRIMARY KEY (id_technicien, id_panne, id_vehicule)
)
  ENGINE = InnoDB;

#------------------------------------------------------------
# Table: Être composée de
#------------------------------------------------------------

CREATE TABLE Etre_composee_de (
  quantite_pieces       INT,
  reference_fournisseur VARCHAR(50),
  prix_u_HT_piece       DECIMAL(15, 3),
  taxe_u_piece          DECIMAL(15, 3),
  prix_u_TTC_piece      DECIMAL(15, 3),
  prix_total_TTC_piece  DECIMAL(15, 3),
  num_commande          INT NOT NULL,
  id_piece              INT NOT NULL,
  PRIMARY KEY (num_commande, id_piece)
)
  ENGINE = InnoDB;

ALTER TABLE Commandes
  ADD CONSTRAINT FK_Commandes_id_technicien FOREIGN KEY (id_technicien) REFERENCES Techniciens (id_technicien);
ALTER TABLE Occuper
  ADD CONSTRAINT FK_Occuper_id_vehicule FOREIGN KEY (id_vehicule) REFERENCES Vehicules (id_vehicule);
ALTER TABLE Occuper
  ADD CONSTRAINT FK_Occuper_id_place FOREIGN KEY (id_place) REFERENCES Places (id_place);
ALTER TABLE Necessiter
  ADD CONSTRAINT FK_Necessiter_id_panne FOREIGN KEY (id_panne) REFERENCES Pannes (id_panne);
ALTER TABLE Necessiter
  ADD CONSTRAINT FK_Necessiter_id_piece FOREIGN KEY (id_piece) REFERENCES Pieces (id_piece);
ALTER TABLE Etre_montable_sur
  ADD CONSTRAINT FK_Etre_montable_sur_id_piece FOREIGN KEY (id_piece) REFERENCES Pieces (id_piece);
ALTER TABLE Etre_montable_sur
  ADD CONSTRAINT FK_Etre_montable_sur_id_vehicule FOREIGN KEY (id_vehicule) REFERENCES Vehicules (id_vehicule);
ALTER TABLE Reparer
  ADD CONSTRAINT FK_Reparer_id_technicien FOREIGN KEY (id_technicien) REFERENCES Techniciens (id_technicien);
ALTER TABLE Reparer
  ADD CONSTRAINT FK_Reparer_id_panne FOREIGN KEY (id_panne) REFERENCES Pannes (id_panne);
ALTER TABLE Reparer
  ADD CONSTRAINT FK_Reparer_id_vehicule FOREIGN KEY (id_vehicule) REFERENCES Vehicules (id_vehicule);
ALTER TABLE Etre_composee_de
  ADD CONSTRAINT FK_Etre_composee_de_num_commande FOREIGN KEY (num_commande) REFERENCES Commandes (num_commande);
ALTER TABLE Etre_composee_de
  ADD CONSTRAINT FK_Etre_composee_de_id_piece FOREIGN KEY (id_piece) REFERENCES Pieces (id_piece);