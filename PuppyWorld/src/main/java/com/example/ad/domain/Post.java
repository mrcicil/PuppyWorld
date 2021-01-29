package com.example.ad.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long postId;
	
	private PostType postType;
	
	private String postTitle, postMessage;
	
	private byte[] postImage;
}
