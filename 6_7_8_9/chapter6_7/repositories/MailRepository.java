package chapter6_7.repositories;

import chapter6_7.constants.*;
import chapter6_7.entities.*;
import java.util.*;
import java.util.function.*;
import chapter6_7.interfaces.*;
import static chapter6_7.services.TriesValidationService.*;


public class MailRepository {
	private List <MailEntity> inboxMailsList = new ArrayList<MailEntity>();
	private List <MailEntity> sentMailsList = new ArrayList<>();
	private Consumer <String> consumerMessages = x -> System.out.println(x); // Consumer functionalInterface 
	public  AddToListInterface <MailEntity> addMailInterface = m -> insertAllMails(m); // personal created functionalInterface
	private Scanner scanner;
	private int mailsFound;
	private final int noMailsFound = 0;
	long mailId;
	private Predicate<String> selectedSentMailIdValidation = m -> removeSentMailIdValidation(m); 
	private Predicate<String> selectedInboxMailIdValidation = m -> removeInboxMailIdValidation(m); 

	public MailRepository(){ // constructor
		super(); // calls the constructor of java.lang.Object class
		this.scanner = new Scanner(System.in);
	}

	private boolean insertMailInList (MailEntity mail){
		mail.setMailId(autoIncrementId());
		if (inboxMailsList.add(new MailEntity(mail))){
			return true;
		} else {
			return false;
		}
	}

	private boolean insertMailInSentList (MailEntity mail){
		mail.setMailId(autoIncrementIdMailsSent());
		if (sentMailsList.add(new MailEntity(mail))){
			return true;
		} else {
			return false;
		}
	}

	public boolean insertAllMails(MailEntity mail){
		if (insertMailInList(mail) && insertMailInSentList(mail)){
		return true;
		} else {
		consumerMessages.accept("Something went wrong, please try again later");
		return false;
		}
	}
	
	public long autoIncrementId(){
		if (inboxMailsList.isEmpty()){
			return 1;
		} else {
			return ((inboxMailsList.get(inboxMailsList.size() -1).getMailId()) + 1);
		}
	}

	public long autoIncrementIdMailsSent(){
		if (sentMailsList.isEmpty()){
			return 1;
		} else {
			return ((sentMailsList.get(sentMailsList.size() -1).getMailId()) + 1);
		}
	}
	
	public List<MailEntity> getMailsList(){
		return inboxMailsList;
	}

	public List<MailEntity> getSentMailsList(){
		return sentMailsList;
	}

	public void deleteMail(String comingFrom, String loggedInUser) {
		if (comingFrom.equals(Constants.COMING_FROM_INBOX)){
			removeMailFromInbox(loggedInUser);
		} else if (comingFrom.equals(Constants.COMING_FROM_SENT)){
			removeMailFromSentBox(loggedInUser);
		}
	}

	private void removeMailFromSentBox(String loggedInUser) {
		mailsFound = 0;
		for (int i = 0; i <getSentMailsList().size(); i++){
			if (loggedInUser.equalsIgnoreCase(getSentMailsList().get(i).getSenderUsername())){
				consumerMessages.accept(" Id :" + getSentMailsList().get(i).getMailId() + "\n"
						+ getSentMailsList().get(i).toStringSentMail());
				++mailsFound;
			}
		}
		if (mailsFound > noMailsFound){
			consumerMessages.accept("Type the mail Id you'd like to delete\n");
			removingSentMail(loggedInUser);
		} else {
			consumerMessages.accept("There were no sent mails\n");
		}
	}
		
	private void removeMailFromInbox(String loggedInUser) {
			mailsFound = 0;
		for (int i = 0; i <getMailsList().size(); i++){
			if (loggedInUser.equalsIgnoreCase(getMailsList().get(i).getReceiverUsername())){
				consumerMessages.accept(" Id :" + getMailsList().get(i).getMailId() + "\n"
						+ getMailsList().get(i).toStringReceivedMail());
				++mailsFound;
			}
		}
		if (mailsFound > noMailsFound) {
			consumerMessages.accept("Type the mail Id you'd like to delete\n");
			removingSentMail(loggedInUser);
		} else {
				consumerMessages.accept("Inbox was empty\n");
		}
	}
	
	private void removingSentMail( String loggedInUser){		
		boolean isIdSelectionValid = false;
		resetTriesService();
		do {
			mailId = scanner.nextLong();
			isIdSelectionValid = selectedSentMailIdValidation.test(loggedInUser); //predicate
			if (!isIdSelectionValid){
				triesValidation();
				consumerMessages.accept("The given Id does not exist\n");
				}				
			} while (!isIdSelectionValid);
			boolean isMailRemoved = getSentMailsList().removeIf(sentMail -> (sentMail.getMailId() == mailId)); //removeIn() method from Lamba API
			if (isMailRemoved){
				consumerMessages.accept("Mail removed successfully\n");
			}
	}
	
	private boolean removeSentMailIdValidation(String loggedInUser){
		for (int i = 0 ; i < getSentMailsList().size();i++){
				if ((loggedInUser.equalsIgnoreCase(getSentMailsList().get(i).getSenderUsername()))
					&&(getSentMailsList().get(i).getMailId() == mailId)){
						return true;
				}				
			}			
		return false;
	}
	
	private void removingInboxMail(String loggedInUser){
	boolean isIdSelectionValid = false;
			resetTriesService();
			do {
				mailId = scanner.nextLong();
				isIdSelectionValid = selectedInboxMailIdValidation.test(loggedInUser); // predicate
				if (!isIdSelectionValid){
					triesValidation();
					consumerMessages.accept("The given Id does not exist\n");
				}				
			} while (!isIdSelectionValid);
			boolean isMailRemoved = getMailsList().removeIf(inboxMail -> (inboxMail.getMailId() == mailId));  //removeIn() method from Lamba API
			if (isMailRemoved){
				consumerMessages.accept("Mail removed successfully\n");
				}	
	}
	private boolean removeInboxMailIdValidation(String loggedInUser){
		for (int i = 0 ; i < getMailsList().size();i++){
				if ((loggedInUser.equalsIgnoreCase(getMailsList().get(i).getReceiverUsername()))
					&&(getMailsList().get(i).getMailId() == mailId)){
						return true;
				}				
			}			
		return false;
	}

}
