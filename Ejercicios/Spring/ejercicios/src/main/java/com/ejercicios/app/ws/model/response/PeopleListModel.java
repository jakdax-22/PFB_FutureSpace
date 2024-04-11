package com.ejercicios.app.ws.model.response;

import com.ejercicios.app.ws.model.response.PersonRest;

import java.util.ArrayList;

public class PeopleListModel {
    //Solo tiene un atributo que es el List
    private final ArrayList<PersonRest> peopleList = new ArrayList<PersonRest>();

    //Constructor
    public PeopleListModel(){
        //Voy a crear y a meter todas las personas directamente desde el constructor
        PersonRest person = new PersonRest("Enrique","Iranzo","Martínez",null,"male","77777777F");
        this.peopleList.add(person);
        person = new PersonRest("Edgar","Mila","Molero",null,"male","77777778F");
        this.peopleList.add(person);
        person = new PersonRest("Alejandro","Polo","Ovejero",null,"male","77777779F");
        this.peopleList.add(person);
        person = new PersonRest("Yaiza","García","Martínez",null,"female","77777709F");
        this.peopleList.add(person);
        person = new PersonRest("Rosa","García","Martínez",null,"female","77777719F");
        this.peopleList.add(person);
        person = new PersonRest("Jennifer","García","Navarro",null,"female","77777729F");
        this.peopleList.add(person);
        person = new PersonRest("Érika","González","Rubio",null,"female","77777739F");
        this.peopleList.add(person);
        person = new PersonRest("Angel","González","Rubio",null,"male","77777749F");
        this.peopleList.add(person);
        person = new PersonRest("Enrique","Arona","Madrid",null,"male","77777759F");
        this.peopleList.add(person);
        person = new PersonRest("Jaime","Martínez","García",null,"male","77777769F");
        this.peopleList.add(person);
        person = new PersonRest("Antonio","Pérez","Gómez",null,"male","77777779F");
        this.peopleList.add(person);
    }
    public ArrayList<PersonRest> getPeopleList() {
        return peopleList;
    }
}
