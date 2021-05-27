package mypackage.application;

import mypackage.models.*;
import mypackage.serviceImpl.LoginServiceImpl;
import mypackage.serviceImpl.UserSessionServiceImpl;

public class MyPackageApplication {
    public static void startLoginService(UserModel expectedUser) {
        new UserSessionServiceImpl(expectedUser);
        LoginServiceImpl loginServiceImpl = new LoginServiceImpl(new UserModel());
        loginServiceImpl.startLoginService();
    }
}
