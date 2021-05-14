package chapter6_7.repositories;

import chapter6_7.entities.*;
import chapter6_7.interfaces.*;
import java.util.*;
import java.util.function.*;

public class UserRepository {
	
	private UserEntity user;
	private List <UserEntity> usersList = new ArrayList<UserEntity>();
	private Consumer<String> consumerMessages = msg -> System.out.println(msg);
	public UserInterface<UserEntity> userInterface = u -> insertUser(u);

	public UserRepository(UserEntity userEntity) {
		this.user = userEntity;
	}
	
	public UserRepository() {
	}
	
	public void insertStandardUser (){
		user.setUserId(1);
		user.setFirstName("Kishan");
		user.setLastName("Sital");
		user.setUsername("ksital");
		user.setPassword("1234");
		user.setPhoneNumber("005978544998");
		user.setBirthDate("3-06-1999");
		userInterface.addUser(user);
	}

	public boolean insertUser(UserEntity user){
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
