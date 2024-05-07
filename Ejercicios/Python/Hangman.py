#Importaciones necesarias para la lógica
import csv;
import random;
import os;
from datetime import datetime;

#Clase principal del programa
class Hangman:
    #Constructor
    def __init__(self):
        #Array vacío de palabras
        self.words = [];
        #Cargar el fichero csv
        self.load("./csv/words.csv");
        #Nombre del usuario
        self.username="";
        #Rondas
        self.rounds = [];
        #Puntuación
        self.score = 0;

    #Método de carga
    def load(self,filename):
        try:
            with open (filename,'r', encoding='utf-8') as file:
                #función para leer un archivo como csv
                reader = csv.reader(file);
                #iteración
                for row in reader:
                    word = row[0];
                    #Si la palabra tiene más de 5 caracteres la mete en el array
                    if len(word) >= 5:
                        self.words.append(word.upper());
            #Se acaba la apertura de fichero
            #Si la longitud del array es mayor o igual a 30 se muestra mensaje, si no se muestra otro y se sale
            if self.get_number_of_words() >= 30:
                print ("Palabras listas, ¡adelante!");
            else:
                print ("Vaya, parece que no encontramos todas las palabras necesarias, no podemos dar comienzo al juego");
                exit();
        #Manejar la excepción de que no se encuentre el fichero
        except FileNotFoundError:
            print("El archivo de palabras no se encontró.");
            exit();

    #Función para sacar el número de palabras del array
    def get_number_of_words(self):
        return len(self.words);

    #Función encargada del juego
    def start(self):
        self.username = input("Ingresa tu nombre de usuario: ");
        #Recoger por pantalla el nombre del usuario y mostrarlo
        print(f"¡Bienvenido, {self.username}!");
        #Bucle por cada una de las 3 rondas
        for i in range(3):
            #Elijo una palabra del array
            word = random.choice(self.words);
            #Creo una ronda para cada iteración
            self.round(word);

    #Función para crear una ronda
    

#Inicialización del juego
hangman_game = Hangman();
