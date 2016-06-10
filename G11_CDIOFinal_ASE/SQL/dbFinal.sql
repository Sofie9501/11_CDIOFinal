use final;

/* must be dropped in this order to avoid constraint violations */
DROP TABLE IF EXISTS productBatchComponent;
DROP TABLE IF EXISTS productBatch;
DROP TABLE IF EXISTS operator;
DROP TABLE IF EXISTS recipeComponent;
DROP TABLE IF EXISTS recipe;
DROP TABLE IF EXISTS ingredientBatch;
DROP TABLE IF EXISTS ingredient;
DROP TABLE IF EXISTS role;

CREATE TABLE role(role_id INT PRIMARY KEY, role_name TEXT) ENGINE = innoDB;

CREATE TABLE operator(opr_id INT PRIMARY KEY, opr_name TEXT,  cpr TEXT, password TEXT, role_id INT, active boolean,
FOREIGN KEY (role_id) REFERENCES role(role_id)) ENGINE=innoDB;
 
CREATE TABLE ingredient(ingredient_id INT PRIMARY KEY, ingredient_name TEXT, supplier TEXT, active boolean) ENGINE=innoDB;

CREATE TABLE ingredientBatch(ib_id INT PRIMARY KEY, ingredient_id INT, amount REAL, recieveDate date, active boolean,
   FOREIGN KEY (ingredient_id) REFERENCES ingredient(ingredient_id)) ENGINE=innoDB;

CREATE TABLE recipe(recipe_id INT PRIMARY KEY, recipe_name TEXT, active boolean) ENGINE=innoDB;

CREATE TABLE recipeComponent(recipe_id INT, ingredient_id INT, nom_net REAL, tolerance REAL,
   PRIMARY KEY (recipe_id, ingredient_id), 
   FOREIGN KEY (recipe_id) REFERENCES recipe(recipe_id), 
   FOREIGN KEY (ingredient_id) REFERENCES ingredient(ingredient_id)) ENGINE=innoDB;

CREATE TABLE productBatch(pb_id INT PRIMARY KEY, status INT, recipe_id INT, startDate DATE, endDate date, active boolean,
   FOREIGN KEY (recipe_id) REFERENCES recipe(recipe_id)) ENGINE=innoDB;

CREATE TABLE productBatchComponent(pb_id INT, ib_id INT, tara REAL, net REAL, opr_id INT, 
   PRIMARY KEY (pb_id, ib_id), 
   FOREIGN KEY (pb_id) REFERENCES productBatch(pb_id), 
   FOREIGN KEY (ib_id) REFERENCES ingredientBatch(ib_id), 
   FOREIGN KEY (opr_id) REFERENCES operator(opr_id)) ENGINE=innoDB;
   
   insert into role values
   (1, 'Administrator'),
   (2, 'Farmaceut'),
   (3, 'Værkfører'),
   (4, 'Operatør');
   
   insert into operator values
   (1, 'Niller', '1910948899','Qwer1234', 1, true),
   (99999999, 'Sofie Paludan Larsen', '1910945588','Qwer1234', 2, true),
   (4545, 'Casper Danielsen', '1910942233', 'Qwer1234', 3, true),
   (121212, 'Brian Christensen', '1910943399', 'Qwer1234', 4, true),
   (98765, 'Morten Due', '1910941177', 'Qwer1234', 3, false),
   (112, 'Cecilie Lindberg', '1910942222', 'Qwer1234', 2, false);
   
   insert into ingredient values
   (1, 'Citron', 'Citronland', true),
   (99, 'Vand', 'Vandland', false),
   (22222222, 'qwertyuiop123456789ø', '987654321åpoiuytrewq', true);
   
   insert into ingredientBatch values
   (99999999, 1, 0.5, '2015-06-16', true),
   (1, 99, 1, '2003-03-15', true),
   (222, 99, 0.1, '2001-01-13', false),
   (45, 22222222, 2, '1931-01-23', true);
   
   insert into recipe values
   (1, 'Vandsaft', true),
   (99999999, 'asdfghjklæøåpoiuytre', true),
   (456, 'Citron', false);
   
   insert into recipeComponent values
   (1, 22222222, 0.5, 0.1),
   (1, 1, 0.1, 0.02),
   (99999999, 22222222, 2, 0.2),
   (456, 99, 2, 0.1),
   (456, 1, 1, 0.2);
   
   insert into productBatch values 
   (1, 0, 1, '2003-04-05', null, true),
   (9999999, 1, 99999999, '2006-06-16', null, true),
   (45, 2, 456, '2015-05-20', '2016-06-06', true),
   (123, 1, 99999999, '2016-03-20', null, false);
   
   insert into productBatchComponent values
   (9999999, 45, 0.9, 1.9, 121212),
   (45, 1, 1, 0.1, 121212),
   (45, 45, 3, 2.1, 121212),
   (123, 222, 1, 2, 1);
   
