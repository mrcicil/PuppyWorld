package com.example.ad.service;

import java.util.ArrayList;

import com.example.ad.domain.User;

public interface UserService {
	
	 public void saveUser(User user);
	 public ArrayList<User> findAllUsers();
	 public User findUserById(Integer id);
	 public void deleteUserById(Integer id);

}
