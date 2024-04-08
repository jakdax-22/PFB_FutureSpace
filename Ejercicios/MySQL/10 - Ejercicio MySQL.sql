/* Creación del procedimiento */
DELIMITER //
/* Voy a poner el parámetro de la categoría al final, era para utilizar sobrecarga de procedimientos, pero no me funciona*/
CREATE PROCEDURE venta_libro (P_ISBN VARCHAR(13) ,P_Unidades INTEGER (4))
       BEGIN
		    DECLARE P_Unidades_restantes INTEGER (4);
			DECLARE P_Precio DECIMAL (6,2);
			IF (P_ISBN IS NULL OR P_ISBN = '')THEN
				SELECT "Debe informar el identificador del libro" AS "Mensaje";
			ELSE
				SELECT Unidades INTO P_Unidades_restantes
                FROM STOCK
                WHERE ISBN_libro = P_ISBN;
                
                IF (P_Unidades_restantes IS NULL)THEN
					SELECT "No existe ningún libro con ese identificador" AS Mensaje;
				ELSEIF (P_Unidades <= 0)THEN
					SELECT "Unidades a vender no válidas" AS Mensaje;
                ELSE
					IF (P_Unidades_restantes = 0) THEN
						SELECT "No hay stock de este ejemplar" AS Mensaje;
					ELSEIF ((P_Unidades_restantes - P_Unidades) < 5)THEN
						SELECT "No hay disponibilidad suficiente" AS Mensaje;
					ELSE 
						SELECT PVP INTO P_Precio
                        FROM LIBROS 
                        WHERE ISBN = P_ISBN;
                        UPDATE STOCK SET Unidades = Unidades - P_Unidades WHERE ISBN_libro = P_ISBN;
                        SELECT 'Venta realizada!' AS MENSAJE, (P_Precio * P_Unidades) AS Precio;
                    END IF;
                END IF;
            END IF;
END//
DELIMITER ;

/* Probando */
/* Sin pasar ID del libro */
CALL venta_libro ('',20);
/* El identificador no existe */
CALL venta_libro (99,20);
/* Unidades negativas */
CALL venta_libro (1,-3);
/* No hay stock del libro */
UPDATE STOCK SET Unidades = 0 WHERE ISBN_libro = 1;
CALL venta_libro (1,3);
/* Hay menos de 5 ejemplares */
UPDATE STOCK SET Unidades = 10 WHERE ISBN_libro = 1;
CALL venta_libro (1,6);
/* Pasa por todos los condicionales y llega al final */
CALL venta_libro (1,3);
/* Veo que han bajado las unidades en stock */
SELECT * FROM STOCK;
/* Veo que el precio se correponde */
SELECT PVP FROM LIBROS WHERE ISBN = 1;