/*******************************************
* VIEWS									   * 
*******************************************/
Drop view if exists recipe_administration;
Drop view if exists ingredientBatch_administration;
Drop view if exists number_done;
Drop view if exists number_Components;
Drop view if exists productBatch_administration;
Drop view if exists weighing;
Drop view if exists ase_info;


create view recipe_administration 
as select recipe_id,recipe_name, ingredient_id, ingredient_name, tolerance, nom_net
from recipe natural join recipeComponent join ingredient using (ingredient_id); 

create view ingredientBatch_administration
as select ib_id, ingredient_name, ingredient_id, amount, ingredientBatch.active, recieveDate
from ingredient left join ingredientBatch using( ingredient_id)
order by ib_id;

create view number_done as SELECT productBatch.pb_id, COUNT(productBatchComponent.pb_id) as antal_faerdige, recipe_id, startdate, endDate, status, active
FROM productBatch
LEFT JOIN productBatchComponent on productBatchComponent.pb_id=productBatch.pb_id group by productBatch.pb_id;

create view number_Components as select recipe_id, count(ingredient_id) as antal_Comp
from recipeComponent group by recipe_id;

create view productBatch_administration as select pb_id, recipe_id, recipe_name, antal_Comp, antal_faerdige, startdate, endDate, status, number_done.active
from number_Components natural join number_done left join recipe using (recipe_id);

create view ase_info as select nom_net, tolerance, pb_id, ib_id
from productbatch natural join ingredientbatch natural join recipecomponent;

/*
create view weighing as select pb_id, ingredient_id , ingredient_name, nom_netto, tolerance 
from productBatch natural join recipeComponent natural join ingredient 
where (recipeComponent.ingredient_id, productBatch.pb_id) not in(
select ingredient_id, pb_id from productBatchComponent natural join ingredientBatch);*/

/*******************************************
* STORED PROCEDURES						   * 
*******************************************/
drop procedure if exists create_opr;
DROP PROCEDURE IF EXISTS update_opr;
drop procedure if exists create_ingredient;  
drop procedure if exists update_ingredient; 
DROP PROCEDURE IF EXISTS create_recipe;
DROP PROCEDURE IF EXISTS update_recipe;
DROP PROCEDURE IF EXISTS create_ingredientBatch;
DROP PROCEDURE IF EXISTS update_ingredientBatch;
DROP PROCEDURE IF EXISTS create_productBatch;
DROP PROCEDURE IF EXISTS update_productBatch;
DROP PROCEDURE IF EXISTS create_recipeComponent;
DROP PROCEDURE IF EXISTS update_recipeComponent;
DROP PROCEDURE IF EXISTS create_productbatchcomponent;
DROP PROCEDURE IF EXISTS update_productbatchstatus;
drop procedure if exists weighing;
	
delimiter //

/* create OPERATOR */
create procedure create_opr
(in id_input int(8), in role_input int(1), in name_input varchar(21), in cpr_input varchar(10), in password_input varchar(8))
begin 
insert into operator(opr_id, role_id, opr_name, cpr, password, active)
values (id_input, role_input, name_input,  cpr_input, password_input, true);
end; //


/* update operator */
create procedure update_opr
(in id_old_input int(8), in id_new_input int(8),  in role_input int(1), in name_input varchar(21), 
in cpr_input varchar(10), in password_input varchar(8), in active_input boolean)
begin
if (id_old_input not in (select opr_id from productBatchComponent) 
and id_new_input not in (select opr_id from operator)) then 
update operator
set opr_id = id_new_input, opr_name = name_input, role_id = role_input, cpr = cpr_input, password = password_input, active = active_input
where opr_id = id_old_input;
else
update operator
set opr_name = name_input, role_id = role_input, cpr = cpr_input, password = password_input, active = active_input
where opr_id = id_old_input;
end if;
end; //

