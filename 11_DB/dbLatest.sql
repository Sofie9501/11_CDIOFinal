/* must be dropped in this order to avoid constraint violations */
DROP TABLE IF EXISTS produktbatchkomponent;
DROP TABLE IF EXISTS produktbatch;
DROP TABLE IF EXISTS operatoer;
DROP TABLE IF EXISTS receptkomponent;
DROP TABLE IF EXISTS recept;
DROP TABLE IF EXISTS raavarebatch;
DROP TABLE IF EXISTS raavare;
DROP TABLE IF EXISTS roller;

CREATE TABLE roller(rolle_id INT PRIMARY KEY, rolle_navn TEXT) ENGINE = innoDB;

CREATE TABLE operatoer(opr_id INT PRIMARY KEY, opr_navn TEXT, cpr TEXT, password TEXT, rolle_id INT,
FOREIGN KEY (rolle_id) REFERENCES roller(rolle_id)) ENGINE=innoDB;
 
CREATE TABLE raavare(raavare_id INT PRIMARY KEY, raavare_navn TEXT, leverandoer TEXT) ENGINE=innoDB;

CREATE TABLE raavarebatch(rb_id INT PRIMARY KEY, raavare_id INT, maengde REAL, 
   FOREIGN KEY (raavare_id) REFERENCES raavare(raavare_id)) ENGINE=innoDB;

CREATE TABLE recept(recept_id INT PRIMARY KEY, recept_navn TEXT) ENGINE=innoDB;

CREATE TABLE receptkomponent(recept_id INT, raavare_id INT, nom_netto REAL, tolerance REAL, 
   PRIMARY KEY (recept_id, raavare_id), 
   FOREIGN KEY (recept_id) REFERENCES recept(recept_id), 
   FOREIGN KEY (raavare_id) REFERENCES raavare(raavare_id)) ENGINE=innoDB;

CREATE TABLE produktbatch(pb_id INT PRIMARY KEY, status INT, recept_id INT, dato DATE,
   FOREIGN KEY (recept_id) REFERENCES recept(recept_id)) ENGINE=innoDB;

CREATE TABLE produktbatchkomponent(pb_id INT, rb_id INT, tara REAL, netto REAL, opr_id INT, 
   PRIMARY KEY (pb_id, rb_id), 
   FOREIGN KEY (pb_id) REFERENCES produktbatch(pb_id), 
   FOREIGN KEY (rb_id) REFERENCES raavarebatch(rb_id), 
   FOREIGN KEY (opr_id) REFERENCES operatoer(opr_id)) ENGINE=innoDB;

INSERT INTO roller(rolle_id, rolle_navn) VALUES
(1, 'DBA'),
(2, 'Værkfører'),
(3, 'stofmisbruger');

INSERT INTO operatoer(opr_id, opr_navn, rolle_id, cpr, password) VALUES
(1, 'Angelo A', 1, '070770-7007', 'lKje4fa'),
(2, 'Antonella B', 2, '080880-8008', 'atoJ21v'),
(3, 'Luigi C', 3, '090990-9009', 'jEfm5aQ');

INSERT INTO raavare(raavare_id, raavare_navn, leverandoer) VALUES
(1, 'dej', 'Wawelka'),
(2, 'tomat', 'Knoor'),
(3, 'tomat', 'Veaubais'),
(4, 'tomat', 'Franz'),
(5, 'ost', 'Ost og Skinke A/S'),
(6, 'skinke', 'Ost og Skinke A/S'),
(7, 'champignon', 'Igloo Frostvarer');

INSERT INTO raavarebatch(rb_id, raavare_id, maengde) VALUES
(1, 1, 1000),
(2, 2, 300),
(3, 3, 300),
(4, 5, 100),
(5, 5, 100), 
(6, 6, 100),
(7, 7, 100);

INSERT INTO recept(recept_id, recept_navn) VALUES
(1, 'margherita'),
(2, 'prosciutto'),
(3, 'capricciosa');


INSERT INTO receptkomponent(recept_id, raavare_id, nom_netto, tolerance) VALUES
(1, 1, 10.0, 0.1),
(1, 2, 2.0, 0.1),
(1, 5, 2.0, 0.1),

(2, 1, 10.0, 0.1),
(2, 3, 2.0, 0.1),  
(2, 5, 1.5, 0.1),
(2, 6, 1.5, 0.1),

