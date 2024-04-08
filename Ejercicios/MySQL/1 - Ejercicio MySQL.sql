CREATE DATABASE IF NOT EXISTS eiranzo; /* Creación de la base de datos */
SHOW DATABASES LIKE 'eiranzo'; /* Comprobar que se ha creado correctamente */
USE eiranzo; /* Uso la BD creada */
CREATE TABLE AUTOMOVILES (
	Codigo INTEGER(2) AUTO_INCREMENT PRIMARY KEY, /* No le pongo NOT NULL porque al ser PK ya lo es, aparte es un autoincrementativo */
	Nombre_marca VARCHAR(80) NOT NULL, /*Alfanumérico obligatorio*/
    Precio DECIMAL(8,2) NOT NULL, /* Ocho posiciones con dos decimales, obligatorio */
    Fecha DATE /* No obligatorio */
);
DESCRIBE AUTOMOVILES; /* Compruebo los campos de la tabla */
#1
/* Las inserciones que se nos pide */
INSERT INTO AUTOMOVILES (Nombre_marca,Precio,Fecha) VALUES ('Peugeot',32000,'2023/09/25');
INSERT INTO AUTOMOVILES (Nombre_marca,Precio,Fecha) VALUES ('Opel ',25500.50,'2022/09/19');
INSERT INTO AUTOMOVILES (Nombre_marca,Precio) VALUES ('Audi ',37000);
INSERT INTO AUTOMOVILES (Nombre_marca,Precio) VALUES ('Dacia ',24000);

SELECT * FROM AUTOMOVILES;/*Comprobar que se han hecho correctamente las inserciones*/
#2
SELECT DISTINCT Nombre_marca FROM AUTOMOVILES WHERE Nombre_marca LIKE '%L%';/* Consulta de las marcas que contienen L, creo que aquí está bien un Distinct para no repetir */
#3
SELECT * FROM AUTOMOVILES WHERE Precio BETWEEN 32000 AND 37000;/*Automoviles con precio entre 32000 y 37000 */
#4
/* Creación de la tabla MODELOS */
CREATE TABLE MODELOS (
	CodModelo INTEGER(2) PRIMARY KEY,
    Nombre VARCHAR(100)
);
DESC MODELOS; /* Comprobación */
#5
ALTER TABLE AUTOMOVILES ADD CodModelo INTEGER(2), ADD FOREIGN KEY (CodModelo) REFERENCES MODELOS(CodModelo); /* Alteración de la tabla para la inserción de un campo que apunta a otra tabla */ 
/* Prueba */
INSERT INTO MODELOS VALUES(22,'A4');
/* Sin Modelo registrado debería dar error */
INSERT INTO AUTOMOVILES(Nombre_marca,Precio,CodModelo) VALUES ('Mercedes',60000,02);
/* Con modelo registrado debería funcionar */
INSERT INTO AUTOMOVILES(Nombre_marca,Precio,CodModelo) VALUES ('Audi',70000,22);