/* Ver la estructura */
DESCRIBE EDITORIALES;
/* Meter distinto casos en los que devolver un valor u otro, muy parecido a un Switch */
SELECT 
	CASE WHEN Nombre = 'RBA' THEN 'EDITORIAL 1'
		WHEN Nombre = 'PLANETA' THEN 'EDITORIAL 2'
		WHEN Nombre = 'O`REILLY' THEN 'EDITORIAL 3'
		/* Si no es ningúo de los casos devuelvo el nombre real */
        ELSE 
			Nombre
	END AS Editorial
FROM EDITORIALES;
	