/* create ingredient */
create procedure create_ingredient
(in ingredient_id_input int(8), in ingredient_name_input varchar(21), in leverandoer_input varchar(21))
begin
insert into ingredient(ingredient_id, ingredient_name, supplier, active)
values (ingredient_id_input, ingredient_name_input, leverandoer_input, true); 
end; //

/* update ingredient */
create procedure update_ingredient
(in ingredient_old_id_input int(8), in ingredient_new_id_input int(8), in ingredient_name_input varchar(21), in leverandoer_input varchar(21), in active_input boolean)
begin
if (ingredient_old_id_input not in (select ingredient_id from ingredientbatch) 
	and ingredient_old_id_input not in (select ingredient_id from recipecomponent)
    and ingredient_new_id_input not in (select ingredient_id from ingredient)) then 
update ingredient
set ingredient_id = ingredient_new_id_input,  ingredient_name=ingredient_name_input, supplier = leverandoer_input, active = active_input
where ingredient_id = ingredient_old_id_input;
else
update ingredient
set active = active_input
where ingredient_id = ingredient_old_id_input;
end if;
end; //


/*create recipe */
create procedure create_recipe
(in id_input int, in name_input text)
begin 
insert into recipe(recipe_id, recipe_name, active)
values (id_input, name_input, true);
end; //

/*update recipe*/
create procedure update_recipe
(in id_old_input int(8), in id_new_input int(8), in name_input varchar(21), in active_input boolean)
begin
if(id_old_input not in (select recipe_id from productbatch)
and id_new_input not in (select recipe_id from recipe)) then
update recipe
set recipe_id = id_new_input, recipe_name = name_input, active = active_input
where recipe_id = id_old_input;
else
update recipe
set active = active_input
where recipe_id = id_old_input;
end if;
end; //


/*create ingredientBatch */
create procedure create_ingredientBatch
(in ib_id_input int(8), in rv_id_input int(8), in amount_input real)
begin 
insert into ingredientBatch(ib_id, ingredient_id, amount, recieveDate, active)
values (ib_id_input, rv_id_input, amount_input, now(), true);
end; //

/*update ingredientBatch */
create procedure update_ingredientbatch
(in ib_old_id_input int(8), in ib_new_id_input int(8), in rv_id_input int(8), in amount_input real, in active_input boolean)
begin
if (ib_old_id_input not in (select ib_id from productbatchcomponent)
and ib_new_id_input not in (select ib_id from productbatch)) then
update ingredientbatch
set ib_id = ib_new_id_input, ingredient_id = rv_id_input, amount=amount_input, active = active_input
where ib_id = ib_old_id_input;
else 
update ingredientbatch
set active = active_input
where ib_id = ib_old_id_input;
end if;
end; //

/*create productBatch */
create procedure create_productBatch
(in pb_id_input int(8),  in recipe_id_input int(8))
begin 
insert into productBatch(pb_id, status, recipe_id, startDate, endDate, active)
values (pb_id_input, 0, recipe_id_input, NOW(), null, true);
end; //

/*update productBatch*/
create procedure update_productBatch
(in pb_old_id_input int(8), in pb_new_id_input int(8), in recipe_id_input int(8), in active_input boolean)
begin
if (pb_old_id_input not in (select pb_id from productbatchcomponent)
and pb_new_id_input not in (select pb_id from productbatch)) then
update productbatch
set pb_id = pb_new_id_input, recipe_id = recipe_id_input, active = active_input
where pb_id = pb_old_id_input;
else 
update productbatch
set status = status_input, active = active_input
where pb_id = pb_old_id_input;
end if;
end; //

/*update productbatch status*/
create procedure update_productbatchstatus
(in pb_id_input int(8), in status_input int(1))
begin
update productbatch
set status = status_input
where pb_id = pb_id_input;
end; //


/*create recipeComponent*/
create procedure create_recipeComponent
(in recipe_id_input int(8), in ingredient_id_input int(8), in netto_input real, in tolerance_input real)
begin 
insert into recipeComponent(recipe_id, ingredient_id, nom_net, tolerance)
values (recipe_id_input, ingredient_id_input, netto_input, tolerance_input);
end; //

