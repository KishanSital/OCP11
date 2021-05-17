package chapter6_7.interfaces;

@FunctionalInterface //annotation to indicate that this interface is intended to have one abstract method
public abstract interface AddToListInterface <T> {  // passing type, which i'll use in my method abstract method
	public abstract boolean add (T type); //Could've used Predicate, but made my own instead
}