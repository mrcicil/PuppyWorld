package com.example.ad.service;

import java.util.ArrayList;

import com.example.ad.domain.User;

public interface UserService {
	
	 public void saveUser(User user);
	 public ArrayList<User> findAllUsers();
	 public User findUserById(Long id);
	 public void deleteUserById(Long id);
	 public User findUserByUserName(String userName);
	 public boolean authenticateUser(User user);

}
