create database recuperacion;
USE recuperacion;

CREATE TABLE `recuperacion`.`dias` (
  `iddias` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `dias` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`iddias`));

INSERT INTO `recuperacion`.`dias` (`dias`) values(1),(2),(3),(4),(5),(6),(7),(8),(9),(10),
(11),(12),(13),(14),(15),(16),(17),(18),(19),(20),
(21),(22),(23),(24),(25),(26),(27),(28),(29),(30),(31);


CREATE TABLE `recuperacion`.`mes` (
  `idmes` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `mes` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`idmes`));

INSERT INTO `recuperacion`.`mes` (`mes`) VALUES ('Enero'),('Febrero'),('Marzo'),('Abril'),('Mayo'),('Junio'),
('Julio'),('Agosto'),('Septiembre'),('Octubre'),('Noviembre'),('Diciembre');

CREATE TABLE `recuperacion`.`anio` (
  `idanio` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `anio` INT NOT NULL,
  PRIMARY KEY (`idanio`));
  
 INSERT INTO `recuperacion`.`anio` (`anio`) VALUES (1980),(1981),(1982),(1983),(1984),(1985),(1986),(1987),(1988),(1989),(1990),
(1991),(1992),(1993),(1994),(1995),(1996),(1997),(1998),(1999),(2000),
(2001),(2002),(2003),(2004),(2005),(2006),(2007),(2008),(2009),(2010),
(2011),(2012),(2013),(2014),(2015),(2016),(2017),(2018),(2019),(2020),(2021),(2022),(2023);
  

CREATE TABLE `recuperacion`.`fecha` (
  `idfecha` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  PRIMARY KEY (`idfecha`));

select * from dias;
select * from mes;
select * from anio;
select * from fecha;

INSERT INTO `recuperacion`.`fecha` (`fecha`) VALUES ('1980-1-1');

delete fecha from fecha
where idfecha=2;


