#manejadores de iteradores
from itertools import product,permutations,combinations, combinations_with_replacement,accumulate,groupby,count,cycle,repeat
import operator

#product
a = [1,2];
b = [3];
prod = product(a,b, repeat=2);
print (list(prod));

#permutations, todos los posibles órdenes
a = [1,2,3];
perm = permutations(a,2);

print (list(perm));

#combinations, hace todas las combinaciones posibles con una longitud específica
a = [1,2,3,4];
comb = combinations(a,2);

print (list(comb));

comb_wr = combinations_with_replacement(a,2);
print (list(comb_wr));

#accumulate
a = [1,2,5,3,4];
acc = accumulate(a, func=max);
print (a);
print (list(acc));

#groupby agrupa según una condición en una función
def smaller_than_three(x):
    return x < 3;

a = [1,2,3,4];
group_obj = groupby(a, key=lambda x: x < 3);

for key,value in group_obj:
    print (key,list(value));

#count
for i in count (10):
    print (i)
    if i == 15:
        break;