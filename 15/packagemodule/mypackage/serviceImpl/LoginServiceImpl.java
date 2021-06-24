package mypackage.serviceImpl;


import mypackage.models.*;
import mypackage.exceptions.*;
import mypackage.services.*;
import mypackage.utils.*;

import java.util.*;
import java.util.function.*;
import static mypackage.serviceImpl.TriesValidationServiceImpl.*;
import static mypackage.serviceImpl.UserSessionServiceImpl.*;

public class LoginServiceImpl extends Object implements LoginService,MenuService{
    boolean isAuthentication;
    private boolean isLoggedIn;
    private UserModel userModel;
    private PrintMessageService println = System.out::println; // instance method on parameter reference
    private Scanner scanner;

    public LoginServiceImpl(UserModel userModel) {
        super();
        this.userModel = userModel;
        init();
    }

    public void init() {
        scanner = new Scanner(System.in);
    }
    @Override
    public void startLoginService() {
        resetTriesService();
        do {
            provideCredentials();
            authentication();
        } while (!isAuthentication);
        println.print(StringUtilsMyPackage.LOGIN_SUCCESS_MESSAGE.getStringValue());
    }

    @Override
    public void authentication() {
        try {
            isAuthentication = authenticationResult();
            if (!isAuthentication) {
                throw new LoginException(StringUtilsMyPackage.INVALID_CREDENTIALS_MESSAGE.getStringValue());
            }
        } catch (LoginException e) {
            println.print(e.getMessage());
        } finally {
            if (!isAuthentication) {
                triesValidation();
            }
        }
    }
    @Override
    public void provideCredentials() {
        println.print(StringUtilsMyPackage.PROVIDE_USERNAME_MESSAGE.getStringValue());
        userModel.setUsername(scanner.next());

        println.print(StringUtilsMyPackage.PROVIDE_PASSWORD_MESSAGE.getStringValue());
        userModel.setPassword(scanner.next());
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
}
