package mypackage.views;

import mypackage.exceptions.LoginException;
import mypackage.serviceImpl.LoginServiceImpl;
import mypackage.services.LoginService;
import mypackage.services.PrintMessageService;
import mypackage.utils.IntUtilsMyPackage;
import mypackage.utils.StringUtilsMyPackage;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

import static mypackage.serviceImpl.TriesValidationServiceImpl.resetTriesService;
import static mypackage.serviceImpl.TriesValidationServiceImpl.triesValidation;

public final class LoginView {
    private LoginService loginService;
    private Scanner scanner;
    private String[] args;
    public PrintMessageService println = System.out::println; // instance method on parameter reference


    public LoginView(LoginService loginService) {
        this.loginService = loginService;
        this.scanner = new Scanner(System.in);
        this.args = new String[2];
    }

    public void startLoginService() {
        resetTriesService();
        do {
            providingCredentials();
            loginService.provideCredentials(args);
            authentication();
        } while (!loginService.isAuthentication());
        println.print(StringUtilsMyPackage.LOGIN_SUCCESS_MESSAGE.getStringValue());
    }


    public void providingCredentials() {
        //using console class for user interaction
        Console console = System.console();
        if (console == null) {
            System.out.println("console not available");
        } else {
            args[IntUtilsMyPackage.USERNAME_INDEX.getIntValue()] = console.readLine(StringUtilsMyPackage.PROVIDE_USERNAME_MESSAGE.getStringValue());
            console.writer().format("Hi %s", args[IntUtilsMyPackage.USERNAME_INDEX.getIntValue()]);
            console.writer().println();

            char[] password = console.readPassword(StringUtilsMyPackage.PROVIDE_PASSWORD_MESSAGE.getStringValue());
            args[IntUtilsMyPackage.PASSWORD_INDEX.getIntValue()] = (String.valueOf(password));
        }
    }

    public void authentication() {
        LoginException loginException = new LoginException(); // must be final or effectively final, to be used in
        //try with resource statement
        try (loginException) { // if both the close and the code within the try clause throw an exception then
            // we'd then have suppressed exceptions, with the one thrown from protected code being the primary exception
            loginService.setAuthentication(loginService.authenticationResult());
            if (!loginService.isAuthentication()) {
                throw new LoginException(StringUtilsMyPackage.INVALID_CREDENTIALS_MESSAGE.getStringValue());
            }
        } catch (LoginException e) {
            println.print(e.getMessage());
            for (Throwable t : e.getSuppressed()) {
                println.print("Suppressed: " + t.getMessage()); // looping through the suppressed exceptions
            }
        } finally {
            if (!loginService.isAuthentication()) {
                triesValidation();
            }
        }

/*        enabling assertions commands
        java -enableassertions MyPackageApplication
        java -ea MyPackageApplication
        java -ea:mypackage... MyPackageApplication (enable for that package and all the subpackages)
        java -ea:mypackage.views.LoginView MyPackageApplication (enable for specific class)

       disabling assertions command
        java -ea:mypackage... -da:mypackage.views.LoginView MypackageApplication (disabling for specific class)*/

        assert (1 + 1) == 2 : "1 + 1 is indeed 2"; // assert with optional message
    }
}
