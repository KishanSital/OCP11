package chapter6_7.views;


import chapter6_7.constants.*;
import chapter6_7.entities.*;
import chapter6_7.entities.*;
import chapter6_7.interfaces.*;
import chapter6_7.interfaces.*;
import chapter6_7.services.*;
import static chapter6_7.services.TriesValidationService.*;
import java.util.*;
import java.util.function.*;
import java.lang.*;

public class MailsView extends Object implements MenuInterface{
    private String loggedInUser;
    private Consumer<String> consumerMsg = (x) -> System.out.println(x);
    private List<String> menuOptionsList = new ArrayList<String>();
    private Scanner scanner;
    private int exitCode = 2;
	private int mailCounter;

	
    public MailsView(String loggedInUser) {
		super(); //calling constructor of java.lang.Object
        this.loggedInUser = loggedInUser;
        init();
	}

	@Override
    public void init() {
        scanner = new Scanner(System.in);
        resetAllValidationServices();
    }
	
	@Override
    public void resetAllValidationServices() {
		resetTriesService();
    }
	
    public void viewMyInbox(){
		mailCounter = 0;
        Constants.mailRepository.getMailsList().forEach(mail -> {  // forEach method from Lambda API to display Inbox
            if (loggedInUser.equalsIgnoreCase(mail.getReceiverUsername())){
                consumerMsg.accept(mail.toStringReceivedMail());
				mailCounter++;
				}
            });			
			if (mailCounter == Constants.NO_MAILS_FOUND){
				consumerMsg.accept("Inbox was empty\n");
			}
        menuNavigator(Constants.COMING_FROM_INBOX);
        return;
		}

    public void viewMySentMails() {
		mailCounter = 0;
        Constants.mailRepository.getSentMailsList().forEach(mailsSent -> { // forEach method from Lambda API to display Inbox
            if (loggedInUser.equalsIgnoreCase(mailsSent.getSenderUsername())){
                consumerMsg.accept(mailsSent.toStringSentMail());
				mailCounter++;
            }
        });
		if (mailCounter == Constants.NO_MAILS_FOUND){
				consumerMsg.accept("There were no sent mails\n");
			}		
        menuNavigator(Constants.COMING_FROM_SENT);
        return;
    }
    public void menuNavigator(String comingFrom){
            int choiceEntry = 0;
            do {
                menuOptions();
                if (scanner.hasNextInt()){
                    choiceEntry = scanner.nextInt();
                }
                else {
                    scanner.next();
                    System.out.println("Please type a valid number \n");
					triesValidation();
                    continue;
                }

                switch (choiceEntry)
                {
                    case 1:
                        resetAllValidationServices();
                        Constants.mailRepository.deleteMail(comingFrom,
                                                            loggedInUser);
                        break;
                    case 2:
                        resetAllValidationServices();
                        System.out.println("Thank you and goodbye");
                        break;
                    default:
                        System.out.println("Choice must be a value between 1 and 2.\n");
						triesValidation();
                }
            } while (choiceEntry != exitCode);
        }
		
	@Override
    public void menuOptions() {
        menuOptionsList = new ArrayList<String>();
        menuOptionsList.add("What would you like to do?");
        menuOptionsList.add("to delete a mail");
        menuOptionsList.add("to navigate back");

        for (int i = 0 ; i < menuOptionsList.size();i++){
            if (i == Constants.QUESTION_MESSAGE_INDEX){
                continue;
            } else {
                consumerMsg.accept("Type " + i + " " +menuOptionsList.get(i));
            }
        }

    }
}
