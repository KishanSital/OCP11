package chapter12_13;

import chapter12_13.serviceImpl.*;
import mypackage.application.*;
import mypackage.models.*;
import mypackage.utils.StringUtilsMyPackage;


public class App {
    public static void main(String ... args) {

        StringUtilsMyPackage.displayWelcomeMessage();

        var userModel = new UserModel(1L, "Kishan", "1234") {
            @Override
            public String toString(){
                return "Entering anonymous class\nThe expected username is "
                        + getUsername() + "\nThe expected password is "
                        + getPassword()
                        + "\n";
            }
        };
        System.out.println(userModel);
        MyPackageApplication.startLoginService(userModel);

        var fireArmsService = new FireArmsServiceImpl();
        fireArmsService.insertStandardFireArms();

        var loggedInMenuServiceImpl = new LoggedInMenuServiceImpl(fireArmsService);
        loggedInMenuServiceImpl.displayMenu();

    }
}
