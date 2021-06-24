package mypackage.application;

import mypackage.models.*;
import mypackage.serviceImpl.*;
import mypackage.services.*;
import mypackage.views.LoginView;

public class MyPackageApplication {
    public static void startLoginService(UserModel expectedUser) {

        //Constructor reference
        UserModelEmpty userModelEmpty= UserModel::new;
        new UserSessionServiceImpl(expectedUser);
        LoginService loginServiceImpl = new LoginServiceImpl(userModelEmpty.create());
        new LoginView(loginServiceImpl).startLoginService();
    }
}
