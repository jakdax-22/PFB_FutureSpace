import random,secrets
#Cosas seguras mejor con secrets
a = secrets.randbelow(10);
print (a);

mylist = list ("ABCDEFGH");
a = secrets.randbits(4);
#1111
print (a);
# De 0 a 1 a = random.random();

# int de 1 a 10, 10 no incluido a = random.randrange(1,10)
"""a = random.normalvariate(0,1);
print (a);"""

mylist = list ("ABCDEFGH");
#Uno en específico
a = random.choice(mylist);
#Un rango específico x veces
a = random.choices(mylist,k=3);
print (a);


random.shuffle(mylist);
print(mylist)

#Guarda un random y lo repite siempre que lo uses, mejor no usarla para info secreta
random.seed(1);

print (random.random());
print (random.randint(1,10));
