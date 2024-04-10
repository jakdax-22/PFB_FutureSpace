package com.ejercicios.app.ws.model.request;

import java.util.Date;

public class PersonDetailsRequestModel {
    //Atributos
    private String firstName;
    private String lastName;
    private String secondLastName;
    String completeName;
    private Date birthDate;
    private String sex;
    private String personId;

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
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

}

