package com.example.ad.service;

import java.util.ArrayList;

import com.example.ad.domain.PostComment;

public interface PostCommentService {
	
	public void savePostComment(PostComment postComment);
	public ArrayList<PostComment> findAllPostComments();
	public PostComment findPostCommentById(int Id);
	ArrayList<PostComment> findPostCommentsbyPostId(int postid);
	void deletePostCommentById(int commentId);

}
