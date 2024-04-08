/* Añadir nuevo campo a la tabla LIBROS */
ALTER TABLE LIBROS ADD PVP DOUBLE (5,2);
/* Introducir el PVP de cada libro */
UPDATE LIBROS SET PVP = 8.80 WHERE ISBN = 1;
UPDATE LIBROS SET PVP = 8.10 WHERE ISBN = 2;
UPDATE LIBROS SET PVP = 8.20 WHERE ISBN = 3;
UPDATE LIBROS SET PVP = 8.30 WHERE ISBN = 4;
UPDATE LIBROS SET PVP = 8.40 WHERE ISBN = 5;
UPDATE LIBROS SET PVP = 8.50 WHERE ISBN = 6;
UPDATE LIBROS SET PVP = 8.60 WHERE ISBN = 7;
UPDATE LIBROS SET PVP = 8.70 WHERE ISBN = 8;
UPDATE LIBROS SET PVP = 8.90 WHERE ISBN = 9;
UPDATE LIBROS SET PVP = 9.00 WHERE ISBN = 10;
UPDATE LIBROS SET PVP = 9.10 WHERE ISBN = 11;
UPDATE LIBROS SET PVP = 9.20 WHERE ISBN = 12;
UPDATE LIBROS SET PVP = 9.30 WHERE ISBN = 13;
UPDATE LIBROS SET PVP = 9.40 WHERE ISBN = 14;
UPDATE LIBROS SET PVP = 9.50 WHERE ISBN = 15;
UPDATE LIBROS SET PVP = 9.60 WHERE ISBN = 16;
UPDATE LIBROS SET PVP = 9.70 WHERE ISBN = 17;
UPDATE LIBROS SET PVP = 9.80 WHERE ISBN = 18;
UPDATE LIBROS SET PVP = 9.90 WHERE ISBN = 19;
UPDATE LIBROS SET PVP = 10.10 WHERE ISBN = 20;

/* Comprobación de los updates */
SELECT * FROM LIBROS ORDER BY ISBN;
USE eiranzo;
/* Creación del procedimiento */
DELIMITER //
/* Voy a poner el parámetro de la categoría al final, era para utilizar sobrecarga de procedimientos, pero no me funciona*/
CREATE PROCEDURE modifica_precio (Tipo CHAR(1), Porcentaje INTEGER(3), P_Categoria VARCHAR(40))
       BEGIN
		IF (Porcentaje <= 0)THEN
			SELECT "No se pueden porcentajes menores o iguales a 0" AS MENSAJE;
        ELSEIF (Tipo NOT IN('B','b','S','s'))THEN
			SELECT "Tipo no reconocido" AS MENSAJE;
		ELSEIF (P_Categoria IS NULL OR P_Categoria = '') THEN
			UPDATE LIBROS 
            SET PVP = CASE WHEN upper(Tipo) = 'S' THEN PVP * (1 + (Porcentaje / 100))
							WHEN upper(Tipo) = 'B' THEN PVP * (1 - (Porcentaje / 100))
			END;
        ELSE
			UPDATE LIBROS 
            SET PVP = CASE WHEN upper(Tipo) = 'S' THEN PVP * (1 + (Porcentaje / 100))
							WHEN upper(Tipo) = 'B' THEN PVP * (1 - (Porcentaje / 100))
						END
			WHERE Nombre_tematica = P_Categoria;
		END IF;
END//
DELIMITER ;

/* Pruebas para ver si funciona */
SELECT * FROM LIBROS;
/* Poniendo un tipo no reconocido */
CALL modifica_precio ('dfsf',20,'');
/* Poniendo un tipo no reconocido */
CALL modifica_precio ('S',-20,'');
/* Esta llamada debería subir el precio de todos */
CALL modifica_precio('S',20,'');
/* Esta llamada los baja todos y los deja como estaban, aunque me sale que ha truncado el valor, por lo que no se quedan exactamente igual */
CALL modifica_precio('B',20,'');
/* Esta llamada solo baja los de la categoria NOVELA */
CALL modifica_precio('B',20,'NOVELA');
/* Vuelvo a subir los precios de NOVELA */
CALL modifica_precio('s',20,'NOVELA');
/* Esta llamada baja los de la categoría HISTORIA */
CALL modifica_precio('B',20,'HISTORIA');
/* Esta llamada vuelve a subir los de la categoría HISTORIA */
CALL modifica_precio('S',20,'HISTORIA');
/* Como va sacando decimales y truncando va variando un poco después de cada subida y bajada */



