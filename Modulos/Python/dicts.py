#diccionarios, parecidos a los maps de Java
mydict = {"name":"Max","age": 28, "city":"New York"};
print (mydict);

mydict["email"] = "max@xyz.com";
print (mydict);

#borrar
del mydict["name"];
print (mydict);

#try-catch
try:
    print (mydict["lastName"]);
except:
    print ("Error");

#bucle de claves
for key in mydict.keys():
    print (key);