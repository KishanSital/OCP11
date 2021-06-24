package mypackage.exceptions;

public class LoginException extends Exception implements AutoCloseable {

    public LoginException() {
        super();
    }

    public LoginException(String message) {
        super(message);
    }

    @Override
    public void close(){
    }
}
