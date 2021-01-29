package com.example.ad.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PostCommentDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long commentId;
	
	private String commentMessage;
	private LocalDateTime commentDateTime;
}
