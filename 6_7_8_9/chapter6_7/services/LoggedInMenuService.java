package chapter6_7.services;

import chapter6_7.constants.*;
import chapter6_7.interfaces.*;
import chapter6_7.repositories.*;
import chapter6_7.views.*;
import chapter6_7.entities.*;
import static chapter6_7.services.TriesValidationService.*; // static import for all static members
import java.util.*;

@FunctionalInterface
interface LoggedInInterface{ //inner class 
	void loggedInMenuNavigator();
}
public class LoggedInMenuService implements MenuInterface { // method signature must be implemented
	private List<String> loggedInMenuOptions;
	private int exitCode = 4;
	private String loggedInUser;
	private Scanner scanChoice;
	private MailsView mailsView;

    public LoggedInMenuService (String loggedInUser){
		super(); // super() or this() must always be the first line in a constructor if used, both can't be used simultaniously in one constructor
        this.loggedInUser = loggedInUser;
		init();
	}
	@Override
	public void init(){ // implemented from MenuInterface
		scanChoice = new Scanner(System.in);
		mailsView = new MailsView(loggedInUser);
		resetAllValidationServices();
	}
	@Override
	public void resetAllValidationServices(){ // implemented from MenuInterface
	 resetTriesService(); // static method 
	}
	@Override
    public void menuOptions(){ // implemented from MenuInterface
		loggedInMenuOptions = new ArrayList<>();
		loggedInMenuOptions.add("What would you like to do ?");
		loggedInMenuOptions.add("to view your inbox");
		loggedInMenuOptions.add("to send an email");
		loggedInMenuOptions.add("to view sent mails");
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
	 public LoggedInInterface loggedInInterface = ()-> { // functional interface for loggedInMenuNavigator
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
					mailsView.viewMyInbox();
					break;
				case 2:
					resetAllValidationServices();
					MailEntity mailEntity = new MailEntity();
					SendMailService sendMailService = new SendMailService(loggedInUser,
																		  mailEntity);
					sendMailService.sendMail();
					break;
				case 3:
					 resetAllValidationServices();
					 mailsView.viewMySentMails();
					break;
				case 4:
					resetAllValidationServices();
					System.out.println("You've been logged out successfully\n");
					break;
				default:
					triesValidation();
					System.out.println("Choice must be between 1 till "+ exitCode+"\n");
			}
		} while (choiceEntry != exitCode);

	};

}
