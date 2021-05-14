package chapter6_7.interfaces;

@FunctionalInterface //annotation to indicate that this interface is intended to have one abstract method
public interface AddToListInterface <T> {  // passing type through, which i'll use in my method
	public boolean add (T t); //Could've used Predicate, but made my own instead
}