package com.ejercicios.app.ws.userservice;

import com.ejercicios.app.ws.model.request.PersonDetailsRequestModel;
import com.ejercicios.app.ws.model.request.UpdatePersonDetailsRequestModel;
import com.ejercicios.app.ws.model.response.PersonRest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;


public interface PersonService {
    ResponseEntity <PersonDetailsRequestModel> createPerson (PersonDetailsRequestModel personDetails);
    ResponseEntity <PersonRest> getPerson ();
    ResponseEntity <PersonRest> getPersonByDni( String personDni);
    ResponseEntity<UpdatePersonDetailsRequestModel> updatePerson ( UpdatePersonDetailsRequestModel personDetails, String personDni);
    ResponseEntity <ArrayList<PersonRest>> getPeople();
}
