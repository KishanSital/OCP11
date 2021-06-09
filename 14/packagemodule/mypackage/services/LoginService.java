package mypackage.services;

import mypackage.annotations.*;
import mypackage.models.*;
import java.util.function.*;
@Service
public interface LoginService {
    void init();
    void startLoginService();
    void authentication();
    void provideCredentials();
    boolean authenticationResult();
    boolean authenticateUser(Predicate<UserModel> loggingIn);
    String getLoggedInUser();
}
