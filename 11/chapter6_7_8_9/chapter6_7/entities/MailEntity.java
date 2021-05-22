package chapter6_7.entities;

import chapter6_7.constants.*;
import java.lang.Object; // JVM makes sure that all the casses of the java.lang package gets imported so this is not manditory

public class MailEntity extends Object{ // JVM takes care of extending the Object class, so this is not manditory
	private long mailId;
	private String receiverUsername;
	private String senderUsername;
	private String subject;
	private String message;
	
	public MailEntity(MailEntity mailEntity) { // constructor
		super(); // calls the java.lang.Object constructor
		//this. is optional, because there is no conflict with variable naming in this case
		this.mailId = mailEntity.getMailId();
		this.receiverUsername = mailEntity.getReceiverUsername();
		this.senderUsername = mailEntity.getSenderUsername() ;
		this.subject = mailEntity.getSubject();
		this.message = mailEntity.getMessage();
	}
	
	public MailEntity() { // construtor with no arguments
		super();
	}
	
	public long getMailId() {
		return mailId;
	}
	
	public String getReceiverUsername() {
		return receiverUsername;
	}
	
	public String getSenderUsername() {
		return senderUsername;
	}

	public String getSubject() {
		return subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMailId(long mailId) {
		this.mailId = mailId; 
		/* in this case this. is required, because I want to assign the value that came from the parameter to 
		   my instance variable which has the same name as my method parameter 
		   same goes for all of my setters 
		*/
	}

	public void setReceiverUsername(String receiverUsername) {
		this.receiverUsername = receiverUsername;
	}

	public void setSenderUsername(String senderUsername) {
		this.senderUsername = senderUsername;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	// this is not overriding, because the method signature is not the same as  the toString() of the Object class
	public String toStringReceivedMail() {
		return " From: " + senderUsername +
				"\n Subject: " + subject +
				"\n Message: " + message + "\n";
	}

	// not overriding, method signature is different than the toString() in Object class
	public String toStringSentMail() {
		return " To: " + receiverUsername +
				"\n Subject: " + subject +
				"\n Message: " + message + "\n";
	}
}

