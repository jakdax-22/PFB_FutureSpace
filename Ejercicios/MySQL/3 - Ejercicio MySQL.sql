/* Aquí he pensado en hacerlo con subconsultas */
SELECT * FROM LIBROS 
WHERE Id_autor IN (
	# Subconsulta de los libros de Martos Etxeberria 
	SELECT Id FROM AUTORES
    WHERE (Nombre = 'Fernando' && Apellidos = 'Martos Etxeberria')
)
/* Aquí lo hago así con subconsulta y no pongo Los Godos a secas porque puede haber otro libro de otro autor que se llame igual */
OR (Titulo = 'Los Godos' AND Id_autor IN (
	SELECT Id FROM AUTORES
    WHERE (Nombre = 'Miguel' && Apellidos = 'Limón Ignacio')
)) 
ORDER BY Ano_publicacion ASC;
/* Por defecto creo que es ASC pero lo pongo de todos modos */
 /* Comprobación */
 SELECT * FROM LIBROS WHERE Id_autor = 4 OR Id_autor = 6;