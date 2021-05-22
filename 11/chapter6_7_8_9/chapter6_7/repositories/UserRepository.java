package chapter6_7.repositories;

import chapter6_7.entities.*;
import chapter6_7.interfaces.*;
import java.util.*;
import java.util.function.*;

public class UserRepository {
	
	private UserEntity user;
	private List <UserEntity> usersList = new ArrayList<UserEntity>();
	private Consumer<String> consumerMessages = msg -> System.out.println(msg); // Consumer to print out messages
	public AddToListInterface<UserEntity> addUserInterface = u -> insertUser(u); // made my own version of Predicate functional interface

	public UserRepository(UserEntity userEntity) {
		this();
		this.user = userEntity; // this. is not required here, because the variable names differ
	}
	
	public UserRepository() {
		super();
	}
	
	public void insertStandardUser(){
		{
		user.setUserId(1);
		user.setFirstName("Kishan");
		user.setLastName("Sital");
		user.setUsername("ksital");
		user.setPassword("1234");
		user.setPhoneNumber("005978544998");
		user.setBirthDate("3-06-1999");
		addUserInterface.add(user);
		}
		{
		user = new UserEntity();
		user.setUserId(autoIncrementId());
		user.setFirstName("Kishan");
		user.setLastName("Sital");
		user.setUsername("kishan");
		user.setPassword("1234");
		user.setPhoneNumber("005978544998");
		user.setBirthDate("3-06-1999");
		addUserInterface.add(user);		
		}
	}

	private boolean insertUser(UserEntity user){
		if (usersList.add(new UserEntity(user))){
			printNewlyAddedUser();
			return true;
		} else {
			consumerMessages.accept("Something went wrong, please try again later\n");
			return false;
		}
	}
	
	public void printNewlyAddedUser(){
		for (int i = getUsersList().size() -1; i == (getUsersList().size() -1); i++){
			consumerMessages.accept("\nAccount created successfully "+"\nYour gmail account is "+ getUsersList().get(i).getUsername() + "\n");
		}
	}
	
	public long autoIncrementId(){
		if (usersList.isEmpty()){
			return 1;
		} else {
			return (usersList.get((usersList.size())-1).getUserId()) + 1;
		}
	}
	
	public List<UserEntity> getUsersList() {
		return usersList;
	}
}
