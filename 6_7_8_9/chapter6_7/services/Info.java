package chapter6_7.services;

import chapter6_7.interfaces.*;
import java.lang.Object;

public abstract class Info extends Object implements InfoInterface { // abstract class Info that inherits all the abstract methods from InfoInterface
	public Info() {
	super();
	}
	public abstract CharSequence message(); // abstract method with the same signature and return type already exists in InfoInterface, this won't cause conflicts
	public abstract void gmailHistory(); // abstract method with the same signature and return type already exists in InfoInterface, won't cause conflicts
	
	protected void hello(){ // normal method which could get overridden in the subclass
	System.out.println("hello");		
	}
}