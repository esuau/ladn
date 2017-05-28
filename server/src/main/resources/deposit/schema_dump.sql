create table commandes
(
	num_commande int auto_increment
		primary key,
	date_commande date null,
	prix_commande_HT decimal(15,3) null,
	taxe_commande decimal(15,3) null,
	prix_commande_TTC decimal(15,3) null,
	id_technicien int null
)
;

create index FK_Commandes_id_technicien
	on commandes (id_technicien)
;

create table etre_composee_de
(
	quantite_pieces int null,
	reference_fournisseur varchar(50) null,
	prix_u_HT_piece decimal(15,3) null,
	taxe_u_piece decimal(15,3) null,
	prix_u_TTC_piece decimal(15,3) null,
	prix_total_TTC_piece decimal(15,3) null,
	num_commande int not null,
	id_piece int not null,
	primary key (num_commande, id_piece),
	constraint FK_Etre_composee_de_num_commande
		foreign key (num_commande) references deposit.commandes (num_commande)
)
;

create index FK_Etre_composee_de_id_piece
	on etre_composee_de (id_piece)
;

create table etre_montable_sur
(
	id_piece int not null,
	id_vehicule varchar(25) not null,
	primary key (id_piece, id_vehicule)
)
;

create index FK_Etre_montable_sur_id_vehicule
	on etre_montable_sur (id_vehicule)
;

create table necessiter
(
	qte_necessaire int null,
	id_panne int not null,
	id_piece int not null,
	primary key (id_panne, id_piece)
)
;

create index FK_Necessiter_id_piece
	on necessiter (id_piece)
;

create table occuper
(
	id_place int not null,
	id_vehicule varchar(25) not null,
	primary key (id_vehicule, id_place)
)
;

create index FK_Occuper_id_place
	on occuper (id_place)
;

create table pannes
(
	id_panne int auto_increment
		primary key,
	intitule varchar(50) null,
	type_panne int null,
	descriptif_protocole text null,
	temps_estime time null,
	constraint pannes_id_panne_uindex
		unique (id_panne)
)
;

create index pannes_type_panne_index
	on pannes (type_panne)
;

alter table necessiter
	add constraint FK_Necessiter_id_panne
		foreign key (id_panne) references deposit.pannes (id_panne)
;

create table pieces
(
	id_piece int auto_increment
		primary key,
	libelle_piece varchar(50) null,
	fabricant varchar(25) null,
	valeur_piece decimal(15,3) null
)
;

alter table etre_composee_de
	add constraint FK_Etre_composee_de_id_piece
		foreign key (id_piece) references deposit.pieces (id_piece)
;

alter table etre_montable_sur
	add constraint FK_Etre_montable_sur_id_piece
		foreign key (id_piece) references deposit.pieces (id_piece)
;

alter table necessiter
	add constraint FK_Necessiter_id_piece
		foreign key (id_piece) references deposit.pieces (id_piece)
;

create table places
(
	id_place int auto_increment
		primary key
)
;

alter table occuper
	add constraint FK_Occuper_id_place
		foreign key (id_place) references deposit.places (id_place)
;

create table reparation_histo_temps
(
	id_reparation int not null
		primary key,
	statut varchar(20) null,
	date_deb date null,
	date_fin date null,
	id_place int null,
	constraint reparation_hist_temps_id_reparation_uindex
		unique (id_reparation),
	constraint reparation_hist_temps_places_id_place_fk
		foreign key (id_place) references deposit.places (id_place)
)
;

create index reparation_hist_temps_id_place_index
	on reparation_histo_temps (id_place)
;

create table reparer
(
	id_reparation int not null
		primary key,
	statut_reparation text null,
	priorite_reparation tinyint null,
	Date_entrée_vehicule date not null,
	date_sortie date null,
	id_technicien int not null,
	id_panne int not null,
	id_vehicule varchar(25) not null,
	id_place int not null,
	constraint Date_entrée_vehicule
		unique (Date_entrée_vehicule, id_technicien, id_panne, id_vehicule),
	constraint id_technicien
		unique (id_technicien, id_panne, id_vehicule),
	constraint FK_Reparer_id_panne
		foreign key (id_panne) references deposit.pannes (id_panne)
)
;

create index FK_Reparer_id_panne
	on reparer (id_panne)
;

create index FK_Reparer_id_vehicule
	on reparer (id_vehicule)
;

alter table reparation_histo_temps
	add constraint reparation_hist_temps_reparer_id_reparation_fk
		foreign key (id_reparation) references deposit.reparer (id_reparation)
;

create table stock_piece
(
	id_piece int not null
		primary key,
	qte int null,
	constraint stock_piece_id_piece_uindex
		unique (id_piece),
	constraint stock_piece_pieces_id_piece_fk
		foreign key (id_piece) references deposit.pieces (id_piece)
)
;

create table techniciens
(
	id_technicien int auto_increment
		primary key,
	nom_technicien varchar(25) null,
	prenom_technicien varchar(25) null,
	num_tel_technicien varchar(10) null,
	password_software varchar(25) null,
	rights enum('chef d''atelier', 'technicien') null
)
;

alter table commandes
	add constraint FK_Commandes_id_technicien
		foreign key (id_technicien) references deposit.techniciens (id_technicien)
;

alter table reparer
	add constraint FK_Reparer_id_technicien
		foreign key (id_technicien) references deposit.techniciens (id_technicien)
;

create table type_panne
(
	id_type_panne int auto_increment
		primary key,
	priorite int not null,
	libelle text not null,
	constraint type_panne_id_type_panne_uindex
		unique (id_type_panne)
)
;

alter table pannes
	add constraint pannes_type_panne_id_type_panne_fk
		foreign key (type_panne) references deposit.type_panne (id_type_panne)
;

create table vehicules
(
	id_vehicule varchar(25) not null
		primary key,
	immat varchar(25) null,
	marque varchar(25) null,
	version varchar(25) null,
	nom varchar(50) null
)
;

alter table etre_montable_sur
	add constraint FK_Etre_montable_sur_id_vehicule
		foreign key (id_vehicule) references deposit.vehicules (id_vehicule)
;

alter table occuper
	add constraint FK_Occuper_id_vehicule
		foreign key (id_vehicule) references deposit.vehicules (id_vehicule)
;

alter table reparer
	add constraint FK_Reparer_id_vehicule
		foreign key (id_vehicule) references deposit.vehicules (id_vehicule)
;
