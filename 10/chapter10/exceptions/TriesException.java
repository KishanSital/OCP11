package chapter10.exceptions;

import static chapter10.constants.Constants.*;

public class TriesException extends Exception implements AutoCloseable{

    public TriesException (){

    }
    public TriesException (String message){
        super(message);
    }

    @Override
    public void close() {
        System.out.println(CLOSING_TRIES_EXCEPTION);
    }
}
