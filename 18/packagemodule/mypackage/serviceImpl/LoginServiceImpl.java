package mypackage.serviceImpl;


import mypackage.models.*;
import mypackage.exceptions.*;
import mypackage.services.*;
import mypackage.utils.*;

import java.util.*;
import java.util.function.*;
import static mypackage.serviceImpl.TriesValidationServiceImpl.*;
import static mypackage.serviceImpl.UserSessionServiceImpl.*;

public class LoginServiceImpl implements LoginService{
    public boolean isAuthentication;
    private boolean isLoggedIn;
    private UserModel userModel;

    public LoginServiceImpl(UserModel userModel) {
        super();
        this.userModel = userModel;
        init();
    }

    public void init() {
    }

    @Override
    public void provideCredentials(String...args) {
        userModel.setUsername(args[IntUtilsMyPackage.USERNAME_INDEX.getIntValue()]);
        userModel.setPassword(args[IntUtilsMyPackage.PASSWORD_INDEX.getIntValue()]);
    }
    @Override
    public boolean authenticationResult() {
        isAuthentication = authenticateUser(k -> {
            if ((LOGIN_USERNAME.equalsIgnoreCase(k.getUsername())) &&
                    (LOGIN_PASSWORD.equals(k.getPassword()))) {
                return true;
            } else {
                return false;
            }
        });
        return isAuthentication;
    }
    @Override
    public boolean authenticateUser(Predicate<UserModel> loggingIn) {
        isLoggedIn = false;
        if (loggingIn.test(userModel)) {
            isLoggedIn = true;
        }
        return isLoggedIn;
    }
    @Override
    public String getLoggedInUser() {
        return LOGIN_USERNAME;
    }

    @Override
    public boolean isAuthentication() {
        return isAuthentication;
    }

    @Override
    public void setAuthentication(boolean authentication) {

    }
}
