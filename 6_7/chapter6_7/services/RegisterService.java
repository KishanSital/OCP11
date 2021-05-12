package chapter6_7.services;

import chapter6_7.constants.*;
import chapter6_7.entities.*;
import chapter6_7.repositories.*;
import java.util.*;	
import static chapter6_7.services.TriesValidationService.*;


public class RegisterService extends UserRepository{
	private Scanner scanner;
	private UserEntity userEntity;
	private String password, confirmPassword;
	private boolean isMatched;
	private MailEntity mailEntity;

	
	public RegisterService (UserEntity userEntity,
							MailEntity mailEntity){
		this.userEntity = userEntity;
		this.mailEntity = mailEntity;
	}
	
	public void createNewUser(){
		long newUserId = Constants.userRepository.autoIncrementId();
		userEntity.setUserId(newUserId);

		scanner = new Scanner(System.in);
		System.out.println("Enter your firstname");
		userEntity.setFirstName(scanner.next());
		
		System.out.println("Enter your lastname");
		userEntity.setLastName(scanner.next());
		
		System.out.println("Enter your username");
		userEntity.setUsername(scanner.next());
		resetTriesService();
		userNameValidation(userEntity.getUsername());
		
		System.out.println("Enter your password");
		password = scanner.next();
		resetTriesService();
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
		for (int i = 0; i < Constants.userRepository.getUsersList().size() ; i++ ){
			if ((Constants.userRepository.getUsersList().get(i).getUsername()).equalsIgnoreCase(username)){
				triesValidation();
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
				triesValidation();
				System.out.println("Please re-enter your password\n");
			}
		} while (isMatched == false);
	}
	
	private void insertUser(){
		if (Constants.userRepository.insertUser(userEntity)){
			insertRegistrationMail();
		}
	}
	private void insertRegistrationMail(){
		mailEntity.setReceiverUsername(userEntity.getUsername()+Constants.GMAIL_SUFFIX);
		mailEntity.setSenderUsername("Google"+Constants.GMAIL_SUFFIX);
		mailEntity.setSubject("Welcome to Gmail");
		mailEntity.setMessage(userEntity.getFirstName().substring(0,1).toUpperCase()
							 + userEntity.getFirstName().substring(1)
							 +" ,thank you for creating a Gmail account with us");
		Constants.mailRepository.insertMailInList(mailEntity);
		Constants.mailRepository.insertMailInSentList(mailEntity);
	}
}	