from collections import Counter,namedtuple,OrderedDict,defaultdict,deque

a = "aaaaaabbbbbccc";
myCounter = Counter(a);
print (myCounter.values());
#valor más común
print(myCounter.most_common(2)[0][0]);

print(list(myCounter.elements()));

#namedtuple
Point = namedtuple('Point','x,y');
pt = Point(1, 4);
print (pt);

#OrderedDict, ya no se usa para casi nada
ordered_dict = OrderedDict();
ordered_dict['a'] = 1;
ordered_dict['b'] = 2;
ordered_dict['c'] = 3;
ordered_dict['d'] = 4;

print (ordered_dict);

#defaultdict, si no encuentra coincidencia devuelve el valor por defecto de ese tipo
d = defaultdict(int);
d['a'] = 1;
d['b'] = 2;

print (d['a']);

#deque
d = deque();

d.append(1);
d.append(2);
d.appendleft(3);

print (d);

d.popleft();
print(d);

d.extendleft([4,5,6]);
print(d);

d.rotate(-1);
print(d);
