package com.example.ad.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long userId;
	
	private UserType userType;
	
	@NotEmpty
	@Column(unique=true)
	private String userName;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	@Email
	private String emailAddress;

	public User(UserType userType, @NotEmpty String userName, @NotEmpty String name, @NotEmpty String password,
			@NotEmpty @Email String emailAddress) {
		super();
		this.userType = userType;
		this.userName = userName;
		this.name = name;
		this.password = password;
		this.emailAddress = emailAddress;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
