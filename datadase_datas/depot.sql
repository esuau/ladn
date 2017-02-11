#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Véhicules
#------------------------------------------------------------

CREATE TABLE Vehicules(
        id_vehicule      Varchar (25) NOT NULL ,
        immat            Varchar (25) ,
        marque           Varchar (25) ,
        version          Varchar (25) ,
        nom              Varchar (50) ,
        num_vin          Varchar (25) ,
        motorisation     Varchar (50) ,
        puissance_max_kW Int ,
        affectation      Varchar (25) ,
        in_out           Enum ("dans le dépôt","en service") ,
        PRIMARY KEY (id_vehicule )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Techniciens
#------------------------------------------------------------

CREATE TABLE Techniciens(
        id_technicien      int (11) Auto_increment  NOT NULL ,
        nom_technicien     Varchar (25) ,
        prenom_technicien  Varchar (25) ,
        num_tel_technicien Varchar (10) ,
        password_software  Varchar (25) ,
        rights             Enum ("chef d'atelier","technicien") ,
        PRIMARY KEY (id_technicien )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Places
#------------------------------------------------------------

CREATE TABLE Places(
        id_place     int (11) Auto_increment  NOT NULL ,
        statut_place Enum ("libre","occupée") ,
        PRIMARY KEY (id_place )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Pannes
#------------------------------------------------------------

CREATE TABLE Pannes(
        id_panne             int (11) Auto_increment  NOT NULL ,
        type_prob            Varchar (25) ,
        intitule             Varchar (50) ,
        descriptif_protocole Text ,
        temps_estime         Time ,
        PRIMARY KEY (id_panne )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Pièces
#------------------------------------------------------------

CREATE TABLE Pieces(
        id_piece      int (11) Auto_increment  NOT NULL ,
        libelle_piece Varchar (50) ,
        fabricant     Varchar (25) ,
        qte_dispo     Int ,
        valeur_piece  DECIMAL (15,3)  ,
        PRIMARY KEY (id_piece )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Commandes
#------------------------------------------------------------

CREATE TABLE Commandes(
        num_commande      int (11) Auto_increment  NOT NULL ,
        date_commande     Date ,
        prix_commande_HT  DECIMAL (15,3)  ,
        taxe_commande     DECIMAL (15,3)  ,
        prix_commande_TTC DECIMAL (15,3)  ,
        id_technicien     Int ,
        PRIMARY KEY (num_commande )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Occuper
#------------------------------------------------------------

CREATE TABLE Occuper(
        date_entree_vehicule Date ,
        date_de_sortie       Date ,
        id_vehicule          Varchar (25) NOT NULL ,
        id_place             Int NOT NULL ,
        PRIMARY KEY (id_vehicule ,id_place )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Nécessiter
#------------------------------------------------------------

CREATE TABLE Necessiter(
        qte_necessaire Int ,
        id_panne       Int NOT NULL ,
        id_piece       Int NOT NULL ,
        PRIMARY KEY (id_panne ,id_piece )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Être montable sur
#------------------------------------------------------------

CREATE TABLE Etre_montable_sur(
        id_piece    Int NOT NULL ,
        id_vehicule Varchar (25) NOT NULL ,
        PRIMARY KEY (id_piece ,id_vehicule )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Réparer
#------------------------------------------------------------

CREATE TABLE Reparer(
        statut_reparation     Enum ("non-diagnostiqué","diagnostiqué","en cours de réparation","réparé") ,
        date_debut_reparation Date ,
        date_fin_reparation   Date ,
        temps_reel_reparation Time ,
        id_technicien         Int NOT NULL ,
        id_panne              Int NOT NULL ,
        id_vehicule           Varchar (25) NOT NULL ,
        PRIMARY KEY (id_technicien ,id_panne ,id_vehicule )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Être composée de
#------------------------------------------------------------

CREATE TABLE Etre_composee_de(
        quantite_pieces       Int ,
        reference_fournisseur Varchar (50) ,
        prix_u_HT_piece       DECIMAL (15,3)  ,
        taxe_u_piece          DECIMAL (15,3)  ,
        prix_u_TTC_piece      DECIMAL (15,3)  ,
        prix_total_TTC_piece  DECIMAL (15,3)  ,
        num_commande          Int NOT NULL ,
        id_piece              Int NOT NULL ,
        PRIMARY KEY (num_commande ,id_piece )
)ENGINE=InnoDB;

ALTER TABLE Commandes ADD CONSTRAINT FK_Commandes_id_technicien FOREIGN KEY (id_technicien) REFERENCES Techniciens(id_technicien);
ALTER TABLE Occuper ADD CONSTRAINT FK_Occuper_id_vehicule FOREIGN KEY (id_vehicule) REFERENCES Vehicules(id_vehicule);
ALTER TABLE Occuper ADD CONSTRAINT FK_Occuper_id_place FOREIGN KEY (id_place) REFERENCES Places(id_place);
ALTER TABLE Necessiter ADD CONSTRAINT FK_Necessiter_id_panne FOREIGN KEY (id_panne) REFERENCES Pannes(id_panne);
ALTER TABLE Necessiter ADD CONSTRAINT FK_Necessiter_id_piece FOREIGN KEY (id_piece) REFERENCES Pieces(id_piece);
ALTER TABLE Etre_montable_sur ADD CONSTRAINT FK_Etre_montable_sur_id_piece FOREIGN KEY (id_piece) REFERENCES Pieces(id_piece);
ALTER TABLE Etre_montable_sur ADD CONSTRAINT FK_Etre_montable_sur_id_vehicule FOREIGN KEY (id_vehicule) REFERENCES Vehicules(id_vehicule);
ALTER TABLE Reparer ADD CONSTRAINT FK_Reparer_id_technicien FOREIGN KEY (id_technicien) REFERENCES Techniciens(id_technicien);
ALTER TABLE Reparer ADD CONSTRAINT FK_Reparer_id_panne FOREIGN KEY (id_panne) REFERENCES Pannes(id_panne);
ALTER TABLE Reparer ADD CONSTRAINT FK_Reparer_id_vehicule FOREIGN KEY (id_vehicule) REFERENCES Vehicules(id_vehicule);
ALTER TABLE Etre_composee_de ADD CONSTRAINT FK_Etre_composee_de_num_commande FOREIGN KEY (num_commande) REFERENCES Commandes(num_commande);
ALTER TABLE Etre_composee_de ADD CONSTRAINT FK_Etre_composee_de_id_piece FOREIGN KEY (id_piece) REFERENCES Pieces(id_piece);
