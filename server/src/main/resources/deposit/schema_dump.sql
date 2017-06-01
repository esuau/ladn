CREATE TABLE assoc_reparation_panne
(
  id_reparation INT NOT NULL,
  id_panne      INT NOT NULL,
  PRIMARY KEY (id_reparation, id_panne)
);

CREATE TABLE commande
(
  num_commande      INT AUTO_INCREMENT
    PRIMARY KEY,
  date_commande     DATETIME       NOT NULL,
  prix_commande_HT  DECIMAL(15, 3) NULL,
  taxe_commande     DECIMAL(15, 3) NULL,
  prix_commande_TTC DECIMAL(15, 3) NULL,
  id_technicien     INT            NULL
);

CREATE INDEX FK_Commandes_id_technicien
  ON commande (id_technicien);

CREATE TABLE etre_composee_de
(
  quantite_pieces       INT            NULL,
  reference_fournisseur VARCHAR(50)    NULL,
  prix_u_HT_piece       DECIMAL(15, 3) NULL,
  taxe_u_piece          DECIMAL(15, 3) NULL,
  prix_u_TTC_piece      DECIMAL(15, 3) NULL,
  prix_total_TTC_piece  DECIMAL(15, 3) NULL,
  num_commande          INT            NOT NULL,
  id_piece              INT            NOT NULL,
  PRIMARY KEY (num_commande, id_piece),
  CONSTRAINT FK_Etre_composee_de_num_commande
  FOREIGN KEY (num_commande) REFERENCES deposit.commande (num_commande)
);

CREATE INDEX FK_Etre_composee_de_id_piece
  ON etre_composee_de (id_piece);

CREATE TABLE etre_montable_sur
(
  id_piece    INT NOT NULL,
  id_vehicule INT NOT NULL,
  PRIMARY KEY (id_piece, id_vehicule)
);

CREATE INDEX FK_Etre_montable_sur_id_vehicule
  ON etre_montable_sur (id_vehicule);

CREATE INDEX etre_montable_sur_id_piece_index
  ON etre_montable_sur (id_piece);

CREATE TABLE necessiter
(
  id_panne       INT NOT NULL,
  id_piece       INT NOT NULL,
  qte_necessaire INT NULL,
  PRIMARY KEY (id_panne, id_piece)
);

CREATE INDEX FK_Necessiter_id_piece
  ON necessiter (id_piece);

CREATE TABLE occuper
(
  id_place    INT NOT NULL,
  id_vehicule INT NOT NULL,
  PRIMARY KEY (id_vehicule, id_place)
);

CREATE INDEX FK_Occuper_id_place
  ON occuper (id_place);

CREATE TABLE panne
(
  id_panne             INT AUTO_INCREMENT
    PRIMARY KEY,
  intitule             VARCHAR(50) NULL,
  type_panne           INT         NULL,
  descriptif_protocole TEXT        NULL,
  temps_estime         TIME        NULL,
  CONSTRAINT pannes_id_panne_uindex
  UNIQUE (id_panne)
);

CREATE INDEX pannes_type_panne_index
  ON panne (type_panne);

ALTER TABLE assoc_reparation_panne
  ADD CONSTRAINT assoc_reparation_panne_panne_id_panne_fk
FOREIGN KEY (id_reparation) REFERENCES deposit.panne (id_panne);

ALTER TABLE necessiter
  ADD CONSTRAINT FK_Necessiter_id_panne
FOREIGN KEY (id_panne) REFERENCES deposit.panne (id_panne);

CREATE TABLE piece
(
  id_piece      INT AUTO_INCREMENT
    PRIMARY KEY,
  libelle_piece VARCHAR(50)    NULL,
  fabricant     VARCHAR(25)    NULL,
  valeur_piece  DECIMAL(15, 3) NULL
);

ALTER TABLE etre_composee_de
  ADD CONSTRAINT FK_Etre_composee_de_id_piece
FOREIGN KEY (id_piece) REFERENCES deposit.piece (id_piece);

ALTER TABLE etre_montable_sur
  ADD CONSTRAINT FK_Etre_montable_sur_id_piece
FOREIGN KEY (id_piece) REFERENCES deposit.piece (id_piece);

ALTER TABLE necessiter
  ADD CONSTRAINT FK_Necessiter_id_piece
FOREIGN KEY (id_piece) REFERENCES deposit.piece (id_piece);

CREATE TABLE place
(
  id_place INT AUTO_INCREMENT
    PRIMARY KEY
);

ALTER TABLE occuper
  ADD CONSTRAINT FK_Occuper_id_place
FOREIGN KEY (id_place) REFERENCES deposit.place (id_place);

