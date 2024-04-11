package com.ejercicios.app.ws.ui.controller;
//Imports de la clase
import java.util.ArrayList;
import com.ejercicios.app.ws.model.response.PeopleListModel;
import com.ejercicios.app.ws.model.request.PersonDetailsRequestModel;
import com.ejercicios.app.ws.model.request.UpdatePersonDetailsRequestModel;
import com.ejercicios.app.ws.model.response.PersonRest;
import com.ejercicios.app.ws.userservice.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Marcamos la clase como controlador de tipo REST para que maneje solicitudes HTTP
@RestController
//Definimos la ruta de acceso, que será la que habrá que poner para hacer peticiones, en este caso people
@RequestMapping("people")
public class PersonController {
    //Utilizo autowired para inyección de dependencias y separar la lógica del controlador
    @Autowired
    PersonService personService;
    //Establecemos los breakpoints

    //Este es un añadido que quería hacer yo si me sobraba tiempo, para practicar
        /*@GetMapping(path="/{all}")
        public ResponseEntity <ArrayList<PersonRest>> getPeople(){
            if (peopleList.isEmpty()){
                return new ResponseEntity <ArrayList<PersonRest>> (people.getPeopleList(),HttpStatus.OK);
            }
            return new ResponseEntity<ArrayList<PersonRest>>(HttpStatus.NO_CONTENT);
        }*/
    //Breakpoint para sacar los datos de una persona, sin coger un identificador
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <PersonRest> getPerson(){
        return personService.getPerson();
    }
    //Breakpoint para sacar los valores de una persona proporcionando su Dni
    @GetMapping(path="/{personDni}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <PersonRest> getPersonByDni(@PathVariable String personDni){
        return personService.getPersonByDni(personDni);
    }
    //Breakpoint para el método POST, aquí añadiremos una persona cuando nos llegue su respectiva petición HTTP
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    //@RequestBody hace que cojamos el JSON que va en el cuerpo de la solicitud y lo transformemos en un objeto Java para poder manipularlo
    //Lo hago void porque no se pide que devuelva nada
    public ResponseEntity<PersonDetailsRequestModel> createPerson(@RequestBody PersonDetailsRequestModel personDetails){
        return personService.createPerson(personDetails);
    }
    @PutMapping(path="/{personDni}",consumes = MediaType.APPLICATION_JSON_VALUE)
    //Lo hago void porque no se pide que devuelva nada
    public ResponseEntity<UpdatePersonDetailsRequestModel> updatePerson (@RequestBody UpdatePersonDetailsRequestModel personDetails,@PathVariable String personDni){
        return personService.updatePerson(personDetails,personDni);
    }
}
