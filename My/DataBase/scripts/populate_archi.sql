/* Insertion des utilisateurs */
LOAD DATA LOCAL INFILE "../data_tables/role.txt" INTO TABLE role FIELDS TERMINATED BY ';';
LOAD DATA LOCAL INFILE "../data_tables/utilisateur.txt" INTO TABLE utilisateur FIELDS TERMINATED BY ';';