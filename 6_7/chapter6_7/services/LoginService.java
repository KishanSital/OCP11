package chapter6_7.services;


import chapter6_7.constants.*;
import chapter6_7.entities.*;
import chapter6_7.interfaces.*;
import chapter6_7.repositories.*;
import static chapter6_7.services.TriesValidationService.*;

import java.util.*;
import java.util.function.Predicate;

public class LoginService{
	
	private String username, password;
	private boolean isLoggedIn;
	
	public LoginService (){
	super();
	}
	
	public void startLogin() {
		resetTriesService();
		var scanner = new Scanner(System.in);
		boolean isAuthentication;
		
		do {
			System.out.println("Please enter your Gmail account");
			username = scanner.next();
			
			System.out.println("Please enter your password");
			password = scanner.next();

			isAuthentication = authenticateUser(k -> {
				if ( (k.getUsername().equalsIgnoreCase(username)) &&
					 (k.getPassword().equalsIgnoreCase(password)) ) {
					return true;
				} else {
					return false;
				}
			});
			
			if (!isAuthentication){
				triesValidation();
				System.out.println(" Your credentials were invalid, please try again\n");
			}
		} while (!isAuthentication);
		
		System.out.println("Login was successful\n");
		
		var loggedInMenuService = new LoggedInMenuService(getLoggedInUser());
		loggedInMenuService.loggedInInterface.loggedInMenuNavigator();
		
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
