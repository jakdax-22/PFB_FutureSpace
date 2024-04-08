/* Añadimos a histórico el campo diferencia */
ALTER TABLE HISTORICO ADD Diferencia INTEGER (4);
/* Creación de la función */
CREATE FUNCTION devolverDiferencia (antiguo Integer(4), nuevo Integer (4))
   RETURNS INTEGER(4)
   RETURN ABS(antiguo - nuevo);
/* Creación del TRIGGER, me gustaría usar CREATE OR REPLACE, pero no lo coge, así que borro e inicio de nuevo */
DROP TRIGGER Paso_historico;
DELIMITER //
CREATE TRIGGER Paso_historico 
BEFORE UPDATE ON STOCK 
FOR EACH ROW 
	BEGIN
		DECLARE resto INTEGER (4);
		IF (old.Unidades <> new.Unidades) THEN
			SET resto = devolverDiferencia (old.Unidades,new.Unidades);
			INSERT INTO HISTORICO VALUES (old.ISBN_libro,old.Id_formato,old.Unidades,current_date(),resto);
		END IF;
    END //
DELIMITER ;
/* Probando si funciona */
/* Esto es por si la tabla no tiene registros, por tener uno para empezar 
INSERT INTO STOCK VALUES (1,1,15,'2024/04/05');
*/
UPDATE STOCK SET Unidades = 35 WHERE ISBN_libro = 1;
/* Probando que funcione */
SELECT * FROM HISTORICO;