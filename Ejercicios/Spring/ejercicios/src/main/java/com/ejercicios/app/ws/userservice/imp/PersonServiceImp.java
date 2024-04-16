package com.ejercicios.app.ws.userservice.imp;

import com.ejercicios.app.ws.model.request.PersonDetailsRequestModel;
import com.ejercicios.app.ws.model.request.UpdatePersonDetailsRequestModel;
import com.ejercicios.app.ws.model.response.PeopleListModel;
import com.ejercicios.app.ws.model.response.PersonRest;
import com.ejercicios.app.ws.userservice.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


//Para usar Autowired tengo que decir que esta clase es un servicio
@Service
public class PersonServiceImp implements PersonService {
    //Objeto con la lista con las Personas metidas en el ejercicio 3
    PeopleListModel people = new PeopleListModel();
    @Override
    public ResponseEntity<PersonDetailsRequestModel> createPerson(PersonDetailsRequestModel personDetails){
        System.out.println("{");
        System.out.println("\t"+personDetails.getLastName()+",");
        System.out.println("\t"+personDetails.getSecondLastName()+",");
        System.out.println("\t"+personDetails.getCompleteName()+",");
        System.out.println("\t"+personDetails.getBirthDate()+",");
        System.out.println("\t"+personDetails.getSex());
        System.out.println("}");

        return new ResponseEntity<PersonDetailsRequestModel> (HttpStatus.OK);
    }
    @Override
    public ResponseEntity<PersonRest> getPerson(){
        //Prueba para comprobar que funciona el breakpoint de coger una persona
        PersonRest returnValue = new PersonRest();
        returnValue.setFirstName("Enrique");
        returnValue.setLastName("Iranzo");
        returnValue.setSecondLastName("Martínez");
        returnValue.setCompleteName();
        returnValue.setSex("Male");

        return new ResponseEntity<PersonRest>(returnValue,HttpStatus.OK);
    }
    @Override
    public ResponseEntity <PersonRest> getPersonByDni(String personDni){
        //Itero y en el momento que coincida con el Dni proporcionado devuelvo el objeto, si no, devuelvo una respuesta de que no se ha encontrado
        for (PersonRest person : people.getPeopleList()){
            if (personDni.equals(person.getDni())){
                //Si coincide devuelvo el objeto convertido a JSON
                return new ResponseEntity<PersonRest>(person,HttpStatus.OK);
            }
        }
        // Si no devuelvo un no_content
        return new ResponseEntity<PersonRest>(HttpStatus.NO_CONTENT);
    }
    @Override
    public ResponseEntity<UpdatePersonDetailsRequestModel> updatePerson (UpdatePersonDetailsRequestModel personDetails,String personDni){
        //Prueba para ver si se han crado bien las personas de dentro
        /*for(UpdatePersonDetailsRequestModel person : peopleList){
            System.out.println(person.getDni());
        }*/
        //Iteramos
        for (PersonRest person : people.getPeopleList()){
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
        return new ResponseEntity<UpdatePersonDetailsRequestModel>(HttpStatus.OK);
    }
    public ResponseEntity <ArrayList<PersonRest>> getPeople(){
        if (people.getPeopleList().isEmpty()){
            System.out.println("Entra");
            return new ResponseEntity<ArrayList<PersonRest>>(HttpStatus.NO_CONTENT);
        }
        System.out.println("No entra al if");
        return new ResponseEntity <ArrayList<PersonRest>> (people.getPeopleList(),HttpStatus.OK);
    }

    }
