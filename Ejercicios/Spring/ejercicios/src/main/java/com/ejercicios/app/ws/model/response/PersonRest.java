package com.ejercicios.app.ws.model.response;

import java.util.Date;

//Clase de Persona, este tipo de dato será el devuelto cuando se haga una petición GET
public class PersonRest {
    //Atributos
    private String firstName;
    private String lastName;
    private String secondLastName;
    String completeName;
    private Date birthDate;
    private String sex;
    private String Dni;

    public PersonRest(String firstName,String lastName,String secondLastName, Date birthDate, String sex, String Dni){
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.birthDate = birthDate;
        this.sex = sex;
        this.Dni = Dni;
    }
    public PersonRest(){

    }

    //Getters y Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    // Aquí he puesto que el get sea la concatenación de el nombre y apellidos
    public String getCompleteName() {
        return firstName + " " + lastName + " " + secondLastName;
    }
    public void setCompleteName() {
        this.completeName = getCompleteName();
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String Dni) {
        this.Dni = Dni;
    }


}
