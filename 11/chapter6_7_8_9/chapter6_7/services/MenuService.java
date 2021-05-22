package chapter6_7.services;

import chapter6_7.views.*;
import chapter6_7.constants.*;
import chapter6_7.entities.*;
import chapter6_7.interfaces.*;
import java.util.*;
import static chapter6_7.services.TriesValidationService.*; // imports all static members
import chapter5.*;


public class MenuService implements MenuInterface {
	
    private List <String> menuOptions;
    private int exitCode = 5;
	private Scanner scanChoice;
	private GmailInfoView gmailInfoView;
	private InfoService infoService;
	private Info infoAbstractClass;
	
    public MenuService (){
		super();
		init();
	}
	
	@Override
	public void init(){
		scanChoice = new Scanner(System.in);
		resetAllValidationServices();
		
		infoService = new InfoService();
		gmailInfoView = new GmailInfoView();
	}
	
	@Override
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
					RegisterService registerService = new RegisterService(mailEntity,
																	  userEntity);
					registerService.createNewUser();
				break;
				case 2:
					resetAllValidationServices();
				
					LoginService loginService = new LoginService();
					loginService.startLogin();
				break;
				case 3:
					resetAllValidationServices();
					InfoInterface infoInterface = infoService; //casting InfoService object from heap memory with all of it's properties to InfoInterface type 
					infoInterface.welcomeMessage(); //calling methods that are available in InfoInterface using same properties of InfoService object from the heap memory
					infoInterface.gmailHistory(); // same happens here
					
					infoAbstractClass = infoService; // casting infoService object from heap memory with it's properties to infoAbstractClass
					infoAbstractClass.welcomeMessage(); // calling methods that are available in  infoAbstractClass with the properties of infoService object
					
					infoService = null; // InfoService reference points to no object on heap memory
					infoService.printOutLocation(); // because it's a static method JVM will know that you're calling a static class method 
					System.out.println("");
				
				break;
				
				case 4:
					resetAllValidationServices();
					gmailInfoView = null; // gmailInfoView reference doesn't point to any object on heap memory
					gmailInfoView.printOutLocation(); // calling static hidden method from GmailInfoView class				
					infoService = new GmailInfoView(); // InfoService reference now points to a new object of GmailInfoView on heap memory
					infoService.welcomeMessage(); //calling the methods that are available from the InfoService type with properties of GmailInfoView from heap memory
					infoService.gmailHistory();// same happens here
					
					infoAbstractClass = infoService; // infoService reference points to the GmailInfoView object on our heap memory and our infoAbstractClass reference also points to the same object( both will make use of the same properties)
					infoAbstractClass.welcomeMessage();
					
					infoService = null;// InfoService reference now points to null
					infoService.printOutLocation(); // calling the static method from InfoService class
					System.out.println("");
				break;
				
				case 5:
					resetAllValidationServices();
					System.out.println("Thank you and goodbye");
				break;
				
				case 6:
					resetAllValidationServices();
					System.out.println("Thank you and goodbye");
					App.main("");
				break;
				default:
					System.out.println("Choice must be a value between 1 and 6.\n");
					triesValidation();
			}
		} while (choiceEntry != exitCode);
	}
	
	@Override
	public void menuOptions(){
		menuOptions = new ArrayList<>();
		menuOptions.add("What would you like to do ?");
		menuOptions.add("to Create a Gmail account");
		menuOptions.add("to Log in");
		menuOptions.add("to print old info");
		menuOptions.add("to print updated info");
		menuOptions.add("to close the app..");
		menuOptions.add("to navigate to chapter5");
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
