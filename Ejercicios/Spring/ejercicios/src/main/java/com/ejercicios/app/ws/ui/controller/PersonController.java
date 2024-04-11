package com.ejercicios.app.ws.ui.controller;
//Imports de la clase
import java.util.ArrayList;
import com.ejercicios.app.ws.model.response.PeopleListModel;
import com.ejercicios.app.ws.model.request.PersonDetailsRequestModel;
import com.ejercicios.app.ws.model.request.UpdatePersonDetailsRequestModel;
import com.ejercicios.app.ws.model.response.PersonRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Marcamos la clase como controlador de tipo REST para que maneje solicitudes HTTP
@RestController
//Definimos la ruta de acceso, que será la que habrá que poner para hacer peticiones, en este caso people
@RequestMapping("people")
public class PersonController {
    //Objeto con la lista con las Personas metidas en el ejercicio 3
    PeopleListModel people = new PeopleListModel();
    ArrayList<PersonRest> peopleList = people.getPeopleList();

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
        //Prueba para comprobar que funciona el breakpoint de coger una persona
        PersonRest returnValue = new PersonRest();
        returnValue.setFirstName("Enrique");
        returnValue.setLastName("Iranzo");
        returnValue.setSecondLastName("Martínez");
        returnValue.setCompleteName();
        returnValue.setSex("Male");

        return new ResponseEntity<PersonRest>(returnValue,HttpStatus.OK);
    }
    //Breakpoint para sacar los valores de una persona proporcionando su Dni
    @GetMapping(path="/{personDni}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <PersonRest> getPersonByDni(@PathVariable String personDni){
        //Itero y en el momento que coincida con el Dni proporcionado devuelvo el objeto, si no, devuelvo una respuesta de que no se ha encontrado
        for (PersonRest person : peopleList){
            if (personDni.equals(person.getDni())){
                //Si coincide devuelvo el objeto convertido a JSON
                return new ResponseEntity<PersonRest>(person,HttpStatus.OK);
            }
        }
        // Si no devuelvo un no_content
        return new ResponseEntity<PersonRest>(HttpStatus.NO_CONTENT);
    }
    //Breakpoint para el método POST, aquí añadiremos una persona cuando nos llegue su respectiva petición HTTP
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    //@RequestBody hace que cojamos el JSON que va en el cuerpo de la solicitud y lo transformemos en un objeto Java para poder manipularlo
    //Lo hago void porque no se pide que devuelva nada
    public void createPerson(@RequestBody PersonDetailsRequestModel personDetails){
        System.out.println("{");
        System.out.println("\t"+personDetails.getLastName()+",");
        System.out.println("\t"+personDetails.getSecondLastName()+",");
        System.out.println("\t"+personDetails.getCompleteName()+",");
        System.out.println("\t"+personDetails.getBirthDate()+",");
        System.out.println("\t"+personDetails.getSex());
        System.out.println("}");
    }
    @PutMapping(path="/{personDni}",consumes = MediaType.APPLICATION_JSON_VALUE)
    //Lo hago void porque no se pide que devuelva nada
    public void updatePerson (@RequestBody UpdatePersonDetailsRequestModel personDetails,@PathVariable String personDni){
        //Prueba para ver si se han crado bien las personas de dentro
        /*for(UpdatePersonDetailsRequestModel person : peopleList){
            System.out.println(person.getDni());
        }*/
        //Iteramos
        for (PersonRest person : peopleList){
            //Si coincide con el Dni cambiamos los atributos por los pasados en el cuerpo de la petición
            if (person.getDni().equals(personDni)){
                person.setBirthDate(personDetails.getBirthDate());
                person.setSex(personDetails.getSex());
                person.setFirstName(personDetails.getFirstName());
                person.setLastName(personDetails.getLastName());
                person.setSecondLastName(personDetails.getSecondLastName());
                person.setCompleteName();

            }
        }
    }
}
