/* Creación del procedimiento */
DROP PROCEDURE modifica_precio
DELIMITER //
CREATE PROCEDURE modifica_precio (Tipo CHAR(1), Porcentaje INTEGER(3), P_Categoria VARCHAR(40),P_Autor INTEGER (4))
       BEGIN
       DECLARE P_Autor_nombre VARCHAR (30);
       DECLARE P_Autor_apellidos VARCHAR (50);
		IF (P_Categoria IS NULL OR P_Categoria = '') THEN
			UPDATE LIBROS 
            SET PVP = CASE WHEN upper(Tipo) = 'S' THEN PVP * (1 + (Porcentaje / 100))
							WHEN upper(Tipo) = 'B' THEN PVP * (1 - (Porcentaje / 100))
						END;
        ELSE
			/* Aquí voy a añadir la parte del autor, ya que si está vacío haré update de todo y si no me ceñiré a ambas condiciones */
			IF (P_Autor IS NULL OR P_Autor = '') THEN
				UPDATE LIBROS 
				SET PVP = CASE WHEN upper(Tipo) = 'S' THEN PVP * (1 + (Porcentaje / 100))
							WHEN upper(Tipo) = 'B' THEN PVP * (1 - (Porcentaje / 100))
							END
				WHERE Nombre_tematica = P_Categoria;
			ELSE
				UPDATE LIBROS 
				SET PVP = CASE WHEN upper(Tipo) = 'S' THEN PVP * (1 + (Porcentaje / 100))
								WHEN upper(Tipo) = 'B' THEN PVP * (1 - (Porcentaje / 100))
							END
				WHERE Id_autor = P_Autor AND Nombre_tematica = P_Categoria;
			END IF;
		END IF;
END//
DELIMITER ;

/* Pruebas para ver si funciona */
SELECT * FROM LIBROS;
/* Esta llamada debería subir el precio de todos */
CALL modifica_precio('S',20,'',NULL);
/* Esta llamada los baja todos y los deja como estaban, aunque me sale que ha truncado el valor, por lo que no se quedan exactamente igual */
CALL modifica_precio('B',20,'',NULL);
/* Esta llamada solo baja los de la categoria NOVELA y la autora Joanne Rowling*/
CALL modifica_precio('B',20,'NOVELA',5);
/* Vuelvo a subir los precios de NOVELA de ROWLING */
CALL modifica_precio('s',20,'NOVELA',5);
/* Esta llamada baja los de la categoría HISTORIA del autor ALBERTO MIGUELEZ LOPEZ*/
CALL modifica_precio('B',20,'HISTORIA',2);
/* Esta llamada vuelve a subir los de la categoría HISTORIA */
CALL modifica_precio('S',20,'HISTORIA',2);
/* Poniendo solo la categoría y no el autor, se deberían subir todos los de esa categoría solo */
CALL modifica_precio('S',20,'HISTORIA',NULL);
CALL modifica_precio('b',20,'HISTORIA',NULL);
/* Como va sacando decimales y truncando va variando un poco después de cada subida y bajada */



