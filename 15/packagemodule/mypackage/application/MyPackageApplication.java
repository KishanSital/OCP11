package mypackage.application;

import mypackage.models.*;
import mypackage.serviceImpl.*;
import mypackage.services.*;

public class MyPackageApplication {
    public static void startLoginService(UserModel expectedUser) {

        //Constructor reference
        UserModelEmpty userModelEmpty= UserModel::new;
        new UserSessionServiceImpl(expectedUser);
        LoginServiceImpl loginServiceImpl = new LoginServiceImpl(userModelEmpty.create());
        loginServiceImpl.startLoginService();
    }
}
