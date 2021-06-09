package mypackage.exceptions;


public class TriesException extends Exception implements AutoCloseable{

    public TriesException (){

    }
    public TriesException (String message){
        super(message);
    }

    @Override
    public void close() {

    }
}
