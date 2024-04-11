package com.ejercicios.app.ws.ui.controller;
//Imports de la clase
import com.ejercicios.app.ws.model.request.PersonDetailsRequestModel;
import com.ejercicios.app.ws.model.response.PersonRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

//Marcamos la clase como controlador de tipo REST para que maneje solicitudes HTTP
@RestController
//Definimos la ruta de acceso, que será la que habrá que poner para hacer peticiones, en este caso people
@RequestMapping("people")
public class PersonController {
    //Map es una colección que tiene todas las personas almacenadas
    Map<String, PersonRest> people;
    //Establecemos los breakpoints

    //Este es un añadido que quería hacer yo si me sobraba tiempo, para practicar
    /*
        @GetMapping
        public ResponseEntity <PersonRest> getPeople(){

        }
     */
    //Breakpoint para sacar los datos de una persona, sin coger un identificador
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <PersonRest> getPerson(){
        //Prueba para comprobar que funciona el breakpoint de coger un usuario
        PersonRest returnValue = new PersonRest();
        returnValue.setFirstName("Enrique");
        returnValue.setLastName("Iranzo");
        returnValue.setSecondLastName("Martínez");
        returnValue.setCompleteName();
        returnValue.setSex("Male");

        return new ResponseEntity<PersonRest>(returnValue,HttpStatus.OK);
    }
    //Breakpoint para el método POST, aquí añadiremos una persona cuando nos llegue su respectiva petición HTTP
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    //@RequestBody hace que cojamos el JSON que va en el cuerpo de la solicitud y lo transformemos en un objeto Java para poder manipularlo
    public void createPerson(@RequestBody PersonDetailsRequestModel personDetails){
        System.out.println("{");
        System.out.println("\t"+personDetails.getLastName()+",");
        System.out.println("\t"+personDetails.getSecondLastName()+",");
        System.out.println("\t"+personDetails.getCompleteName()+",");
        System.out.println("\t"+personDetails.getBirthDate()+",");
        System.out.println("\t"+personDetails.getSex());
        System.out.println("}");
    }
}
