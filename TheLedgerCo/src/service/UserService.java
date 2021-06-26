package service;

import java.util.*;

import model.User;

public class UserService {

	private ArrayList<User> userList;

	public UserService() {
		this.userList = new ArrayList<User>();
	}

	public ArrayList<User> getUserList() {
		return userList;
	}

	public User addUser(String userName) {
		User u = new User(userName);
		userList.add(u);
		return u;
	}

	public User getUserByName(String userName) {
		for (User u : userList) {
			if (u.getName().equalsIgnoreCase(userName)) {
				return u;
			}
		}
		return null;
	}

}
