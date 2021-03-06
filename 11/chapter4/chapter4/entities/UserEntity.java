package chapter4.entities;

import chapter4.constants.*;

public class UserEntity {
	
	private long userId;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String phoneNumber;
	private String birthDate;
	
	public UserEntity() {
	}
	
	public UserEntity (UserEntity userEntity) {
		this.userId = userEntity.getUserId();
		this.firstName = userEntity.getFirstName();
		this.lastName = userEntity.getLastName();
		this.username = userEntity.getUsername() + Constants.GMAIL_SUFFIX;
		this.password = userEntity.getPassword();
		this.phoneNumber = userEntity.getPhoneNumber();
		this.birthDate = userEntity.getBirthDate();
	}
	
	
	public long getUserId() {
		return userId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getBirthDate() {
		return birthDate;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	public String toString() {
		return  "userId = " + userId +
		", firstName = " + firstName  +
		", lastName = " + lastName  +
		", username = " + username  +
		", password = " + password+
		", phoneNumber = " + phoneNumber +
		", birthDate = " + birthDate;
	}
}	