/* Creación de la tabla formatos */
CREATE TABLE FORMATOS (
		Id INTEGER (1) AUTO_INCREMENT PRIMARY KEY,
        Descripcion VARCHAR (40) NOT NULL
);
/* Comprobación */
DESCRIBE FORMATOS;
/* Inserción de los datos */
INSERT INTO FORMATOS (Descripcion) VALUES ('Tapa blanda');
INSERT INTO FORMATOS (Descripcion) VALUES ('Tapa dura');
/* Comprobación de la inserción */
SELECT * FROM FORMATOS;
/* Creación de la tabla STOCK */
CREATE TABLE STOCK (
	ISBN_libro VARCHAR (13),
	Id_formato INTEGER (1),
    Unidades INTEGER (4),
    Fecha DATE,
    FOREIGN KEY (ISBN_libro) REFERENCES LIBROS(ISBN),
    FOREIGN KEY (Id_formato) REFERENCES FORMATOS(Id),
    CONSTRAINT PRIMARY KEY (ISBN_libro,Id_formato)
);
/* Comprobación de campos */
DESCRIBE STOCK;
/* Inserción de datos para la comprobación */
INSERT INTO STOCK VALUES (1,1,20,'2024/04/05');
/* Comprobación */
SELECT * FROM STOCK;
/* Creación de la tabla HISTORICO */
CREATE TABLE HISTORICO (
	ISBN_libro VARCHAR (13),
	Id_formato INTEGER (1),
    Unidades INTEGER (4),
    Fecha DATE
);
/* Comprobación de campos */
DESCRIBE HISTORICO;
/* CREACIÓN DEL TRIGGER */
DELIMITER //
CREATE TRIGGER Paso_historico 
BEFORE UPDATE ON STOCK 
FOR EACH ROW 
	BEGIN
		IF (old.Unidades <> new.Unidades) THEN
			INSERT INTO HISTORICO VALUES (old.ISBN_libro,old.Id_formato,old.Unidades,current_date());
		END IF;
    END //
DELIMITER ;
/* Comprobación del TRIGGER, este debería hacerlo funcionar */
UPDATE STOCK SET Unidades = 25 WHERE ISBN_libro = 1;
/* Comprobación */
SELECT * FROM HISTORICO;
/* Segunda comprobación, esta no debería dispararlo */
UPDATE STOCK SET Unidades = 25 WHERE ISBN_libro = 1;
/* Comprobación */
SELECT * FROM HISTORICO;
/* Tercera comprobación, si la fecha se cambia no se debería disparar */
UPDATE STOCK SET Fecha = '2024/02/05' WHERE ISBN_libro = 1;
/* Comprobación */
SELECT * FROM HISTORICO;

