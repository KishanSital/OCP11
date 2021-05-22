package chapter4.entities;

import chapter4.constants.*;

public class MailEntity {
	private long mailId;
	private String receiverUsername;
	private String senderUsername;
	private String subject;
	private String message;
	
	public MailEntity(MailEntity mailEntity) {
		this.mailId = mailEntity.getMailId();
		this.receiverUsername = mailEntity.getReceiverUsername() + Constants.GMAIL_SUFFIX;
		this.senderUsername = mailEntity.getSenderUsername() + Constants.GMAIL_SUFFIX;
		this.subject = mailEntity.getSubject();
		this.message = mailEntity.getMessage();
	}
	
	public MailEntity() {
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

	public String toString() {
		return " From: " + senderUsername +
		", subject: " + subject +
		", message: " + message ;
	}
}

