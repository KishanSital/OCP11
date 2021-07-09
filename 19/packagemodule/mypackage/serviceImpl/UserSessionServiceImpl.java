package mypackage.serviceImpl;


import mypackage.models.*;
import mypackage.services.*;

public class UserSessionServiceImpl implements UserSessionService {
    public static String LOGIN_USERNAME;
    public static char [] LOGIN_PASSWORD;
    public static Long USER_ID;

    public UserSessionServiceImpl(UserModel expectedUser) {
        USER_ID = expectedUser.getUserId();
        LOGIN_USERNAME = expectedUser.getUsername();
        LOGIN_PASSWORD = expectedUser.getPassword();
    }


}
