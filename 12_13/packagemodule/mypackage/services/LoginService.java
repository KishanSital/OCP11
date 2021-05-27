package mypackage.services;

import mypackage.models.*;
import java.util.function.*;

public interface LoginService {
    void init();
    void startLoginService();
    void authentication();
    void provideCredentials();
    boolean authenticationResult();
    boolean authenticateUser(Predicate<UserModel> loggingIn);
    String getLoggedInUser();
}
