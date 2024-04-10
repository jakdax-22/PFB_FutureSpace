package mobileappwsquickstartmaster.app.ws.userservice;
import mobileappwsquickstartmaster.app.ws.model.request.UserDetailsRequestModel;
import mobileappwsquickstartmaster.app.ws.model.response.UserRest;

public interface UserService {
    UserRest createUser(UserDetailsRequestModel userDetails);
}
