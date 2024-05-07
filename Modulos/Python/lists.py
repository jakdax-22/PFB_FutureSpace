#Listas ordenadas, mutables, permiten elementos duplicados
myList = ["banana","cherry","apple"];
print (myList);

myList2 = [5, True, "apple","apple"];
print (myList2);

item = myList[-1];
print (item);

#bucle for

for i in myList:
    print(i);

#if 
if "banana" in myList:
    print ("yes");
else:
    print ("no");

#length
print (len(myList));

#append
myList.append("lemnon");
print (myList);

#insertar en lugar específico
myList.insert(1, "blueberry");
print(myList);

#pop
item = myList.pop();
print (item);
print (myList);

#remove
item = myList.remove("cherry");
print (myList);

#borrar todo
myList.clear();
print (myList);