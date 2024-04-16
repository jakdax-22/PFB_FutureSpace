package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio6 {
    public ArrayList<String> generateColors(){
        //Creo un array fijo con los posibles colores
        String [] colors = {"Azul","Rojo","Verde","Amarillo"};
        //Creo el arrayList antes de iterar el bucle
        ArrayList<String> result = new ArrayList<String>();
        //Relleno el arrayList
        for (int i = 0; i < colors.length;i++){
            result.add(i,colors[(int)(Math.random() * 4)]);
        }
        //Lo devuelvo
        return result;
    }
    public ArrayList<String> checkUserResponse (ArrayList<String> result, ArrayList <String>userResponse){
        //recorro ambos ArrayList a la vez y genero otro con los colores Blanco y Negro
        ArrayList<String>userResponseChecked = new ArrayList<String>();
        for (int i = 0; i < result.size(); i++){
            //Si contiene el mismo String en la misma posición introduce blanco
            if (result.get(i).equals(userResponse.get(i))){
                userResponseChecked.add(i,"Blanco");
            }
            //Si no, pero el ArrayList lo tiene en otra posición introduce negro
            else if (result.contains(userResponse.get(i))) {
                userResponseChecked.add(i,"Negro");
            }
            else{
                userResponseChecked.add(i,"Vacío");
            }
        }
        return userResponseChecked;
    }
    public boolean allWhite (ArrayList<String>userResponseChecked){
        //Debugging
        //System.out.println(userResponseChecked.size());
        for (String element:userResponseChecked){
            if (!element.equals("Blanco"))
                return false;
        }
        return true;
    }
    public void colorGame (){
        ArrayList<String> result = generateColors();
        //Para que entre en el bucle, doy valor falso al booleano
        boolean isCorrect = false;
        ArrayList<String> userResponseChecked = new ArrayList<String>();
        //Creo el scanner, para almacenar los datos introducidos por teclado
        Scanner scanner = new Scanner(System.in);
        // Creo el ArrayList que voy a rellenar con los valores que vaya introduciendo el usuario
        ArrayList<String> userResponse = new ArrayList<String>();
        //Inicializo las 4 posiciones que voy a usar con un add, luego cambiaré su contenido con set dentro del bucle
        for (int i = 0; i < 4; i++){
            userResponse.add (i,"");
        }
        //Comienza el juego
        for (int i = 0; i < 10 && !isCorrect; i++){
            //Bucle anidado para preguntar los 4 colores
            for (int j = 0; j < 4; j++){
                System.out.println("Dime el color "+(j+1)+":");
                String scannerResponse = scanner.nextLine();
                userResponse.set(j,scannerResponse);
            }
            //Almaceno resultados
            userResponseChecked = checkUserResponse(result,userResponse);
            //Los muestro
            System.out.println(userResponseChecked.toString());
            //Veo si se han acertado todas, esto me permitiría romper el bucle
            if (allWhite(userResponseChecked)){
                isCorrect=true;
                System.out.println("Felicidades!!, has ganado en: "+(i+1)+" intentos");
            }
        }
        //Si se ha salido del bucle y isCorrect sigue en false es que hemos perdido
        if (!isCorrect)
            System.out.println("Has perdido :(");
    }
}
