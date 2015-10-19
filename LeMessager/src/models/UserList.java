package models;

import java.util.ArrayList;
import designPatterns.Observable;

public class UserList extends Observable {
	private ArrayList<User> userList;
	
	public UserList(){
		this.userList = new ArrayList<User>();
	}
	
	public void addUser(User u){
		this.userList.add(u);
		this.notifyObs();
	}
} 
 