CREATE TABLE reparation_histo_temps
(
  id_reparation INT         NOT NULL
    PRIMARY KEY,
  statut        VARCHAR(20) NULL,
  date_debut    DATETIME    NULL,
  date_fin      DATETIME    NULL,
  id_place      INT         NULL,
  CONSTRAINT reparation_hist_temps_id_reparation_uindex
  UNIQUE (id_reparation),
  CONSTRAINT reparation_hist_temps_places_id_place_fk
  FOREIGN KEY (id_place) REFERENCES deposit.place (id_place)
);

CREATE INDEX reparation_hist_temps_id_place_index
  ON reparation_histo_temps (id_place);

CREATE TABLE reparer
(
  id_reparation        INT AUTO_INCREMENT
    PRIMARY KEY,
  id_technicien        INT          NOT NULL,
  id_place             INT          NULL,
  id_vehicule          INT          NOT NULL,
  statut_reparation    VARCHAR(25)  NULL,
  priorite_reparation  TINYINT      NULL,
  commentaire          VARCHAR(250) NULL,
  date_entree_vehicule DATETIME     NOT NULL,
  date_sortie_vehicule DATETIME     NULL,
  CONSTRAINT reparer_id_reparation_uindex
  UNIQUE (id_reparation),
  CONSTRAINT reparation_place_id_place_fk
  FOREIGN KEY (id_place) REFERENCES deposit.place (id_place)
);

CREATE INDEX reparation_place_id_place_fk
  ON reparer (id_place);

CREATE INDEX reparation_technicien_id_technicien_fk
  ON reparer (id_technicien);

CREATE INDEX reparer_vehicule_id_vehicule_fk
  ON reparer (id_vehicule);

ALTER TABLE assoc_reparation_panne
  ADD CONSTRAINT assoc_reparation_panne_reparation_id_reparation_fk
FOREIGN KEY (id_reparation) REFERENCES deposit.reparer (id_reparation);

ALTER TABLE reparation_histo_temps
  ADD CONSTRAINT reparation_hist_temps_reparer_id_reparation_fk
FOREIGN KEY (id_reparation) REFERENCES deposit.reparer (id_reparation);

CREATE TABLE stock_piece
(
  id_piece INT NOT NULL
    PRIMARY KEY,
  qte      INT NULL,
  CONSTRAINT stock_piece_id_piece_uindex
  UNIQUE (id_piece),
  CONSTRAINT stock_piece_pieces_id_piece_fk
  FOREIGN KEY (id_piece) REFERENCES deposit.piece (id_piece)
);

CREATE TABLE technicien
(
  id_technicien       INT AUTO_INCREMENT
    PRIMARY KEY,
  nom_technicien      VARCHAR(25)                                         NULL,
  prenom_technicien   VARCHAR(25)                                         NULL,
  num_tel_technicien  VARCHAR(10)                                         NULL,
  password_technicien VARCHAR(25)                                         NOT NULL,
  droits_technicien   ENUM ('manager', 'technicien') DEFAULT 'technicien' NOT NULL,
  CONSTRAINT technicien_id_technicien_uindex
  UNIQUE (id_technicien)
);

ALTER TABLE commande
  ADD CONSTRAINT FK_Commandes_id_technicien
FOREIGN KEY (id_technicien) REFERENCES deposit.technicien (id_technicien);

ALTER TABLE reparer
  ADD CONSTRAINT reparation_technicien_id_technicien_fk
FOREIGN KEY (id_technicien) REFERENCES deposit.technicien (id_technicien);

CREATE TABLE type_panne
(
  id_type_panne INT AUTO_INCREMENT
    PRIMARY KEY,
  priorite      INT  NOT NULL,
  libelle       TEXT NOT NULL,
  CONSTRAINT type_panne_id_type_panne_uindex
  UNIQUE (id_type_panne)
);

ALTER TABLE panne
  ADD CONSTRAINT pannes_type_panne_id_type_panne_fk
FOREIGN KEY (type_panne) REFERENCES deposit.type_panne (id_type_panne);

CREATE TABLE vehicule
(
  id_vehicule     INT AUTO_INCREMENT
    PRIMARY KEY,
  immatriculation VARCHAR(25) NOT NULL,
  marque          VARCHAR(25) NOT NULL,
  constructeur    VARCHAR(25) NOT NULL,
  version         VARCHAR(25) NULL,
  CONSTRAINT vehicule_id_vehicule_uindex
  UNIQUE (id_vehicule)
);

ALTER TABLE etre_montable_sur
  ADD CONSTRAINT etre_montable_sur_vehicle_id_vehicule_fk
FOREIGN KEY (id_vehicule) REFERENCES deposit.vehicule (id_vehicule);

ALTER TABLE occuper
  ADD CONSTRAINT occuper_vehicle_id_vehicule_fk
FOREIGN KEY (id_vehicule) REFERENCES deposit.vehicule (id_vehicule);

ALTER TABLE reparer
  ADD CONSTRAINT reparer_vehicule_id_vehicule_fk
FOREIGN KEY (id_vehicule) REFERENCES deposit.vehicule (id_vehicule);