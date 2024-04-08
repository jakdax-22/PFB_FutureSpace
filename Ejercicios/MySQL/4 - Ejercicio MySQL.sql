/* He hecho un COUNT y un Alias para que aparezca como cuenta, luego simplemente he agrupado por los otros dos campos de los que se hace SELECT */
SELECT Nombre,Apellidos, COUNT(LIBROS.ISBN) AS Cuenta
FROM AUTORES
# Es un left join porque en el caso de que haya un autor sin libros quiero que en el recuento le salga 0
LEFT JOIN LIBROS ON AUTORES.Id = LIBROS.Id_autor
#Agrupo por nombre y apellidos
GROUP BY Nombre,Apellidos;