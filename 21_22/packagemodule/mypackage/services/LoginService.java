package mypackage.services;

import mypackage.annotations.*;
import mypackage.models.*;
import java.util.function.*;
@Service
public interface LoginService {
    void init();
    void provideCredentials(String [] args);
    boolean authenticationResult();
    boolean authenticateUser(Predicate<UserModel> loggingIn);
    String getLoggedInUser();
    boolean isAuthentication();
    void setAuthentication(boolean authentication);
}
