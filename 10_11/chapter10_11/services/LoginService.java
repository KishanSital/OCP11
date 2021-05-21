package chapter10_11.services;

import chapter10_11.entities.*;
import chapter10_11.exceptions.*;
import java.util.*;
import java.util.function.*;
import static chapter10_11.constants.Constants.*;
import static chapter10_11.services.TriesValidationService.*;
import static chapter10_11.util.UserEntityUtil.*;

public class LoginService extends Object {
    boolean isAuthentication;
    private boolean isLoggedIn;
    private UserEntity userEntity;
    private Consumer<String> println = p -> System.out.println(p);
    private Scanner scanner;
    private String directory;

    public LoginService(UserEntity userEntity) {
        super();
        this.userEntity = userEntity;
        init();
    }

    private void init() {
        scanner = new Scanner(System.in);
    }

    public void startLogin() {
        resetTriesService();
        do {
            provideCredentials();
            authentication();
        } while (!isAuthentication);
        println.accept(LOGIN_SUCCESS_MESSAGE);

        providePath();
        var loggedInMenuService = new LoggedInMenuService(directory);
        loggedInMenuService.displayMenu();
    }

    private void providePath() {
        System.out.println(ENTER_THE_PATH_MESSAGE);
        directory = scanner.next();
        System.out.println("");
    }

    private void authentication() {

        try (LoginException loginException = new LoginException()) {
            isAuthentication = authenticationResult();
            if (!isAuthentication) {
                throw new LoginException(INVALID_CREDENTIALS_MESSAGE);
            }
        } catch (LoginException e) {
            e.printStackTrace();
            e.getMessage();
            System.out.println(e);
        } catch (IllegalArgumentException | NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (!isAuthentication) {
                triesValidation();
            }
        }
    }

    private void provideCredentials() {
        println.accept(PROVIDE_USERNAME_MESSAGE);
        userEntity.setUsername(scanner.next());

        println.accept(PROVIDE_PASSWORD_MESSAGE);
        userEntity.setPassword(scanner.next());
    }

    private boolean authenticationResult() {
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

    public boolean authenticateUser(Predicate<UserEntity> loggingIn) {
        isLoggedIn = false;
        if (loggingIn.test(userEntity)) {
            isLoggedIn = true;
        }
        return isLoggedIn;
    }

    private String getLoggedInUser() {
        return LOGIN_USERNAME;
    }
}