(3, 1, 10.0, 0.1),
(3, 4, 1.5, 0.1),
(3, 5, 1.5, 0.1),
(3, 6, 1.0, 0.1),
(3, 7, 1.0, 0.1);

INSERT INTO produktbatch(dato,pb_id, recept_id, status) VALUES
(NOW(),1, 1, 2), 
(NOW(),2, 1, 2),
(NOW(),3, 2, 2),
(NOW(),4, 3, 1),
(NOW(),5, 3, 0);


INSERT INTO produktbatchkomponent(pb_id, rb_id, tara, netto, opr_id) VALUES
(1, 1, 0.5, 10.05, 1),
(1, 2, 0.5, 2.03, 1),
(1, 4, 0.5, 1.98, 1),

(2, 1, 0.5, 10.01, 2),
(2, 2, 0.5, 1.99, 2),
(2, 5, 0.5, 1.47, 2),

(3, 1, 0.5, 10.07, 1),
(3, 3, 0.5, 2.06, 2),
(3, 4, 0.5, 1.55, 1),
(3, 6, 0.5, 1.53, 2),

(4, 1, 0.5, 10.02, 3),
(4, 5, 0.5, 1.57, 3),
(4, 6, 0.5, 1.03, 3),
(4, 7, 0.5, 0.99, 3);

use lab;
/*******************************************
* VIEWS									   * 
*******************************************/
Drop view if exists recept_administration;
Drop view if exists raavarebatch_administration;
Drop view if exists antal_faerdige;
Drop view if exists antal_komponenter;
Drop view if exists produktbatch_administration;
Drop view if exists afvejning;

create view recept_administration 
as select recept_navn, recept_id, raavare_navn, raavare_id, tolerance, nom_netto
from recept natural join receptkomponent natural join raavare; 


create view raavarebatch_administration
as select rb_id, raavare_navn, maengde, raavare_id
from raavare natural join raavarebatch;


create view antal_faerdige as SELECT produktbatch.pb_id, COUNT(produktbatchkomponent.pb_id) as antal_faerdige, recept_id, dato, status
FROM produktbatch
LEFT JOIN produktbatchkomponent on produktbatchkomponent.pb_id=produktbatch.pb_id group by produktbatch.pb_id;


create view antal_komponenter as select recept_id, count(raavare_id) as antal_komp
from receptkomponent group by recept_id;


create view produktbatch_administration as select pb_id, recept_id, recept_navn, antal_komp, antal_faerdige, dato, status
from antal_komponenter natural join antal_faerdige natural join recept;


create view afvejning as select pb_id, raavare_id , raavare_navn, nom_netto, tolerance 
from produktbatch natural join receptkomponent natural join raavare 
where (receptkomponent.raavare_id, produktbatch.pb_id) not in(
select raavare_id, pb_id from produktbatchkomponent natural join raavarebatch);

/*******************************************
* STORED PROCEDURES						   * 
*******************************************/
drop procedure if exists opret_opr;
drop procedure if exists opret_raavare;  
drop procedure if exists aendre_raavare; 
DROP PROCEDURE IF EXISTS opret_recept;
DROP PROCEDURE IF EXISTS aendre_recept;
DROP PROCEDURE IF EXISTS opret_raavarebatch;
DROP PROCEDURE IF EXISTS opret_produktbatch;
DROP PROCEDURE IF EXISTS opret_receptkomponent;
DROP PROCEDURE IF EXISTS aendre_opr;
drop procedure if exists afvejning;
	
delimiter //

/* OPRET OPERATOR */
create procedure opret_opr
(in id_input int(5), in rolle_input int(1), in navn_input text, in cpr_input text, in password_input text)
begin 
insert into operatoer(opr_id, rolle_id, opr_navn, cpr, password)
values (id_input, rolle_input, navn_input,  cpr_input, password_input);
end; //


/* AENDRE RAAVARE */
create procedure aendre_opr
(in id_input int(5), in rolle_input int(1), in navn_input text, in cpr_input text, in password_input text)
begin
update operatoer
set opr_navn = navn_input, rolle_id = rolle_input, cpr = cpr_input, password = password_input
where opr_id = id_input;
end; //

/* OPRET RAAVARE */
create procedure opret_raavare
(in raavare_id_input int, in raavare_navn_input text, in leverandoer_input text)
begin
insert into raavare(raavare_id, raavare_navn,leverandoer)
values (raavare_id_input, raavare_navn_input, leverandoer_input); 
end; //

