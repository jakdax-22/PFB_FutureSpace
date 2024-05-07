import json

person = {"name": "John", "age": 30, "city": "New York", "hasChildren": False, "titles": ["engineer", "programmer"]}

personJSON = json.dumps(person, indent=4,sort_keys=True);
print (personJSON);

#Guardar el JSON en un fichero
"""with open('person.json','w') as file:
    json.dump(person,file,indent=4);"""

#decodificar de JSON a dict
person = json.loads(personJSON);
print(person);

#Sacar de fichero y mostrar el dict
with open('person.json','r') as file:
    person = json.load(file);
    print(person);