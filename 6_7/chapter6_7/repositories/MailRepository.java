package chapter6_7.repositories;

import chapter6_7.constants.*;
import chapter6_7.entities.*;

import java.util.*;
import java.util.function.*;

public class MailRepository {
	

	private List <MailEntity> inboxMailsList = new ArrayList<MailEntity>();
	private List <MailEntity> sentMailsList = new ArrayList<>();
	private Consumer <String> consumerMessages = x -> System.out.println(x);
	private Scanner scanner;
	private int mailsFound;
	private final int noMailsFound = 0;


	public MailRepository(){
		this.scanner = new Scanner(System.in);
	}

	public void insertMailInList (MailEntity mail){
		mail.setMailId(autoIncrementId());
		inboxMailsList.add(new MailEntity(mail));

	}

	public void insertMailInSentList (MailEntity mail){
		mail.setMailId(autoIncrementIdMailsSent());
		sentMailsList.add(new MailEntity(mail));

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
			consumerMessages.accept("Type the mail Id you'd like to delete");
			long mailId = scanner.nextLong();
			boolean isMailRemoved = getSentMailsList().removeIf(sentMail -> (sentMail.getMailId() == mailId));
			if (isMailRemoved){
				consumerMessages.accept("Mail removed successfully");
			}
		} else {
			consumerMessages.accept("There were no sent mails");
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
			consumerMessages.accept("Type the mail Id you'd like to delete");
			long mailId = scanner.nextLong();
			boolean isMailRemoved = getMailsList().removeIf(inboxMail -> (inboxMail.getMailId() == mailId));
			if (isMailRemoved) {
				consumerMessages.accept("Mail removed successfully");
			}
		} else {
				consumerMessages.accept("Inbox was empty");
		}
	}
}