/* AENDRE RAAVARE */
create procedure aendre_raavare
(in raavare_id_input int, in raavare_navn_input text, in leverandoer_input text)
begin
update raavare
set raavare_navn = raavare_navn_input, leverandoer = leverandoer_input
where raavare_id = raavare_id_input;
end; //

/* MANGLER SLET RAAVARE */

/*OPRET RECEPT */
create procedure opret_recept
(in id_input int, in navn_input text)
begin 
insert into recept(recept_id, recept_navn)
values (id_input, navn_input);
end; //

/*ÆNDRE RECEPT*/
create procedure aendre_recept
(in id_input int(5), in navn_input text)
begin
update recept
set recept_navn = navn_input
where recept_id = id_input;
end; //


/*OPRET RAAVAREBATCH */
create procedure opret_raavarebatch
(in rb_id_input int(5), in rv_id_input int(5), in maengde_input real)
begin 
insert into raavarebatch(rb_id, raavare_id, maengde)
values (rb_id_input, rv_id_input, maengde_input);
end; //


/*OPRET PRODUKTBATCH */
create procedure opret_produktbatch
(in pb_id_input int(5), in status_input int(2), in recept_id_input int(5))
begin 
insert into produktbatch(pb_id, status, recept_id, dato)
values (pb_id_input, status_input, recept_id_input, NOW());
end; //



/*OPRET RECEPTKOMPONENT*/
create procedure opret_receptkomponent
(in recept_id_input int(5), in raavare_id_input int(5), in netto_input real, in tolerance_input real)
begin 
insert into receptkomponent(recept_id, raavare_id, nom_netto, tolerance)
values (recept_id_input, raavare_id_input, netto_input, tolerance_input);
end; //




/*AFVEJNING*/
create procedure afvejning
(in pb_id_input int(5), in rb_id_input int(5), in tara_input real, in netto_input real, in opr_id_input int(5))
begin start transaction;
set @gamlemaengde = (select maengde from raavarebatch where rb_id_input = rb_id);
set @nyemaengde = @gamlemaengde - netto_input; 
update raavarebatch set maengde = @nyemaengde where rb_id_input = rb_id;

/* OPRETTELSE AF PRODUKTBATCHKOMPONENT */
insert into produktbatchkomponent(pb_id, rb_id, tara, netto, opr_id)
values (pb_id_input, rb_id_input, tara_input, netto_input, opr_id_input);

if(0 not in (select antal_komp from produktbatch_administration where pb_id=pb_id_input))
then update produktbatch set status=1 where pb_id=pb_id_input;
end if;

if ((select antal_komp from produktbatch_administration where pb_id=pb_id_input) in (select antal_faerdige from produktbatch_administration where pb_id=pb_id_input))
then update produktbatch set status=2 where pb_id=pb_id_input;
end if;

if(@gamlemaengde = (select maengde from raavarebatch where rb_id_input = rb_id) + (select netto from produktbatchkomponent where pb_id_input = pb_id and rb_id_input = rb_id)) and 
((select maengde from raavarebatch where rb_id_input = rb_id) >= 0)
then commit;
else rollback;
end if;
end; //
delimiter ;





/* User rights */
drop user 'server_access'@'localhost';
create user 'server_access'@'localhost' identified by 'qwer1234';

/* Stored procedure access rights */
Grant Execute on procedure aendre_raavare to 'server_access';
Grant Execute on procedure aendre_opr to 'server_access';
Grant Execute on procedure aendre_recept to 'server_access';
Grant Execute on procedure opret_opr to 'server_access';
Grant Execute on procedure opret_produktbatch to 'server_access';
Grant Execute on procedure opret_raavarebatch to 'server_access';
Grant Execute on procedure opret_receptkomponent to 'server_access';
Grant Execute on procedure opret_recept to 'server_access';
Grant Execute on procedure opret_raavare to 'server_access';
Grant Execute on procedure afvejning to 'server_access';

/* table and view access rights */
Grant Select on table operatoer to 'server_access';
Grant Select on table raavare to 'server_access';
Grant Select on afvejning to 'server_access';
Grant Select on antal_faerdige to 'server_access';
Grant Select on produktbatch_administration to 'server_access';
Grant Select on recept_administration to 'server_access';
Grant Select on raavarebatch_administration to 'server_access';
Grant Select on antal_komponenter to 'server_access';
