package mypackage.serviceImpl;


import mypackage.models.*;
import mypackage.services.*;

public class UserSessionServiceImpl implements UserSessionService {
    public static String LOGIN_USERNAME;
    public static String LOGIN_PASSWORD;
    public static Long USER_ID;

    public UserSessionServiceImpl(UserModel expectedUser) {
        this.USER_ID = expectedUser.getUserId();
        this.LOGIN_USERNAME = expectedUser.getUsername();
        this.LOGIN_PASSWORD = expectedUser.getPassword();
    }


}
