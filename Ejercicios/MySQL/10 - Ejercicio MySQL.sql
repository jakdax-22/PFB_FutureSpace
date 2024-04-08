/* Creación del procedimiento */
DELIMITER //
/* Voy a poner el parámetro de la categoría al final, era para utilizar sobrecarga de procedimientos, pero no me funciona*/
CREATE PROCEDURE venta_libro (P_ISBN VARCHAR(13) ,P_Unidades INTEGER (4),OUT Precio_total DECIMAL (6,2),OUT Texto VARCHAR(200))
       BEGIN
		    DECLARE P_Unidades_restantes INTEGER (4);
			DECLARE P_Precio DECIMAL (6,2);
			IF (P_ISBN IS NULL OR P_ISBN = '')THEN
				SET Texto = "Debe informar el identificador del libro";
				SET Precio_total = 0;
			ELSE
				SELECT Unidades INTO P_Unidades_restantes
                FROM STOCK
                WHERE ISBN_libro = P_ISBN;
                
                IF (P_Unidades_restantes IS NULL)THEN
					SET Texto =  "No existe ningún libro con ese identificador";
					SET Precio_total = 0;
				ELSEIF (P_Unidades <= 0)THEN
					SET Texto =  "Unidades a vender no válidas";
					SET Precio_total = 0;
                ELSE
					IF (P_Unidades_restantes = 0) THEN
						SET Texto =  "No hay stock de este ejemplar";
						SET Precio_total = 0;
					ELSEIF ((P_Unidades_restantes - P_Unidades) < 5)THEN
						SET Texto =  "No hay disponibilidad suficiente";
						SET Precio_total = 0;
					ELSE 
						SELECT PVP INTO P_Precio
                        FROM LIBROS 
                        WHERE ISBN = P_ISBN;
                        UPDATE STOCK SET Unidades = Unidades - P_Unidades WHERE ISBN_libro = P_ISBN;
                        SET Texto =  'Venta realizada!'; 
                        SET Precio_total = (P_Precio * P_Unidades);
                    END IF;
                END IF;
            END IF;
END//
DELIMITER ;

/* Probando */
/* Sin pasar ID del libro */
CALL venta_libro ('',20,@Precio,@Texto);
SELECT @Texto AS MENSAJE, @Precio AS PVP;
/* El identificador no existe */
CALL venta_libro (99,20,@Precio,@Texto);
SELECT @Texto AS MENSAJE, @Precio AS PVP;
/* Unidades negativas */
CALL venta_libro (1,-10,@Precio,@Texto);
SELECT @Texto AS MENSAJE, @Precio AS PVP;
/* No hay stock del libro */
UPDATE STOCK SET Unidades = 0 WHERE ISBN_libro = 1;
CALL venta_libro (1,1,@Precio,@Texto);
SELECT @Texto AS MENSAJE, @Precio AS PVP;
/* Hay menos de 5 ejemplares */
UPDATE STOCK SET Unidades = 10 WHERE ISBN_libro = 1;
CALL venta_libro (1,7,@Precio,@Texto);
SELECT @Texto AS MENSAJE, @Precio AS PVP;
/* Pasa por todos los condicionales y llega al final */
CALL venta_libro (1,3,@Precio,@Texto);
SELECT @Texto AS MENSAJE, @Precio AS PVP;
/* Veo que han bajado las unidades en stock */
SELECT * FROM STOCK;
/* Veo que el precio se correponde */
SELECT PVP FROM LIBROS WHERE ISBN = 1;
