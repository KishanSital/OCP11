package chapter6_7.services;


import chapter6_7.constants.*;
import chapter6_7.entities.*;
import chapter6_7.interfaces.*;
import chapter6_7.repositories.*;
import static chapter6_7.services.TriesValidationService.*; // static import for all the static members 
import java.util.function.*;

import java.util.*;
import java.util.function.*;
import java.lang.*;

public class LoginService extends Object{
	private String username, password;
	private boolean isLoggedIn;
	private Consumer <String> println = p -> System.out.println(p); // consumer for printing messages
	
	public LoginService (){
		super();
	}
	
	public void startLogin() {
		resetTriesService(); // static method from triesValidationService class
		var scanner = new Scanner(System.in);
		boolean isAuthentication;
		do {
			println.accept("Please enter your Gmail account");
			username = scanner.next();
			
			println.accept("Please enter your password");
			password = scanner.next();

			isAuthentication = authenticateUser(k -> {
				if ( (k.getUsername().equalsIgnoreCase(username)) &&
					 (k.getPassword().equals(password)) ) {
					return true;
				} else {
					return false;
				}
			});
			
			if (!isAuthentication){
				triesValidation();
				println.accept(" Your credentials were invalid, please try again\n");
			}
		} while (!isAuthentication);
		
		println.accept("Login was successful\n");
		
		var loggedInMenuService = new LoggedInMenuService(getLoggedInUser());
		loggedInMenuService.loggedInInterface.loggedInMenuNavigator(); // calling the functional method		
	}

	public boolean authenticateUser(Predicate <UserEntity> loggingIn) { // predicate
		isLoggedIn = false;
		for (UserEntity usersEntity: Constants.userRepository.getUsersList()){
			if (loggingIn.test(usersEntity)){
				isLoggedIn = true;
				break;
			}
		}
		return isLoggedIn;
	  }
	
	private String getLoggedInUser() {
		return username;
	}
}
