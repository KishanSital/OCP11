package chapter10;

import chapter10.constants.*;
import chapter10.entities.*;
import chapter10.services.*;
import chapter10.util.*;

public class App {

    public static void main(String... args) {

        UserEntity user = new UserEntity(1L,
                                 "Kishan",
                                 "1234");
        new UserEntityUtil(user);

        Constants.displayWelcomeMessage();

        LoginService loginService = new LoginService(new UserEntity());
        loginService.startLogin();

    }
}
