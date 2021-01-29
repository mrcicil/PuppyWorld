package com.example.ad.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long postId;
	
	private PostCategory postCategory;
	
	private String postTitle, postMessage;
	
	private byte[] postImage;
}
