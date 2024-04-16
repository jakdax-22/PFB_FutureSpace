package org.example.ejercicio2;

public class Human {
    /*Viendo como está planteado el ejercicio y que Human de base tiene 7 atributos referidos a él pero sin embargo, 5
    más que referirse a él se refieren a la dirección incluyendo el método, he pensado en separarlo en dos clases e incluir
    un objeto Address en Human como atributo
     */
    //Atributos
    private String name;
    private String age;
    private Address address;

    //Constructor
    //Pongamos que en el main se crea una address primero y luego se le pasa como argumento al constructor para instanciar un objeto human
    public Human (String name,String age,Address address){
        this.name = name;
        this.age = age;
        this.address = address;
    }

}
