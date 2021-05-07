package chapter4.repositories;

import chapter4.entities.*;
import java.util.*;

public class MailRepository {
	
	
	private UserRepository userRepository;
	private MailEntity mailEntity;
	private List <MailEntity> mailsList = new ArrayList<MailEntity>();
	private String [] mails [];
	
	public MailRepository(UserRepository userRepository,
						  MailEntity mailEntity){
		this.userRepository = userRepository;
		this.mailEntity = mailEntity;
	}
	
	public void insertStandard2dMails() {
		mails  = new String [2][3];
		mails [0][0]= "From: Google, ";
		mails [0][1] = " Subject: Welcome to Gmail, ";
		mails [0][2] = " Message: Thank you for creating a Gmail account with us ";
		mails [1][0] = "From: Instagram, ";
		mails [1][1] = " Subject : Welcome to Instagram, ";
		mails [1][2] = " Message : Thank you for creating an Instagram account \n";
	}
	
	public void print2dMails(){
		for (int i = 0  ; i < mails.length ; i++){
			for (int j = 0; j < mails[i].length; j++){
				System.out.print(  mails[i][j]);
			}
			System.out.println(" ");
		}
	}
	
	public void insertMailInList (MailEntity mail){
		if (mailsList.add(new MailEntity(mail))){
		} else {
			System.out.println("Something went wrong, please try again later");
		}
		
	}
	
	public long autoIncrementId(){
		if (mailsList.isEmpty()){
			return 1;
		} else {
			return ((mailsList.get(mailsList.size() -1).getMailId()) + 1);
		}
	}
	
	public List<MailEntity> getMailsList(){
		return mailsList;
	}
	
}
