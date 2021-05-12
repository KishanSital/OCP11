package chapter6_7.services;

import chapter6_7.constants.*;
import chapter6_7.entities.*;
import chapter6_7.interfaces.*;
import java.util.*;
import static chapter6_7.services.TriesValidationService.*;


public class MenuService implements MenuInterface {
	
    private List <String> menuOptions;
    private int exitCode = 3;
	private Scanner scanChoice;
	
    public MenuService (){
		super();
		init();
	}
	
	public void init(){
	
		scanChoice = new Scanner(System.in);
		resetAllValidationServices();
	}
	
	public void resetAllValidationServices(){
		 resetTriesService();
	}

    public void menuNavigator(){
      	    
        int choiceEntry = 0;		
   
        do {
            menuOptions();
			
			if (scanChoice.hasNextInt()){
				choiceEntry = scanChoice.nextInt();
			} 
			else {
				scanChoice.next();
				System.out.println("Please type a valid number \n");
				 triesValidation();

				continue;
			}
			
            switch (choiceEntry)
            {
				case 1:
				resetAllValidationServices();
				
				UserEntity userEntity = new UserEntity();
				MailEntity mailEntity = new MailEntity();
				RegisterService registerService = new RegisterService(userEntity,														
																	  mailEntity);
				registerService.createNewUser();
				break;
				case 2:
				resetAllValidationServices();
				
				LoginService loginService = new LoginService();
				loginService.startLogin();
				
				break;
				case 3:
				resetAllValidationServices();
				
				System.out.println("Thank you and goodbye");
				break;
				
				default:
				System.out.println("Choice must be a value between 1 and 3.\n");
				 triesValidation();
				
			}
		} while (choiceEntry != exitCode);
	}
	
	public void menuOptions(){
		menuOptions = new ArrayList<>();
		menuOptions.add("What would you like to do ?");
		menuOptions.add("to Create a Gmail account");
		menuOptions.add("to Log in");
		menuOptions.add("to close the app..");
		for (int i = 0 ; i < menuOptions.size(); i++){
            if (i == Constants.QUESTION_MESSAGE_INDEX){
				System.out.println(menuOptions.get(i));
				continue;
				} else {
				System.out.println("Type " + i + " " + menuOptions.get(i));
			}
		}

	}
	
	
}
