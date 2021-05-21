package chapter10_11.util;

import chapter10_11.entities.*;

public class UserEntityUtil {

    public static String LOGIN_USERNAME;
    public static String LOGIN_PASSWORD;
    public static Long USER_ID;

    public UserEntityUtil(UserEntity expectedUser) {
        this.USER_ID = expectedUser.getUserId();
        this.LOGIN_USERNAME = expectedUser.getUsername();
        this.LOGIN_PASSWORD = expectedUser.getPassword();
    }


}
