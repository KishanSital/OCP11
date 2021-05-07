package chapter4.repositories;

import chapter4.entities.*;

import java.util.*;

public class UserRepository {
	
	private UserEntity user;
	private List <UserEntity> usersList = new ArrayList<UserEntity>();
	
	
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
		insertUser(user);
	}
	
	public boolean insertUser(UserEntity user){
		if (usersList.add(new UserEntity(user))){
			printNewlyAddedUser();
			return true;
		} else {
			System.out.println("Something went wrong, please try again later\n");
			return false;
		}
		/*   for (UserEntity users : usersList) {
		System.out.println(users.toString());
		}*/
	}
	
	public void printNewlyAddedUser(){
		for (int i = getUsersList().size() -1; i == (getUsersList().size() -1); i++){
			System.out.println("\nAccount created successfully "+"\nYour gmail account is "+ getUsersList().get(i).getUsername() + "\n");
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
