package com.example.ad.service;

import java.util.ArrayList;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ad.domain.User;
import com.example.ad.repo.UserRepository;


@Service
@Transactional
public class UserServiceImplementation implements UserService{
	
	@Autowired
	UserRepository urepo;

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		urepo.save(user);
	}

	@Override
	public ArrayList<User> findAllUsers() {
		// TODO Auto-generated method stub
		return (ArrayList<User>) urepo.findAll();
		
	}

	@Override
	public User findUserById(Long id) {
		// TODO Auto-generated method stub
		ArrayList<User> uList = findAllUsers();
		User searchUser = null;
		for (Iterator <User> iterator = uList.iterator(); iterator.hasNext();) {
			User user = iterator.next();
			if(user.getUserId() == id) {
				searchUser = user;
			}
		}
		return searchUser;
	}
	
	@Override
	public User findUserByUserName(String userName) {
		// TODO Auto-generated method stub
		ArrayList<User> uList = findAllUsers();
		User searchUser = null;
		for (Iterator <User> iterator = uList.iterator(); iterator.hasNext();) {
			User user = iterator.next();
			if(user.getUserName().equals(userName)){
				searchUser = user;
			}
		}
		return searchUser;
	}
	
	@Override
	public boolean authenticateUser(User user) {
		// TODO Auto-generated method stub
		User dbuser = findUserByUserName(user.getUserName());
		
		if (dbuser.getUserName().equals(user.getUserName()) && dbuser.getPassword().equals(user.getPassword()))
			return true;
		else
			return false;
	}

	@Override
	public void deleteUserById(Long Id) {
		// TODO Auto-generated method stub
		User searchUser = findUserById(Id);
		urepo.delete(searchUser);
	}
}
