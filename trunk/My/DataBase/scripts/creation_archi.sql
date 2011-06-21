/* Creation de la table des roles */
CREATE TABLE IF NOT EXISTS role (
	id_role 				INTEGER 		NOT NULL				, 
	lib_role	 			VARCHAR(20)		NOT NULL				,
	PRIMARY KEY (id_role)
);

/* Creation de la table des utilisateurs */
CREATE TABLE IF NOT EXISTS utilisateur (
	id_utilisateur 			INTEGER 		NOT NULL AUTO_INCREMENT	,
	id_role					INTEGER 		NOT NULL 				,
	nom_utilisateur 		VARCHAR(20)		NOT NULL				,
	password_utilisateur 	VARCHAR(20)		NOT NULL				,
	PRIMARY KEY (id_utilisateur)									,
	FOREIGN KEY (id_role) 	REFERENCES role(id_role)
);