/*update recipeComponent*/
create procedure update_recipeComponent
(in recipe_id_old_input int(8), in recipe_id_new_input int(8), in ingredient_old_id_input int(8), in ingredient_new_id_input int(8), in net_input real, in tolerance_input real)
begin
if(recipe_id_old_input not in (select recipe_id from productbatch)
and recipe_id_new_input in (select recipe_id from recipe)
and ingredient_new_id_input in (select ingredient_id from ingredient))then
update recipecomponent
set recipe_id = recipe_id_new_input, ingredient_id = ingredient_new_id_input, nom_net = net_input, tolerance = tolerance_input
where recipe_id = recipe_id_old_input and ingredient_id = ingredient_old_id_input;
end if;
end; //

/*create productbatchcomponent*/
create procedure create_productbatchcomponent
(in pb_id_input int(8), in ib_id_input int(8), in tara_input real, in net_input real, in opr_id_input int(8))
begin start transaction;
set @oldamount = (select amount from ingredientBatch where ib_id_input = ib_id);
set @newamount = @gamleamount - net_input; 
update ingredientBatch set amount = @nyeamount where ib_id_input = ib_id;

insert into productbatchcomponent(pb_id, ib_id, tara, net, opr_id)
values (pb_id_input, ib_id_input, tara_input, net_input, opr_id_input);

if ((select antal_Comp from productBatch_administration where pb_id = pb_id_input) in (select antal_faerdige from productBatch_administration where pb_id = pb_id_input))
then update productBatch set status = 2 where pb_id = pb_id_input;
end if;

if(@gamleamount = (select amount from ingredientBatch where ib_id_input = ib_id) + (select net from productBatchComponent where pb_id_input = pb_id and ib_id_input = ib_id)) and 
((select amount from ingredientBatch where ib_id_input = ib_id) >= 0)
then commit;
else rollback;
end if;
end; //
delimiter ;


/* User rights */
drop user 'server_access'@'localhost';
drop user 'ase_access'@'localhost';
create user 'server_access'@'localhost' identified by 'qwer1234';
create user 'ase_access'@'localhost' identified by 'zxcvbnm';

/* Stored procedure access rights */
Grant Execute on procedure create_ingredient to 'server_access' @'localhost';
Grant Execute on procedure update_opr to 'server_access'@'localhost';
Grant Execute on procedure update_recipe to 'server_access'@'localhost';
Grant Execute on procedure create_opr to 'server_access'@'localhost';
Grant Execute on procedure create_productBatch to 'server_access'@'localhost';
Grant Execute on procedure update_productBatch to 'server_access'@'localhost';
Grant Execute on procedure create_ingredientBatch to 'server_access'@'localhost';
Grant Execute on procedure update_ingredientBatch to 'server_access'@'localhost';
Grant Execute on procedure create_recipeComponent to 'server_access'@'localhost';
Grant Execute on procedure update_recipeComponent to 'server_access'@'localhost';
Grant Execute on procedure create_recipe to 'server_access'@'localhost';
Grant Execute on procedure update_ingredient to 'server_access'@'localhost';
Grant Execute on procedure create_productbatchcomponent to 'ase_access'@'localhost';
Grant Execute on procedure update_productbatchstatus to 'ase_access'@'localhost';
/*Grant Execute on procedure weighing to 'server_access';*/

/* table and view access rights */
Grant Select on table operator to 'server_access'@'localhost';
Grant Select on table ingredient to 'server_access'@'localhost';
Grant Select on table recipe to 'server_access'@'localhost';
Grant Select on table recipeComponent to 'server_access'@'localhost';
Grant Select on number_done to 'server_access'@'localhost';
Grant Select on productBatch_administration to 'server_access'@'localhost';
Grant Select on ingredientBatch_administration to 'server_access'@'localhost';
Grant Select on number_Components to 'server_access'@'localhost';
Grant Select on recipe_administration to 'server_access'@'localhost';
Grant select on ase_info to 'ase_access'@'localhost';
Grant select on operator to 'ase_access'@'localhost';
Grant select on productBatch_administration to 'ase_access'@'localhost';
Grant select on ingredientBatch_administration to 'ase_access'@'localhost';

/*
Grant Select on weighing to 'server_access';
*/