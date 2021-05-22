package chapter4.services;

import chapter4.constants.*;
import chapter4.entities.*;
import chapter4.repositories.*;
import java.util.*;

public class LoginService {
	
	private TriesValidationService triesValidationService;
	private UserRepository userRepository;
	private MailRepository mailRepository;
	private String username, password;
	private boolean isLoggedIn;
	
	public LoginService (UserRepository userRepository,
						 TriesValidationService triesValidationService,
						 MailRepository mailRepository){
		
		this.triesValidationService = triesValidationService;
		this.userRepository = userRepository;
		this.mailRepository = mailRepository;
		
	}
	
	public void startLogin() {
		triesValidationService.resetTriesService();
		
		var scanner = new Scanner(System.in);
		
		do {
			System.out.println("Please enter your Gmail account");
			username = scanner.next();
			
			System.out.println("Please enter your password");
			password = scanner.next();
			authenticateUser();
			
			if (isLoggedIn == false ){
				triesValidationService.triesValidation();
				System.out.println(" Your credentials were invalid, please try again\n");
			}
		} while (authenticateUser() == false);
		
		System.out.println("Login was successful\n");
		
		var loggedInMenuService = new LoggedInMenuService(getLoggedInUser(),
														  userRepository,
														  triesValidationService ,
														  mailRepository);
		loggedInMenuService.loggedInMenuNavigator();
		
	}
	
	private boolean authenticateUser() {
		isLoggedIn = false;
		for (UserEntity users : userRepository.getUsersList()){
			if ( (username.equalsIgnoreCase(users.getUsername()))  &&
					(password.equals(users.getPassword()))){
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
