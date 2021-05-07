package chapter4.services;

import java.util.*;
import chapter4.constants.*;
import chapter4.repositories.*;
import chapter4.entities.*;

public class MenuService {
	
    private List <String> menuOptions;
    private TriesValidationService triesValidationService;
    private int exitCode = 3;
	private Scanner scanChoice;
	
    public MenuService (TriesValidationService triesValidationService){
       
	   this.triesValidationService = triesValidationService;
	
		init();
	}
	
	private void init(){
	
		scanChoice = new Scanner(System.in);
		
		resetAllValidationServices();
	}
	
	private void resetAllValidationServices(){
		triesValidationService.resetTriesService();
	}
	
	
	
    public void menuNavigator(UserRepository userRepository,
							  MailRepository mailRepository){
      	    
        int choiceEntry = 0;		
   
        do {
            menuOptions();
			
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
				
				UserEntity userEntity = new UserEntity();
				MailEntity mailEntity = new MailEntity();
				RegisterService registerService = new RegisterService(userRepository,
																	  userEntity,
																	  triesValidationService,
																	  mailRepository,
																	  mailEntity);
				registerService.createNewUser();
				break;
				case 2:
				resetAllValidationServices();
				
				LoginService loginService = new LoginService(userRepository,
															 triesValidationService,
															 mailRepository);
				loginService.startLogin();
				
				break;
				case 3:
				resetAllValidationServices();
				
				System.out.println("Thank you and goodbye");
				break;
				
				default:
				System.out.println("Choice must be a value between 1 and 3.\n");
				triesValidationService.triesValidation();
				
			}
		} while (choiceEntry != exitCode);
	}
	
	private void menuOptions(){
		menuOptions = new ArrayList<>();
		menuOptions.add("What would you like to do ?");
		menuOptions.add("to Create a Gmail account");
		menuOptions.add("to Log in");
		menuOptions.add("to close the app..");
		First:   for (int i = 0 ; i < menuOptions.size(); i++){
            if (i == Constants.QUESTION_MESSAGE_INDEX){
				System.out.println(menuOptions.get(i));
				continue First;
				} else {
				System.out.println("Type " + i + " " + menuOptions.get(i));
			}
		}
		
	}
	
	
}
