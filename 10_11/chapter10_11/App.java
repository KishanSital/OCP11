package chapter10_11;

import chapter10_11.constants.*;
import chapter10_11.entities.*;
import chapter10_11.services.*;
import chapter10_11.util.*;

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
