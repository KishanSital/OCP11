package chapter6_7.interfaces;

//interface with abstract public methods
abstract public interface InfoInterface { // for interfaces jvm implicitly adds public static final for variables and for methods it adds public abstract
	int ZERO = 0;
	abstract public void welcomeMessage();
	public abstract void gmailHistory();
	CharSequence message();
}