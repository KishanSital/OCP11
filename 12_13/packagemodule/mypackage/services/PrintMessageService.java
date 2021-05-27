package mypackage.services;

@FunctionalInterface
public interface PrintMessageService {
    void print(String message); //SAM
    String toString();// will not count towards SAM test, because this method is from java.lang.Object
}
