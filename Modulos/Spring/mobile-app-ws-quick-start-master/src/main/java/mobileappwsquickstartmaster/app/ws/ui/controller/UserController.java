package mobileappwsquickstartmaster.app.ws.ui.controller;

import mobileappwsquickstartmaster.app.ws.model.request.UpdateUserDetailsRequestModel;
import mobileappwsquickstartmaster.app.ws.model.request.UserDetailsRequestModel;
import mobileappwsquickstartmaster.app.ws.model.response.UserRest;
import mobileappwsquickstartmaster.app.ws.userservice.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("users") //localhost://8080//
public class UserController {
    Map <String, UserRest> users;

    @Autowired
    UserService userService;

    @GetMapping
    public String getUsers(@RequestParam(value="page", defaultValue = "1") int page,
                           @RequestParam(value="limit",defaultValue = "50") int limit,
                           @RequestParam(value="sort",defaultValue = "desc" , required = false) String sort){
        return "get user was called! with page = " +page+ " limit = " +limit+ " and sort = "+sort;
    }
    @GetMapping(path="/{userId}", produces = { MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity <UserRest> getUser(@PathVariable String userId){
        if (users.containsKey(userId)){
            return new ResponseEntity<UserRest>(users.get(userId), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
        }
    }
    @PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity <UserRest> createUser(@Validated @RequestBody UserDetailsRequestModel userDetails){

        UserRest returnValue = userService.createUser(userDetails);
        return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
    }
    @PutMapping(path="/{userId}",consumes = { MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE })
    public UserRest updateUser(@PathVariable String userId, @RequestBody UpdateUserDetailsRequestModel userDetails){
        UserRest storedUserDetails = users.get(userId);
        storedUserDetails.setFirstName(userDetails.getFirstName());
        storedUserDetails.setLastName(userDetails.getLastName());

        users.put(userId, storedUserDetails);
        return storedUserDetails;
    }
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id){
        users.remove(id);
        return ResponseEntity.noContent().build();
    }
}
