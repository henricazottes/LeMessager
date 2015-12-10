package models;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import java.util.Iterator;

import javax.swing.DefaultListModel;

import designPatterns.Observable;

public class UserList extends Observable {

	//private DefaultListModel userList;
	private ArrayList<User> userList;
	
	public UserList() throws UnknownHostException{
		this.userList = new ArrayList<User>();		
	}
	
	public void addUser(User u){
		this.userList.add(u);
		//this.nameList.addElement(u);
		//this.notifyObs();
	}
	
	public void removeUser(User u){
		Iterator<User> it = this.userList.iterator();
		while (it.hasNext()) {
		  if(it.next().equals(u)){
			  it.remove();
			  
		  }
		}
	}
	
	
	@Override
	public String toString(){
		
		String result = "\n========";
		Iterator it = this.userList.iterator();
		while(it.hasNext()){
			result += "\n"+it.next();
		}
		
		result += "\n========";
		
		return result;
	}
	
	

	public boolean contains(User newUser) {
		// TODO Auto-generated method stub
		return this.userList.contains(newUser);
	}	
	
	public ArrayList<User> getUserList() {
		return userList;
	}
	
} 
 