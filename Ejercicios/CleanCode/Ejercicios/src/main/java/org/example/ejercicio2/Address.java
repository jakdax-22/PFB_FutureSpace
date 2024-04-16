package org.example.ejercicio2;

public class Address {
    //Atributos
    private String country;
    private String city;
    private String street;
    private String house;
    private String quarter;

    //Constructor
    public Address (String country, String city, String street, String house, String quarter){
        this.country = country;
        this.city = city;
        this.street = street;
        this.house = house;
        this.quarter = quarter;
    }
    //Método
    public String getFullAddress(){
        //Yo aquí no veo la necesidad del uso de StringBuilder, así que lo voy a hacer con un String normal
        return country+", "+city+", "+street+", "+house+", "+quarter;
    }
}
