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
    //Breakpoint para sacar los datos de una persona específica, por su id del Map
    @GetMapping(path = "/{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <PersonRest> getPerson(@PathVariable String personId){
        //Prueba para comprobar que funciona el breakpoint de coger un usuario, cuando haga el breakpoint de POST lo comentaré, porque ya no tendrá mucho sentido
        PersonRest returnValue = new PersonRest();
        returnValue.setPersonId("1");
        returnValue.setFirstName("Enrique");
        returnValue.setLastName("Iranzo");
        returnValue.setSecondLastName("Martínez");
        returnValue.setCompleteName();
        returnValue.setSex("Male");

        return new ResponseEntity<PersonRest>(returnValue,HttpStatus.OK);
        /*if (people.containsKey(personId)){
            return new ResponseEntity<PersonRest>(people.get(personId), HttpStatus.OK);
        }
        return new ResponseEntity<PersonRest>(people.get(personId),HttpStatus.NO_CONTENT);*/
    }
    //Breakpoint para el método POST, aquí añadiremos una persona cuando nos llegue su respectiva petición HTTP
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    //@RequestBody hace que cojamos el JSON que va en el cuerpo de la solicitud y lo transformemos en un objeto Java para poder manipularlo
    public void createPerson(@RequestBody PersonDetailsRequestModel personDetails){
        System.out.println("{");
        System.out.println("\t"+personDetails.getPersonId()+",");
        System.out.println("\t"+personDetails.getLastName()+",");
        System.out.println("\t"+personDetails.getSecondLastName()+",");
        System.out.println("\t"+personDetails.getCompleteName()+",");
        System.out.println("\t"+personDetails.getBirthDate()+",");
        System.out.println("\t"+personDetails.getSex());
        System.out.println("}");
    }
}
