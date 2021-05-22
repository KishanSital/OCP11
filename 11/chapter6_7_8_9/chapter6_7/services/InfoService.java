package chapter6_7.services;

import chapter6_7.interfaces.*;
import java.lang.*;

// concrete class that inherits all the abstract methods from InfoInterface and Info, all the abstract methods must be implemented 
public class InfoService extends Info{
	
	public static String classLocation = "Old location Info.java"; // static member, will be initialized when loading class
	
	public InfoService(){
		super();
	}
	@Override
	public void welcomeMessage(){ //overriding welcomeMessage() method from infoInterface
		System.out.println(this.message());
	}
	@Override
	public void gmailHistory(){ //overriding gmailHistory() method from infoInterface
		System.out.println("Created by Paul Buchheit on April 1, 2004");
	}
	
	@Override
	public CharSequence message(){ //overriding message() method from infoInterface
		return "Welcome to Gmail (old version)";
	}
	
	public static void printOutLocation(){ // static method will be initialized when loading class
		System.out.println(classLocation);
	}
	
}