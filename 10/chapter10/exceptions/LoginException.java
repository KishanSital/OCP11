package chapter10.exceptions;

import static chapter10.constants.Constants.*;

public class LoginException extends Exception implements AutoCloseable {

    public LoginException() {
        super();
    }

    public LoginException(String message) {
        super(message);
    }

    @Override
    public void close(){
        System.out.println(CLOSING_LOGIN_EXCEPTION);
    }
}
