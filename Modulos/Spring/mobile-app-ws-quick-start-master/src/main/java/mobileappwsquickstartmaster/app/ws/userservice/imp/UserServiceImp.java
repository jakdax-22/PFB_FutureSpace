package mobileappwsquickstartmaster.app.ws.userservice.imp;

import mobileappwsquickstartmaster.app.ws.model.request.UserDetailsRequestModel;
import mobileappwsquickstartmaster.app.ws.model.response.UserRest;
import mobileappwsquickstartmaster.app.ws.shared.Utils;
import mobileappwsquickstartmaster.app.ws.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService {
    Map<String, UserRest> users;
    Utils utils;
    public UserServiceImp(){}

    @Autowired
    public UserServiceImp(Utils utils){
        this.utils = utils;
    }
    @Override
    public UserRest createUser(UserDetailsRequestModel userDetails){
        UserRest returnValue = new UserRest();
        returnValue.setEmail(userDetails.getEmail());
        returnValue.setFirstName(userDetails.getFirstName());
        returnValue.setLastName(userDetails.getLastName());

        String userId = utils.generateUserId();
        returnValue.setUserId(userId);
        if (users == null) users = new HashMap<>();
        users.put(userId, returnValue);

        return returnValue;

    }
}
