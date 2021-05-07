package chapter4.services;

import chapter4.constants.*;
import chapter4.entities.*;
import chapter4.repositories.*;
import chapter4.views.MailsView;

import java.util.*;


public class LoggedInMenuService {
	
	private UserRepository userRepository;
	private TriesValidationService triesValidationService;
	private MailRepository mailRepository;
	private List<String> loggedInMenuOptions;
	private int exitCode = 2;
	private String loggedInUser;
	private Scanner scanChoice;
	
	
    public LoggedInMenuService (String loggedInUser,
								UserRepository userRepository,
								TriesValidationService triesValidationService,
								MailRepository mailRepository){
		
        this.loggedInUser = loggedInUser;
        this.userRepository= userRepository;
        this.triesValidationService = triesValidationService;
        this.mailRepository = mailRepository;
		
		init();
	}
	
	private void init(){
	
		scanChoice = new Scanner(System.in);
		resetAllValidationServices();
	
	}
	
	private void resetAllValidationServices(){
		triesValidationService.resetTriesService();
	
	}
	
	
	
    public void loggedInMenuNavigator (){
		
		int choiceEntry = 0;	
		
		do {
			LoggedInMenuOptions();
			
			if (scanChoice.hasNextInt()){
				choiceEntry = scanChoice.nextInt();
			} 
			else {
				scanChoice.next();
				System.out.println("Please type a valid number \n");
				triesValidationService.triesValidation();

				continue;
			}
			switch (choiceEntry)
			{
				
				case 1:
				
				resetAllValidationServices();
				
				MailsView mailsView = new MailsView( userRepository,
													 mailRepository,
													 loggedInUser);
				mailsView.viewMyInbox();
				break;
				
				case 2:
				
				resetAllValidationServices();
				System.out.println("You've been logged out successfully\n");
				break;
				default:
				triesValidationService.triesValidation();
				System.out.println("Choice must either be 1 or 2\n");
			}
		} while (choiceEntry != exitCode);
		
		
	}
	
	
	
    private void LoggedInMenuOptions(){
		loggedInMenuOptions = new ArrayList<>();
		loggedInMenuOptions.add("What would you like to do ?");
		loggedInMenuOptions.add("to view your inbox");
		loggedInMenuOptions.add("to Log out");
		First:   for (int i = 0 ; i < loggedInMenuOptions.size(); i++){
			if (i == Constants.QUESTION_MESSAGE_INDEX){
				System.out.println(loggedInMenuOptions.get(i));
				continue First;
				} else {
				System.out.println("Type " + i + " " + loggedInMenuOptions.get(i));
			}
		}
		
	}

}
