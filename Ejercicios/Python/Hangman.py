#Importaciones necesarias para la lógica
import csv;
import random;
from datetime import datetime;
import uuid;
import pandas as pd;
import os;

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
        #Atributo del ejercicio 3, es un array que va a guardar la info de cada ronda, al final he cambiado alguna cosa y ya no lo uso
        #self.rounds_info = [];
        #Atributo del ejercicio 3, el id del juego
        self.game_id = str(uuid.uuid4());
        #Hora de inicio
        self.start_date = datetime.now();

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

        #Al final de las rondas mostrar la puntuación
        print("Tu puntuación es de: "+str(self.score));
        self.save_game_csv();

    #Función para crear una ronda
    def round(self,word):
        #Id de la ronda
        round_id = str(uuid.uuid4());
        #Array de letras usadas, lo inicializo a vacío
        guessed = [];
        #intentos
        attempts = 0;
        #Número máximo de intentos
        max = 5;
        #Bucle para hacer todos los intentos
        while attempts < max:
            #Función que dibuja el ahorcado
            self.draw_hangman(attempts);
            #Función que dibuja las letras que quedan
            self.draw_result(word,guessed);
            #Dibujar las letras que han sido usadas 
            if len(guessed) > 0 :
                print ("Letras usadas: ");
                for letter in guessed:
                    print(letter + ", ");
            #Pedir una letra al usuario
            user_input = input("Introduce una letra: ").upper();
            #Si introduce más de una letra, se penaliza
            if len(user_input) > 1:
                print("Longitud no válida, se va a aplicar una pequeña penalización :(");
                attempts += 1;
                #Si se ha alcanzado el número máximo establezco gameover en true y se acaba el juego
                if attempts >= max:
                    self.draw_hangman(attempts);
                    print ("La palabra era: "+word)
                    print("Número máximo de intentos alcanzado , ronda perdida");
                    self.save_round_csv(word,round_id,attempts,False);
            #Si la letra ya la había usado antes, se penaliza
            elif user_input in guessed:
                print("Ya has probado con esta letra, se va a aplicar una penalización :(");
                attempts += 1;
                #Si se ha alcanzado el número máximo establezco gameover en true y se acaba el juego
                if attempts >= max:
                    self.draw_hangman(attempts);
                    print ("La palabra era: "+word)
                    print("Número máximo de intentos alcanzado , ronda perdida");
                    self.save_round_csv(word,round_id,attempts,False);
            #Si el usuario ha acertado compruebo si ha sido la última letra a acertar
            elif user_input in word:
                #Meto la letra en en array de letras usadas
                guessed.append(user_input);
                print("¡Has acertado!");
                if self.word_guessed(word,guessed):
                    print("¡Has adivinado la palabra!, un punto para ti");
                    #Subo la puntuación
                    self.score += 1;
                    #Introduzco información en el csv
                    self.save_round_csv(word,round_id,attempts,True);
                    #Rompo el condicional
                    break;

            #Si no es que se ha fallado
            else:
                print("Has fallado :(");
                guessed.append(user_input);
                #Subo el número de intentos
                attempts += 1;
                #Si se ha alcanzado el número máximo establezco gameover en true y se acaba el juego
                if attempts >= max:
                    self.draw_hangman(attempts);
                    print ("La palabra era: "+word)
                    print("Número máximo de intentos alcanzado , ronda perdida");
                    self.save_round_csv(word,round_id,attempts,False);
    



    def draw_hangman(self,attempts):
        #Array con las formas, según el número de intentos imprimirá una u otra
        stages = [
            """
                --------
                |      |
                |
                |
                |
                |
                -
            """,
            """
                --------
                |      |
                |      O
                |
                |
                |
                -
            """,
            """
                --------
                |      |
                |      O
                |      |
                |      |
                |
                -
            """,
            """
                --------
                |      |
                |      O
                |     \|/
                |      |
                |
                -
            """,
            """
                --------
                |      |
                |      O
                |     \|/
                |      |
                |     /
                -
            """,
            """
                --------
                |      |
                |      O
                |     \|/
                |      |
                |     / \\
                -
            """,
        ];
        print (stages[attempts]);

    #Función encargada de mostrar las letras que se han introducido correctamente y las _
    def draw_result(self,word,guess):
        display = "";
        for letter in word:
            if letter in guess:
                display += letter;
            else:
                display += "_";
        print (display);

    #Función encargada de saber si se ha acertado la palabra del todo
    def word_guessed(self,word,guessed):
        for letter in word:
            if letter not in guessed:
                return False;
        
        return True;

    #Función encargada de guardar los datos del juego en un CSV
    def save_game_csv(self):
        end_date = datetime.now();
        game_frame = pd.DataFrame({
            "game_id":[self.game_id],
            "username":[self.username],
            "start_date":[self.start_date],
            "end_date":[end_date],
            "score":[self.score]
        });

        file_name = "./csv/games.csv";

        #Ver si el archivo está vacío, si lo está escribo el encabezado, si no, escribo el contenido a secas
        if os.path.exists(file_name):
            if os.path.getsize(file_name) == 0:
                game_frame.to_csv(file_name,header=True,index=False);
            else:
                game_frame.to_csv(file_name, header=False, index=False,mode="a");
        else:
            game_frame.to_csv(file_name,header=True,index=False);
    #Función encargada de guardar los datos de la ronda en un CSV
    def save_round_csv(self,word,round_id,attempts,victory):
        game_frame = pd.DataFrame({
            "game_id":[self.game_id],
            "word":[word],
            "username":[self.username],
            "round_id":[round_id],
            "user_try":[attempts],
            "victory":[victory]
        });

        file_name = "./csv/rounds_in_games.csv";

        #Ver si el archivo está vacío, si lo está escribo el encabezado, si no, escribo el contenido a secas
        if os.path.exists(file_name):
            if os.path.getsize(file_name) == 0:
                game_frame.to_csv(file_name,header=True,index=False);
            else:
                game_frame.to_csv(file_name, header=False, index=False,mode="a");
        else:
            game_frame.to_csv(file_name,header=True,index=False);
        

    

#Inicialización del juego
hangman_game = Hangman();
hangman_game.start();
