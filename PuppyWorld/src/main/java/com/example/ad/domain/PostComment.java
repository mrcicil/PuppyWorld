package com.example.ad.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PostComment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int commentId;
	
	private String commentMessage;
	private LocalDateTime commentDateTime;
	public PostComment(String commentMessage, LocalDateTime commentDateTime) {
		super();
		this.commentMessage = commentMessage;
		this.commentDateTime = commentDateTime;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getCommentMessage() {
		return commentMessage;
	}
	public void setCommentMessage(String commentMessage) {
		this.commentMessage = commentMessage;
	}
	public LocalDateTime getCommentDateTime() {
		return commentDateTime;
	}
	public void setCommentDateTime(LocalDateTime commentDateTime) {
		this.commentDateTime = commentDateTime;
	}
	
}
