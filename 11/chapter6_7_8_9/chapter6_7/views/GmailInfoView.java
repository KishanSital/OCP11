package chapter6_7.views;
import chapter6_7.services.*;

public class GmailInfoView extends InfoService {
	//member hiding
	public static String classLocation = "New Location GmailInfoView.java";
	
	public GmailInfoView(){
		super();
	}
	//overridden method
	@Override
	public void welcomeMessage(){
		System.out.println(this.message());
	}
	//overridden method
	@Override
	public void gmailHistory(){
		System.out.println("Created by Kishan on May 17, 2021");
	}
	//overridden method
	@Override
	public StringBuilder message(){ //covariant return type of CharSequence 
		return new StringBuilder("Welcome to Gmail by Kishan");
	}
	//method hiding
	public static void printOutLocation(){
		System.out.println(classLocation);
	}
	
}