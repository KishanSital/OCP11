package chapter4.services;

import java.util.*;

import chapter4.entities.*;
import chapter4.repositories.*;
import chapter4.constants.*;

public class RegisterService {
	private UserRepository userRepository;
	private Scanner scanner;
	private UserEntity userEntity;
	private MailRepository mailRepository;
	private String password, confirmPassword;
	private boolean isMatched;
	private TriesValidationService triesValidationService;
	private MailEntity mailEntity;
	
	public RegisterService (UserRepository userRepository,
							UserEntity userEntity,
							TriesValidationService triesValidationService,
							MailRepository mailRepository,
							MailEntity mailEntity){
		
		this.userRepository = userRepository;
		this.userEntity = userEntity;
		this.triesValidationService = triesValidationService;
		this.mailRepository = mailRepository;
		this.mailEntity = mailEntity;
	}
	
	public void createNewUser(){
		long newUserId = userRepository.autoIncrementId();
		userEntity.setUserId(newUserId);
		
		scanner = new Scanner(System.in);
		System.out.println("Enter your firstname");
		userEntity.setFirstName(scanner.next());
		
		System.out.println("Enter your lastname");
		userEntity.setLastName(scanner.next());
		
		System.out.println("Enter your username");
		userEntity.setUsername(scanner.next());
		triesValidationService.resetTriesService();
		userNameValidation(userEntity.getUsername());
		
		System.out.println("Enter your password");
		password = scanner.next();
		triesValidationService.resetTriesService();
		confirmAndCheckPassword();
		
		System.out.println("Enter you phone number");
		userEntity.setPhoneNumber(scanner.next());
		
		System.out.println("Enter your birth date");
		userEntity.setBirthDate (scanner.next());
		insertUser();
	}
	
	
	
	private boolean checkUserExistence( String username){
		boolean isUserExistent = false;
		username+= Constants.GMAIL_SUFFIX;
		for (int i = 0; i < userRepository.getUsersList().size() ; i++ ){
			if ((userRepository.getUsersList().get(i).getUsername()).equalsIgnoreCase(username)){
				triesValidationService.triesValidation();
				System.out.println("Please try again, username has already been used\n");
				isUserExistent = true;
				break;
			} else {
				isUserExistent = false;
			}
		}
		return isUserExistent;
	}
	
	private void userNameValidation(String username){
		boolean isUserExistent = checkUserExistence(username);
		while (isUserExistent == true){
			System.out.println("Enter your username");
			userEntity.setUsername(scanner.next());
			isUserExistent = checkUserExistence(userEntity.getUsername());
		}
	}
	
	private void confirmAndCheckPassword(){
		do{
			System.out.println("Confirm your password");
			confirmPassword = scanner.next();

			if (password.equals(confirmPassword)){
				isMatched = true;
				userEntity.setPassword(password);
			} else {
				triesValidationService.triesValidation();
				System.out.println("Please re-enter your password\n");
			}
		} while (isMatched == false);
	}
	
	private void insertUser(){
		if (userRepository.insertUser(userEntity)){
			insertRegistrationMail();
		}
	}
	private void insertRegistrationMail(){
		mailEntity.setMailId(mailRepository.autoIncrementId());
		mailEntity.setReceiverUsername(userEntity.getUsername());
		mailEntity.setSenderUsername("Google");
		mailEntity.setSubject("Welcome to Gmail");
		mailEntity.setMessage("Thank you for creating a Gmail account with us");
		mailRepository.insertMailInList(mailEntity);
		
	}
}	