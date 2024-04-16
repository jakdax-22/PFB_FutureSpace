package org.example;

import java.util.ArrayList;

public class Ejercicio5 {
    //funciones autodescriptivas
    public ArrayList <Integer> generateTicketNumber(){
        //Uso ArrayList por los métodos propios que tienen como contains, en este ejercicio me va a hacer falta
        ArrayList<Integer> ticketNumber = new ArrayList<Integer>();
        //Al ser un objeto no simple, al llamar a una función pasamos el argumento como referencia y el original muta
        fillArrayList(ticketNumber,5,50);
        return ticketNumber;
    }
    public ArrayList <Integer> generateSerialNumber(){
        //Uso ArrayList por los métodos propios que tienen como contains, en este ejercicio me va a hacer falta
        ArrayList<Integer> serialNumber = new ArrayList<Integer>();
        //Al ser un objeto no simple, al llamar a una función pasamos el argumento como referencia y el original muta
        fillArrayList(serialNumber,2,12);
        return serialNumber;
    }

    //Esta función la puedo reutilizar luego tanto en la de generar el ticket como el número de serie
    public ArrayList <Integer> fillArrayList(ArrayList<Integer>ArrayListObject,int iterator,int maxRandom){
        //No declarar variables dentro de un bucle
        int randomNumber;
        for (int i = 0; i < iterator; i++){
            randomNumber = (int)(Math.random() * maxRandom) + 1;
            //Un while anidado para que genere hasta que no se cumpla la condición
            while (ArrayListObject.contains(randomNumber)){
                randomNumber = (int)(Math.random() * maxRandom) + 1;
            }
            //Añado el número no repetido
            ArrayListObject.add(randomNumber);
        }
        //Devuelvo el ArrayList
        return ArrayListObject;
    }
}
